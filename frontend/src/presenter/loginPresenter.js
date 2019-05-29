import user from "../model/Users";

class QuestionsListPresenter {
    onLogin(){
        user.state.currentUser = user.state.newUser.username;
        user.loginUser(user.state.newUser.username,user.state.newUser.password)
        .then(()=>{
            //alert("merge");
            user.searchUser(user.state.newUser.username,user.state.newUser.password);
            window.location.assign("#/questions");
        
        }
        );
    }
    onRegister(){
        user.state.currentUser = user.state.newUser.username;
        user.registerUser(user.state.newUser.username,user.state.newUser.password)
        .then(()=> { 
            user.addUser(user.state.newUser.username,user.state.newUser.password);
            window.location.assign("#/questions");
        });        
        
    }
    onChange(property,value){
        user.changeNewUserProperty(property,value);
    }

    onInit(){
        user.loadUsers();
    }
}

const questionsListPresenter = new QuestionsListPresenter();

export default questionsListPresenter;