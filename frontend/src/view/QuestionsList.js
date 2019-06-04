import React from "react";

const QuestionsList = ({ questions, onSearch, onAskQuestion, onListAnswers, onChange, searchTitle, }) => (
    <div>
        <h2>Questions</h2>
        <table>
            <thead>
            <input value={searchTitle} 
                onChange={ e=> onChange("title",e.target.value)}/>
            <button onClick={onSearch}>Search</button>
            </thead>
            <tbody>
                {
                    questions.slice().reverse().map((question,index) =>(
                        <tr key={index}>
                            <td>{question.title}</td>
                            <td>{question.body}</td>
                            <td>{question.tags}</td>
                            <td>{question.author}</td>
                            <td>{question.score}</td>
                            <td><button onClick={() => onListAnswers(index)}>See answers</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <button onClick={onAskQuestion}>Ask Question</button>
        <br/>
        
    </div>
);

export default QuestionsList;