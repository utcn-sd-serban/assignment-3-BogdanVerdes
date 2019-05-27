import question from "../model/Questions";
import user from "../model/Users";

class CreateQuestionPresenter {

    onAskQuestion(){
        question.addQuestion(question.state.newQuestion.title,
            question.state.newQuestion.body,
            question.state.newQuestion.tags,user.state.currentUser.username);
        question.changeNewQuestionProperty("title","");
        question.changeNewQuestionProperty("body","");
        question.changeNewQuestionProperty("tags","");
        question.createQuestion(question.state.newQuestion.title, 
            question.state.newQuestion.body, user.state.currentUser.username,
            question.state.newQuestion.tags);
        window.location.assign("#/questions");
    }

    onChange(property,value){
        question.changeNewQuestionProperty(property,value);
    }
}

const createQuestionPresenter = new CreateQuestionPresenter();

export default createQuestionPresenter;