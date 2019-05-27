import answer from "../model/Answers";
import user from "../model/Users"

class AnswersListPresenter {
    onAnswerQuestion(index){
        answer.addAnswer(answer.state.newAnswer.text,user.state.currentUser.username,index)
        answer.state.newAnswer.text = "";
        window.location.assign("#/answers/"+index);
    }
    onChange(property,value){
        answer.changeNewAnswerProperty(property,value);
    } 
    onEdit(answerId){
        answer.editAnswer(answerId);
    };

    onDelete(answerId){
        answer.deleteAnswer(answerId);
    };

    onInit(questionId){
        answer.listAnswers(questionId);
    }  
}

const answersListPresenter = new AnswersListPresenter();

export default answersListPresenter;