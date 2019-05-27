package ro.utcn.sd.vba.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.*;
import ro.utcn.sd.vba.a1.service.*;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Order(0)
public class ConsoleController implements CommandLineRunner{
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;
    private final TagService tagService;
    private final QuestionTagService questionTagService;
    private final VoteQuestionService voteQuestionService;
    private final VoteAnswerService voteAnswerService;
    private boolean usingApp = true;
    private User currentUser;
    private boolean logged = false;
    @Override
    public void run(String... args) throws Exception {
        while(!logged){
           handleLogIn();
        }


        while(usingApp){
            print("Logged in as " + currentUser.getUsername() );
            print("Type a command or type help to see the list of commands: ");
            String command = scanner.next().trim();
            handleCommand(command);
        }

    }

    private void handleCommand(String command) throws SQLException {
        switch(command){
            case "help":
                handleHelp();
                break;
            case "show questions":
                handleShowQuestions();
                break;
            case "ask question":
                handleAskQuestion();
                break;
            case "search by title":
                handleSearchQuestionByTitle();
                break;
            case "search by tag":
                handleSearchQuestionByTag();
                break;
            case "show answers":
                handleShowAnswers();
                break;
            case "answer question":
                handleAnswerQuestion();
                break;
            case "edit answer":
                handleEditAnswer();
                break;
            case "delete answer":
                handleDeleteAnswer();
                break;
            case "vote question":
                handleVoteQuestion();
                break;
            case "vote answer":
                handleVoteAnswer();
                break;
            case "show tags":
                handleTags();
                break;
            case "logout":
                logged = false;
                while(!logged) {
                    handleLogIn();
                }
                break;
            case "exit":
                usingApp = false;
            default:
                print("Invalid command. Try typing help.");
        }
    }

    private void handleHelp(){
        print("List of commands: ");
        print("For questions: show questions, ask question, search by title, search by tag");
        print("For answers: show answers, answer question, edit answer, delete answer");
        print("For voting: vote question, vote answer");
        print("General commands: show tags, logout, help, exit");
    }

    private void handleShowQuestions(){
        questionService.findAllQuestions().forEach(System.out::println);
    }

    private void handleAskQuestion() throws SQLException {
        print("Title: ");
        String title = scanner.next().trim();
        print("Question: ");
        String body = scanner.next().trim();
        Question questionNew = new Question(title,body,currentUser.getUsername());
        print("Type tags for your question. Separate by space and press enter when done." +
                "\nIf a tag does not exist it will be created");
        String[] tags = scanner.next().trim().split(" ");
        for(String t:tags){
            if(tagService.findAllTags().isEmpty()){
                tagService.addTag(new Tag(t));
                questionTagService.saveQuestionTag(new QuestionTag(t,questionNew.getId()));
            }
            for(Tag tag:tagService.findAllTags()){
                if(t.equals(tag.getName())){
                    questionTagService.saveQuestionTag(new QuestionTag(t,questionNew.getId()));
                }
                else{
                    tagService.addTag(new Tag(t));
                    questionTagService.saveQuestionTag(new QuestionTag(t,questionNew.getId()));
                }
            }
        }
        questionService.saveQuestion(questionNew);
    }

    private void handleSearchQuestionByTitle(){
        print("Title: ");
        String title = scanner.next().trim();
        questionService.findByTitle(title).forEach(System.out::println);
    }

    private void handleSearchQuestionByTag(){
        print("Enter tag: ");
        String tag = scanner.next().trim();
        questionTagService.findQuestionsByTag(new Tag(tag)).forEach(System.out::println);
    }

    private void handleShowAnswers() throws SQLException {
        print("Id of question: ");
        String id = scanner.next().trim();
        int qId = Integer.parseInt(id);
        Question question = questionService.findById(qId);
        answerService.findAnswersByQuestion(question).forEach(System.out::println);
    }

    private void handleAnswerQuestion() throws SQLException {
        print("Id of question: ");
        String id = scanner.next().trim();
        int qId = Integer.parseInt(id);
        Question question = questionService.findById(qId);
        print("Your answer: ");
        String answer = scanner.next().trim();
        answerService.saveAnswer(new Answer(answer,currentUser.getUsername(),qId));
    }

    private void handleEditAnswer(){
        print("Id of answer: ");
        String id = scanner.next().trim();
        int aId = Integer.parseInt(id);
        print("New answer: ");
        String answerEdit = scanner.next().trim();
        Optional<Answer> answer = answerService.editAnswer(aId,currentUser.getUsername(),answerEdit);
        print("Your answer was edited.");
    }

    private void handleDeleteAnswer() throws SQLException {
        print("Id of answer: ");
        String id = scanner.next().trim();
        int aId = Integer.parseInt(id);
        Optional<Answer> answer = answerService.deleteAnswer(aId,currentUser.getUsername());
        print("Your answer was deleted");
    }

    private void handleVoteQuestion() throws SQLException {
        print("Id of question: ");
        String id = scanner.next().trim();
        int qId = Integer.parseInt(id);
        print("Vote up or down? Type retract to retract vote");
        String vote = scanner.next().trim();
        if(vote.equals("up")){
            voteQuestionService.voteQuestion(qId,currentUser.getUsername(),true);
        }
        if(vote.equals("down")){
            voteQuestionService.voteQuestion(qId,currentUser.getUsername(),false);
        }
        if(vote.equals("retract")){
            voteQuestionService.removeVote(qId,currentUser.getUsername());
        }
        else print("That is not a valid vote type");
    }

    private void handleVoteAnswer() throws SQLException {
        print("Id of answer: ");
        String idAnswer = scanner.next().trim();
        int aId = Integer.parseInt(idAnswer);
        print("Vote up or down? Type retract to retract vote");
        String vote = scanner.next().trim();
        if(vote.equals("up")){
            voteAnswerService.voteAnswer(aId,currentUser.getUsername(),true);
        }
        if(vote.equals("down")){
            voteAnswerService.voteAnswer(aId,currentUser.getUsername(),false);
        }
        if(vote.equals("retract")){
            voteAnswerService.removeVote(aId,currentUser.getUsername());
        }
        else print("That is not a valid vote type");
    }

    private void handleLogIn() throws SQLException {
        print("Type login or register to continue:");
        String loginRegister = scanner.next().trim();
        if(loginRegister.equals("login")){
            try {
                print("Username: ");
                String username = scanner.next().trim();
                print("Password: ");
                String password = scanner.next().trim();
                currentUser = userService.loginUser(username, password);
                logged = true;
            } catch (RuntimeException e) {
                print("Invalid username or password! ");
            }
        }
        if(loginRegister.equals("register")){
            try {
                print("Username: ");
                String username = scanner.next().trim();
                print("Password: ");
                String password = scanner.next().trim();
                print("Email: ");
                String email = scanner.next().trim();
                currentUser = userService.createUser(username, password, email);
                logged = true;
            } catch (RuntimeException e) {
                print("Choose a different username! ");
            }
        }
        else print("Invalid command");
    }

    private void handleTags(){
        tagService.findAllTags().forEach(System.out::println);
    }

    private void print(String value){
        System.out.println(value);
    }
}
