package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public Question saveQuestion(Question question) throws SQLException {
        return repositoryFactory.createQuestionRepository().save(question);
    }

    @Transactional
    public Question findById(int id) throws SQLException {
        Optional<Question> questionOptional = repositoryFactory.createQuestionRepository().findById(id);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            int score = repositoryFactory.createVoteQuestionRepository().voteCount(question);
            question.setScore(score);
            return repositoryFactory.createQuestionRepository().save(question);
        }
        else return new Question();
    }

    @Transactional
    public List<Question> findAllQuestions(){
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        for(Question q : questions){
            int score = repositoryFactory.createVoteQuestionRepository().voteCount(q);
            q.setScore(score);
        }
        questions.sort((c1,c2) -> -c1.getCreation().compareTo(c2.getCreation()));
        return questions;
    }

    @Transactional
    public List<Question> findByTitle(String title){
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        List<Question> result = new ArrayList<>();
        for(Question q: questions){
            int score = repositoryFactory.createVoteQuestionRepository().voteCount(q);
            q.setScore(score);
            if(q.getTitle().equals(title)){
                result.add(q);
            }
        }
        result.sort((c1,c2) -> -c1.getCreation().compareTo(c2.getCreation()));
        return result;
    }
}
