import React from "react";

const AnswersList = ({ answers, onAnswerQuestion, onChange, onEdit, onDelete, newAnswer, qId }) => (
    <div>
        <h2>Answers</h2>
        <table>
            <thead>
            
            </thead>
            <tbody>
                {
                    answers.map((answer,index) =>(
                        <tr key={index}>
                            <td>{answer.text}</td>
                            <td>{answer.author}</td>
                            <td>{answer.score}</td>
                            <button onClick={() => onEdit(qId)}>Edit Answer</button>
                            <button onClick={() => onDelete(qId)}>Delete Answer</button>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <input value={newAnswer}
                onChange={ e=> onChange("text",e.target.value)}/> 
        <button onClick={() => onAnswerQuestion(qId)}>Answer Question</button>
        <br/>
        
    </div>
);

export default AnswersList;