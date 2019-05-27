import React, {Component} from "react";
import question from "../model/Questions";
import QuestionsList from "./QuestionsList";
import questionsListPresenter from "../presenter/questionsListPresenter";


const mapModelStateToComponentState = modelState => ({
    questions: modelState.questions,
}
);

export default class SmartQuestionsList extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(question.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        question.addListener("change",this.listener);
        questionsListPresenter.onInit();
    }

    render(){
        return(
            <QuestionsList 
            onAskQuestion={questionsListPresenter.onAskQuestion}
            onChange={questionsListPresenter.onChange}
            onSearch={questionsListPresenter.onSearch}
            onListAnswers={questionsListPresenter.onListAnswers}
            questions={this.state.questions}/>
        );
    }

    componentWillUnmount(){
        question.removeListener("change",this.listener);
    }
}