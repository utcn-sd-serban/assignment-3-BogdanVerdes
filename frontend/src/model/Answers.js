import { EventEmitter } from "events";
import { getClient } from "./Users";

class Answer extends EventEmitter{
    constructor(){
        super();
        this.state = {
            answers : [{
                qId: 0,            
                text: "This is an answer",
                author: "verdes",
                score: 0
            }, {
                qId: 1,
                text: "Good",                
                author: "bogdan",
                score: 0
            }],
            newAnswer:{
                text: "",
                author: "",
                score: 0
            },
            searchedAnswers:[]
        }
    }

    addAnswer(text,author,qId){
        return getClient().createAnswer(text, author, qId)
            .then(answer => {
                if (!this.answerAlreadyExists(answer.id)) {
                    this.appendAnswer(answer);
                    //this.emit("changeAnswer", this.state); // the state has changed, passed the new state as arg
                }
            })
    }

    changeNewAnswerProperty(property,value){
            this.state = {
                ...this.state,
                newAnswer:{
                    ...this.state.newAnswer,
                    [property]:value
                }
            }
            this.emit("change",this.state);
    }

    listAnwsers(qId){
        return getClient().findAnswersByQuestionId(qId)
        .then(answers => {
            if (answers !== undefined) {
                this.state = {
                    ...this.state,
                    answers: answers
                };
                this.emit("changeAnswer", this.state);
            }
        })
    }

    modifyTextForAnswer(newAnswer) {
        let answers = this.state.answers;
        for (let i = 0; i < answers.length; i++) {
            if (newAnswer.id === answers[i].id) {
                answers[i].text = newAnswer.text;
            }
        }
        this.state = {
            ...this.state,
            answers: answers
        };
        this.emit("changeAnswer", this.state);
    }

    editAnswer(answerId) {
        let newAnswerText = this.state.newAnswer.text;
        return getClient().editAnswer(answerId, newAnswerText)
            .then(newAnswer => this.modifyTextForAnswer(newAnswer));
    }

    updateAnswersAfterDelete(answerId) {
        let answers = this.state.answers;
        for (let i = 0; i < answers.length; i++) {
            if (answerId === answers[i].id) {
                answers.splice(i, 1);
            }
        }
        this.state = {
            ...this.state,
            answers: answers
        };
        this.emit("changeAnswer", this.state);
    }

    deleteAnswer(answerId) {
        getClient().deleteAnswer(answerId)
            .then(deletedAnswerId => {
                console.log(deletedAnswerId);
                this.updateAnswersAfterDelete(deletedAnswerId);
            });
    }
}

const answer = new Answer();

export default answer;