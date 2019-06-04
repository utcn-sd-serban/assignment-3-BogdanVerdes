const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    loadAllUsers = () => {
        return fetch(BASE_URL + "/users", {
            method: "GET",
            headers: {
            }
        }).then(response => response.json());
    };

    createUser = (username, password) => {
        
        return fetch(BASE_URL + "/register", {
            method: "POST",
            body: JSON.stringify({
                username: username,
                password: password,
                score : 0,
                email : "dummy"
            }),
            headers: {
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    loginUser = (username, password) => {
        return fetch(BASE_URL + "/login", {
            method: "POST",
            body: JSON.stringify({
                username: username,
                password: password
            }),
            headers: {
                "Content-type": "application/json"
            }
        });
    };

    loadAllQuestions = () => {
        return fetch(BASE_URL + "/questions", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("Unknown user");
                window.location.assign("#/");
            });
    };

    createQuestion = (title, body, username, tags) => {
        return fetch(BASE_URL + "/questions", {
            method: "POST",
            body: JSON.stringify({
                
                title: title,
                body: body,
                username: username,
                tags: tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    filterQuestions = (filterType, filterText) => {
        return fetch(BASE_URL + "/questions/filter" + filterType + "=" + filterText, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("Filter not working");
                window.location.assign("#/");
            });
    };

    findAnswersByQuestionId = (questionId) => {
        questionId = parseInt(questionId);
        return fetch(BASE_URL + "/question/" + questionId + "/answers", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("Unknown user");
                window.location.assign("#/");
            });
    };

    createAnswer = (text, author, questionId) => {
        return fetch(BASE_URL + "/answers", {
            method: "POST",
            body: JSON.stringify({
                text: text,
                author: author, 
                questionId: questionId
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    editAnswer = (answerId, newText) => {
        return fetch(BASE_URL + "/answer/" + answerId, {
            method: "PUT",
            body: JSON.stringify({
                newAnswerText: newText
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json())
            .catch(error => console.log(error))
    };

    deleteAnswer = (answerId) => {
        return fetch(BASE_URL + "/answer/" + answerId, {
            method: "DELETE",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json())
            .catch(error => console.log(error))
    }


}