package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.VoteAnswer;
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
        if(!answer.isPresent()){
            throw new RuntimeException("Answer not found");
        }
        if(!answer.get().getAuthor().equals(username)){
            List<VoteAnswer> voteAnswers = repositoryFactory.createVoteAnswerRepository().findAll();
            boolean ok = true;
            for(VoteAnswer va:voteAnswers){
                if(va.getIdAnswer()==idAnswer&&va.getAuthor().equals(username)){
                    if(!va.isVoteType()==voteType){
                        va.setVoteType(voteType);
                    }
                    else ok = false;
                }
            }
            if(ok){
                repositoryFactory.createVoteAnswerRepository().save(new VoteAnswer(idAnswer,username,voteType));
            }

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
