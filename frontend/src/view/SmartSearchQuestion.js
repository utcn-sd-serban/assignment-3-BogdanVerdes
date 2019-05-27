import React, {Component} from "react";
import question from "../model/Questions";
import SearchQuestion from "./SearchQuestion";


const mapModelStateToComponentState = modelState => ({
    searchedQuestions: modelState.searchedQuestions
});

export default class SmartSearchQuestion extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(question.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        question.addListener("change",this.listener);
    }

    render(){
        return(
            <SearchQuestion
            questions={this.state.searchedQuestions} />
        );
    }

    componentWillUnmount(){
        question.removeListener("change",this.listener);
    }
}