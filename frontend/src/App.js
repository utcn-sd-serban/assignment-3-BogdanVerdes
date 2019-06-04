import React from 'react';
import './App.css';

import {HashRouter,Switch,Route} from "react-router-dom";
import SmartQuestionsList from './view/SmartQuestionsList';
import SmartCreateQuestion from './view/SmartCreateQuestion';
import SmartSearchQuestion from './view/SmartSearchQuestion';
import SmartLogin from './view/SmartLogin';
import SmartAnswersList from './view/SmartAnswersList';

const App = () => (
      <div className="App">
        <HashRouter>
          <Switch>
            <Route exact={true} component={SmartLogin} path="/"/>
            <Route exact={true} component={SmartQuestionsList} path="/questions"/>
            <Route exact={true} component={SmartCreateQuestion} path="/ask-question"/>
            <Route exact={true} component={SmartSearchQuestion} path="/search"/>
            <Route exact={true} component={SmartAnswersList} path="/answers/:index"/>
          </Switch>
        </HashRouter>
      </div>
    );

export default App;
