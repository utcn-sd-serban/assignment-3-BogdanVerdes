package ro.utcn.sd.vba.a1.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Answer> findAnswersByQuestion(Question question){
        List<Answer> answers = repositoryFactory.createAnswerRepository().findAllFromQuestion(question);
        for(Answer a : answers){
            int score = repositoryFactory.createVoteAnswerRepository().voteCount(a);
            a.setScore(score);
        }
        answers.sort((a1,a2)-> -a1.getScore().compareTo(a2.getScore()));
        return answers;
    }

    @Transactional
    public Answer saveAnswer(Answer answer) throws SQLException {
        return repositoryFactory.createAnswerRepository().save(answer);
    }

    @Transactional
    public Optional<Answer> editAnswer(int answerId, String username, String answerBody){
        Optional<Answer> answerOptional = repositoryFactory.createAnswerRepository().findById(answerId);
        if(answerOptional.isPresent()){
            if(answerOptional.get().getAuthor().equals(username)){
                answerOptional.get().setText(answerBody);
            }
        }
        return answerOptional;
    }

    @Transactional
    public Optional<Answer> deleteAnswer(int answerId, String username) throws SQLException {
        Optional<Answer> answerOptional = repositoryFactory.createAnswerRepository().findById(answerId);
        if(answerOptional.isPresent()) {
            if (answerOptional.get().getAuthor().equals(username)) {
                repositoryFactory.createAnswerRepository().remove(answerOptional.get());
            }
        }
        return answerOptional;
    }

}
