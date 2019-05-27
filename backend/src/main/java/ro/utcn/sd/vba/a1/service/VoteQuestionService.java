package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.VoteQuestion;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteQuestionService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public void voteQuestion(int idQuestion, String username, boolean voteType) throws SQLException {
        Optional<Question> Question = repositoryFactory.createQuestionRepository().findById(idQuestion);
        if(!Question.isPresent()){
            throw new RuntimeException("Question not found");
        }
        if(!Question.get().getUsername().equals(username)){
            List<VoteQuestion> voteQuestions = repositoryFactory.createVoteQuestionRepository().findAll();
            boolean ok = true;
            for(VoteQuestion vq:voteQuestions){
                if(vq.getIdQuestion()==idQuestion&&vq.getAuthor().equals(username)){
                    if(!vq.isVoteType()==voteType){
                        vq.setVoteType(voteType);
                    }
                    else ok = false;
                }
            }
            if(ok){
                repositoryFactory.createVoteQuestionRepository().save(new VoteQuestion(idQuestion,username,voteType));
            }

        }
    }

    @Transactional
    public void removeVote(int idQuestion, String username) throws SQLException {
        Optional<Question> question = repositoryFactory.createQuestionRepository().findById(idQuestion);
        List<VoteQuestion> voteQuestions = repositoryFactory.createVoteQuestionRepository().findAll();
        if(!question.isPresent()){
            throw new RuntimeException("Question does not exist");
        }
        for(VoteQuestion vq:voteQuestions){
            if(vq.getIdQuestion()==idQuestion){
                repositoryFactory.createVoteQuestionRepository().remove(vq);
                break;
            }

        }
    }
}
