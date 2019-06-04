import question from "../model/Questions";
import user from "../model/Users";

class CreateQuestionPresenter {

    onAskQuestion(){
        question.addQuestion(question.state.newQuestion.title,
            question.state.newQuestion.body,
            question.state.newQuestion.tags,user.state.currentUser);
        question.changeNewQuestionProperty("title","");
        question.changeNewQuestionProperty("body","");
        question.changeNewQuestionProperty("tags","");

        window.location.assign("#/questions");
    }

    onChange(property,value){
        question.changeNewQuestionProperty(property,value);
    }
}

const createQuestionPresenter = new CreateQuestionPresenter();

export default createQuestionPresenter;