package ro.utcn.sd.vba.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.*;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
@Order(-1)
public class Seed implements CommandLineRunner{
    private final RepositoryFactory repositoryFactory;

    @Override
    public void run(String... args) throws Exception {
            repositoryFactory.createUserRepository().save(
                    new User("bogdan","verdes98",0,"bogdanverdes@yahoo.com"));
            repositoryFactory.createUserRepository().save(
                    new User("verdes","bogdan98",0,"verdes@gmail.com"));
            repositoryFactory.createQuestionRepository().save(
                    new Question(1,"What is java","Hello I am new programmer",generateDate("25-03-2019"),"bogdan",0));
            repositoryFactory.createQuestionRepository().save(
                    new Question(2,"Hello I am new","Is anybody here?",generateDate("26-03-2019"),"verdes",0));
            repositoryFactory.createAnswerRepository().save(
                    new Answer(1,"Java is a programming language",generateDate("25-03-2019"),1,"verdes",0));
            repositoryFactory.createAnswerRepository().save(
                    new Answer(2,"Ok thank you",generateDate("26-03-2019"),1,"bogdan",0));
            repositoryFactory.createAnswerRepository().save(
                    new Answer(3,"Hello I am here",generateDate("28-03-2019"),2,"bogdan",0));
            repositoryFactory.createTagRepository().save(new Tag("programming"));
            repositoryFactory.createTagRepository().save(new Tag("java"));
            repositoryFactory.createTagRepository().save(new Tag("general"));
            repositoryFactory.createQuestionTagRepository().save(
                    new QuestionTag(1,"java",1));
            repositoryFactory.createQuestionTagRepository().save(
                    new QuestionTag(2,"programming",1));
            repositoryFactory.createQuestionTagRepository().save(
                    new QuestionTag(3,"general",2));

    }

    private Date generateDate(String date) throws ParseException {
        java.util.Date first = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        java.sql.Date last = new java.sql.Date(first.getTime());
        return last;
    }
}
