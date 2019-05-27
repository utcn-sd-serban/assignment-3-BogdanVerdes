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

public class QuestionServiceTest {
    private static Question question1 = new Question("Hello","How are you?","verdes");
    private static Question question2 = new Question("What is java","How do i use it?","bogdan");

    private static RepositoryFactory createMockedFactory() throws SQLException {
        RepositoryFactory repositoryFactory = new InMemoryRepositoryFactory();
        repositoryFactory.createQuestionRepository().save(question1);
        repositoryFactory.createQuestionRepository().save(question2);
        return repositoryFactory;
    }

    @Test
    public void testQuestions() throws SQLException {
        RepositoryFactory repositoryFactory = createMockedFactory();
        QuestionService questionService = new QuestionService(repositoryFactory);

        Assert.assertEquals(2,questionService.findAllQuestions().size());

        Assert.assertEquals(1,questionService.findByTitle("What is java").size());
    }
}
