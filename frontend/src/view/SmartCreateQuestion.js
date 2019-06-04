import React, {Component} from "react";
import question from "../model/Questions";
import CreateQuestion from "./CreateQuestion";
import createQuestionPresenter from "../presenter/createQuestionPresenter";

const mapModelStateToComponentState = modelState => ({
    title: modelState.newQuestion.title,
    body: modelState.newQuestion.body,
    tags: modelState.newQuestion.tags
});

export default class SmartCreateQuestion extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(question.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        question.addListener("change",this.listener);
    }

    render(){
        return(
            <CreateQuestion 
            onAskQuestion={createQuestionPresenter.onAskQuestion}
            onChange={createQuestionPresenter.onChange}
            title={this.state.title}
            body={this.state.body}
            tags={this.state.tags}/>
        );
    }

    componentWillUnmount(){
        question.removeListener("change",this.listener);
    }
}