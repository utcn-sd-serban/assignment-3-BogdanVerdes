import React from "react";

const CreateQuestion = ({ newTitle, newBody, newTags,onChange, onAskQuestion }) => (
    <div>
        <h2>Ask question:</h2>
        <div>
            <label>Title: </label>
            <input value={newTitle} 
                onChange={ e=> onChange("title",e.target.value)}/>
            <br />
            <label>Body:</label>
            <input value={newBody} 
                onChange={ e=> onChange("body",e.target.value)}/>
            <br />
            <label>Tags: </label>
            <input value={newTags} 
                onChange={ e=> onChange("tags",e.target.value)}/>
            <br />
            <button onClick={onAskQuestion}>Ask Question</button>
        </div>
    </div>
);

export default CreateQuestion;