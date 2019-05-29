package ro.utcn.sd.vba.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.entity.*;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class Seed implements CommandLineRunner {
    private final RepositoryFactory repositoryFactory;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        repositoryFactory.createUserRepository().save(
                new User("bogdan", passwordEncoder.encode("verdes98"), 0, "bogdanverdes@yahoo.com"));
        repositoryFactory.createUserRepository().save(
                new User("verdes", passwordEncoder.encode("bogdan98"), 0, "verdes@gmail.com"));
        repositoryFactory.createQuestionRepository().save(
                new Question("What is java", "Hello I am new programmer", "bogdan"));
        repositoryFactory.createQuestionRepository().save(
                new Question("Hello I am new", "Is anybody here?", "verdes"));
        repositoryFactory.createAnswerRepository().save(
                new Answer("Java is a programming language",  "verdes", 0));
        repositoryFactory.createAnswerRepository().save(
                new Answer( "Ok thank you", "bogdan", 0));
        repositoryFactory.createAnswerRepository().save(
                new Answer( "Hello I am here",  "bogdan", 1));
        repositoryFactory.createTagRepository().save(new Tag("programming"));
        repositoryFactory.createTagRepository().save(new Tag("java"));
        repositoryFactory.createTagRepository().save(new Tag("general"));
        repositoryFactory.createQuestionTagRepository().save(
                new QuestionTag("java", 0));
        repositoryFactory.createQuestionTagRepository().save(
                new QuestionTag("programming", 0));
        repositoryFactory.createQuestionTagRepository().save(
                new QuestionTag("general", 1));

    }

    private Date generateDate(String date) throws ParseException {
        java.util.Date first = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        java.sql.Date last = new java.sql.Date(first.getTime());
        return last;
    }
}
