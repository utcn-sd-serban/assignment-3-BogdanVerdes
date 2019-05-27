package ro.utcn.sd.vba.a1;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;
import ro.utcn.sd.vba.a1.repository.memory.InMemoryRepositoryFactory;
import ro.utcn.sd.vba.a1.service.AnswerService;
import ro.utcn.sd.vba.a1.service.QuestionService;

import java.sql.SQLException;
import java.util.List;

public class AnswerServiceTest {
    private static Answer answer1 = new Answer("yes","bogdan",1);
    private static Answer answer2 = new Answer("no","verdes",1);

    private static RepositoryFactory createMockedFactory() throws SQLException {
        RepositoryFactory repositoryFactory = new InMemoryRepositoryFactory();
        repositoryFactory.createAnswerRepository().save(answer1);
        repositoryFactory.createAnswerRepository().save(answer2);
        return repositoryFactory;
    }

    @Test
    public void testAnswers() throws SQLException {
        RepositoryFactory repositoryFactory = createMockedFactory();
        AnswerService answerService = new AnswerService(repositoryFactory);

        answerService.findAnswersByQuestion(new Question("Hello","Are you good","bogdan"));
        Assert.assertEquals(2,repositoryFactory.createAnswerRepository().findAll().size());

        answerService.deleteAnswer(2,"verdes");
        Assert.assertEquals(1,repositoryFactory.createAnswerRepository().findAll().size());
    }
}
