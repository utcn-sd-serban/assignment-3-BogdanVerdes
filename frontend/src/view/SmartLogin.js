import React, {Component} from "react";
import user from "../model/Users";
import Login from "./Login";
import loginPresenter from "../presenter/loginPresenter";

const mapModelStateToComponentState = modelState => ({
    username: modelState.users.username,
    password: modelState.users.password
});

export default class SmartLogin extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(user.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        user.addListener("change",this.listener);
        loginPresenter.onInit();
    }

    render(){
        return(
            <Login 
            username={this.state.username}
            password={this.state.password}
            onLogin={loginPresenter.onLogin}
            onRegister={loginPresenter.onRegister}
            onChange={loginPresenter.onChange}/>
        );
    }

    componentWillUnmount(){
        user.removeListener("change",this.listener);
    }
}