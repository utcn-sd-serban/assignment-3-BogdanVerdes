package ro.utcn.sd.vba.a1;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.VoteAnswer;
import ro.utcn.sd.vba.a1.model.VoteQuestion;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;
import ro.utcn.sd.vba.a1.repository.memory.InMemoryRepositoryFactory;
import ro.utcn.sd.vba.a1.service.AnswerService;
import ro.utcn.sd.vba.a1.service.QuestionService;
import ro.utcn.sd.vba.a1.service.VoteAnswerService;
import ro.utcn.sd.vba.a1.service.VoteQuestionService;

import java.sql.SQLException;

public class VoteServiceTest {
    private static Question question1 = new Question("Hello","How are you?","verdes");
    private static Question question2 = new Question("What is java","How do i use it?","bogdan");
    private static Answer answer1 = new Answer("yes","bogdan",1);
    private static Answer answer2 = new Answer("no","verdes",1);

    private static RepositoryFactory createMockedFactory() throws SQLException {
        RepositoryFactory repositoryFactory = new InMemoryRepositoryFactory();
        repositoryFactory.createQuestionRepository().save(question1);
        repositoryFactory.createQuestionRepository().save(question2);
        repositoryFactory.createAnswerRepository().save(answer1);
        repositoryFactory.createAnswerRepository().save(answer2);

        return repositoryFactory;
    }

    @Test
    public void voteTest() throws SQLException {
        RepositoryFactory repositoryFactory = createMockedFactory();
        VoteAnswerService voteAnswerService = new VoteAnswerService(repositoryFactory);
        VoteQuestionService voteQuestionService = new VoteQuestionService(repositoryFactory);
        AnswerService answerService = new AnswerService(repositoryFactory);
        QuestionService questionService = new QuestionService(repositoryFactory);

        voteAnswerService.voteAnswer(1,"verdes",true);
        voteQuestionService.voteQuestion(2,"verdes",true);
        voteAnswerService.voteAnswer(2,"bogdan",false);
        Assert.assertEquals(1,(int)answerService.findAnswersByQuestion(question1).get(0).getScore());
        Assert.assertEquals(-1,(int)answerService.findAnswersByQuestion(question1).get(1).getScore());
        Assert.assertEquals(1,questionService.findById(2).getScore());

        voteAnswerService.removeVote(1,"verdes");
        Assert.assertEquals(0,(int)answerService.findAnswersByQuestion(question1).get(0).getScore());
    }
}
