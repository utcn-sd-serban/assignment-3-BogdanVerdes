import { EventEmitter } from "events";
import  {getClient} from "./Users";

class Question extends EventEmitter{
    constructor(){
        super();
        this.state = {
            questions : [{
                title: "What is javascript?",
                body: "I want to know",
                tags: "programming,javascript",
                author: "bogdan",
                score: 0
            }, {
                title: "How are you?",
                body: "Hello i am new here",
                tags: "general",
                author: "verdes",
                score: 0
            }],
            newQuestion:{
                title: "",
                body: "",
                tags: "",
                author: "",
                score: 0
            },
            searchedQuestions: []
        }
    }

    addQuestion(title,body,tags,author){
        this.state = {
            ...this.state,
            questions: this.state.questions.concat([{
                title: title,
                body: body,
                tags: tags,
                author: author,
                score: 0
            }])
        }
        this.emit("change",this.state);
    }

    loadQuestions() {
        return getClient().loadAllQuestions()
        .then(questions => {
            this.state = {
                ...this.state,
                questions: questions
            };
            this.emit("change", this.state);
        });
           
    }

    changeNewQuestionProperty(property,value){
            this.state = {
                ...this.state,
                newQuestion:{
                    ...this.state.newQuestion,
                    [property]:value
                }
            }
            this.emit("change",this.state);
    }

    changeMainStateProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    searchQuestion(title){
            return getClient().filterQuestions("Tag",title)
            .concat(getClient().filterQuestions("Title",title)).then(
                searchedQuestions => {
                    this.changeMainStateProperty("searchedQuestions",searchedQuestions);
                }
            );
    }
}

const question = new Question();

export default question;