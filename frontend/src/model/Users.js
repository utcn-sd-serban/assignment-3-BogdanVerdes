import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";
import WebSocketListener from "../websocket/WebSocketListener";
import question from "./Questions";

let client = new RestClient();
let wsListener = new WebSocketListener();
wsListener.client.deactivate();

export function getClient() {
    return client;
}

class User extends EventEmitter{
    constructor(){
        super();
        this.state = {
            users : [{
                username: "bogdan",
                password: "verdes"
            }, {
                username: "verdes",
                password: "bogdan"
            }],
            newUser:{
                username: "",
                password: ""
            },
            searchedUser: [],
            currentUser: ""
        }
    }

    changeMainStateProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    addUser(username,password){
        this.state = {
            ...this.state,
            users: this.state.users.concat([{
                username: username,
                password: password
            }]),
            currentUser:{
                username: username,
                password: password
            }
        }
        
        this.emit("change",this.state);
    }

    loginUser(username, password) {
        client = new RestClient(username, password);
        wsListener.client.deactivate();
        wsListener = new WebSocketListener(username, password);
        this.onEvent(wsListener);
        return client.loginUser(username, password)
            .then(response => {
                    if (response.ok) {
                        this.changeMainStateProperty("currentUser", username);
                        response = response.json();
                    } else {
                        this.changeMainStateProperty("currentUser", "");
                    }
                }
            );
    }

    onEvent(wsListener) {
        wsListener.on("event", event => {
            if (event.type === "USER_CREATED") {                
                this.addUser(event.user.username,event.user.password);
            }
            if (event.type === "QUESTION_CREATED") {
                question.addQuestion(event.question.title,event.question.body,
                    event.question.tags,event.user.username);
            }           
        });
    }

    loadUsers() {
        return client.loadAllUsers()
            .then(users => {
                this.state = {
                    ...this.state,
                    users: users
                };
                this.emit("change", this.state);
            });
    }

    

    registerUser(username, password) {
        client = new RestClient(username, password);
        wsListener.client.deactivate();
        wsListener = new WebSocketListener(username, password);
        this.onEvent(wsListener);
        
        return client.createUser(username, password)
            .then(user => this.addUser(username,password));
    }

    changeNewUserProperty(property,value){
            this.state = {
                ...this.state,
                newUser:{
                    ...this.state.newUser,
                    [property]:value
                }
            }
            this.emit("change",this.state);
    }

    logout(){
        question.clearQuestions();
        this.changeMainStateProperty("currentUser", "");
        window.location.assign("#/");
        client = new RestClient();
        wsListener.client.deactivate();
    }

    searchUser(username,password){
        let newUser = user.state.newUser;
        let users = user.state.users;

        for (let i = 0; i < users.length; i++) {
            if (newUser.username === users[i].username) {
                if (newUser.password === users[i].password) {
                    user.state.currentUser.username = newUser.username;
                    user.state.currentUser.password = newUser.password;
                }

            }
        }
    }
}

const user = new User();

export default user;