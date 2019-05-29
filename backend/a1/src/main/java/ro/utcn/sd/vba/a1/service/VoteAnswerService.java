package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.entity.Answer;
import ro.utcn.sd.vba.a1.entity.VoteAnswer;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteAnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public void voteAnswer(int idAnswer, String username, boolean voteType) throws SQLException {
        Optional<Answer> answer = repositoryFactory.createAnswerRepository().findById(idAnswer);
        VoteAnswer voteAnswer = new VoteAnswer(idAnswer,username,voteType);
        if(!answer.isPresent()){
            throw new RuntimeException("Answer not found");
        }
        if(!answer.get().getAuthor().equals(username)){
            List<VoteAnswer> voteAnswers = repositoryFactory.createVoteAnswerRepository().findAll();
            for(VoteAnswer va:voteAnswers){
                if(va.getIdAnswer()==idAnswer&&va.getAuthor().equals(username)){
                    voteAnswer = va;
                    voteAnswer.setVoteType(voteType);
                }
            }
            repositoryFactory.createVoteAnswerRepository().save(voteAnswer);
        }
    }

    @Transactional
    public void removeVote(int idAnswer, String username) throws SQLException {
        Optional<Answer> answer = repositoryFactory.createAnswerRepository().findById(idAnswer);
        List<VoteAnswer> voteAnswers = repositoryFactory.createVoteAnswerRepository().findAll();
        if(!answer.isPresent()){
            throw new RuntimeException("Answer does not exist");
        }
        for(VoteAnswer va:voteAnswers){
            if(va.getIdAnswer()==idAnswer){
                repositoryFactory.createVoteAnswerRepository().remove(va);
                break;
            }

        }
    }
}
