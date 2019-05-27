import React, {Component} from "react";
import answer from "../model/Answers";
import AnswersList from "./AnswersList";
import answersListPresenter from "../presenter/answersListPresenter";


const mapModelStateToComponentState = modelState => ({
    searchedAnswers: modelState.searchedAnswers,
}
);

export default class SmartAnswersList extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(answer.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        answer.addListener("change",this.listener);
        answersListPresenter.onInit(answer.state.qId);
    }

    render(){
        return(
            <AnswersList 
            answers = {this.state.searchedAnswers}
            qId = {this.state.searchedAnswers[0].qId}
            onAnswerQuestion = {answersListPresenter.onAnswerQuestion}
            onChange = {answersListPresenter.onChange}
            onEdit = {answersListPresenter.onEdit}
            onDelete = {answersListPresenter.onDelete}
           />
        );
    }

    componentWillUnmount(){
        answer.removeListener("change",this.listener);
    }
}