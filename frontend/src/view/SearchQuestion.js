import React from "react";

const SearchQuestion = ({ questions }) => (
    <div>
        <h2>Questions</h2>
        <table>
            <thead>
            </thead>
            <tbody>
                {
                    questions.map((question,index) =>(
                        <tr key={index}>
                            <td>{question.title}</td>
                            <td>{question.body}</td>
                            <td>{question.tags}</td>
                            <td>{question.author}</td>
                            <td>{question.score}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </div>
);

export default SearchQuestion;