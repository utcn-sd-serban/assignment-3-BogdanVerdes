import { EventEmitter } from "events";
import  {getClient} from "./Users";

class Question extends EventEmitter{
    constructor(){
        super();
        this.state = {
            questions : [],
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
        return getClient().createQuestion(title, body, author, tags)
            .then(question => {
                if (!this.questionAlreadyExists(question.id)) {
                    this.appendQuestion(question);
                }


            });
    }

    appendQuestion(question) {
        this.state = {
            ...this.state,
            questions: [question].concat(this.state.questions)
        };
        this.emit("change",this.state);
    }

    questionAlreadyExists(questionId) {
        let questions = this.state.questions;
        for (let i = 0; i < questions.length; i++) {
            if (questions[i].id === questionId)
                return true;
        }
        return false;
    }

    loadQuestions() {
        return getClient().loadAllQuestions()
        .then(questions => {
            console.log(questions);
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

    // remove duplicates
    searchQuestion(title){
            return getClient().filterQuestions("Tag",title)
                .then(tagFiltered => {
                    console.log("Tag filtered: ");
                    console.log(tagFiltered);
                    getClient().filterQuestions("Title",title)
                        .then(titleFiltered => {
                            console.log("title filtered: ");
                            console.log(titleFiltered);                            
                            var names = tagFiltered.concat(titleFiltered);
                            for(var i=0; i<names.length; ++i) {
                                for(var j=i+1; j<names.length; ++j) {
                                    if(names[i] === names[j])
                                        names.splice(j--, 1);
                                }
                            }
                            let unique = [...new Set(names)];
                            this.state.searchedQuestions = unique;
                            console.log(this.state.searchedQuestions);
                            //let unique = [...new Set(this.state.searchedQuestions)];
                            //this.state.searchedQuestions = unique;
                            this.emit("change", this.state); 
                        })
                        
                });
    }
}

const question = new Question();

export default question;