package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.VoteQuestion;
import ro.utcn.sd.vba.a1.repository.api.VoteQuestionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryVoteQuestionRepository implements VoteQuestionRepository {
    private int currentId = 1;
    private final Map<Integer,VoteQuestion> data = new HashMap<>();
    @Override
    public synchronized VoteQuestion save(VoteQuestion voteQuestion) {
        if(voteQuestion.getId() != 0){
            data.put(voteQuestion.getId(),voteQuestion);
        }
        else{
            voteQuestion.setId(currentId++);
            data.put(voteQuestion.getId(),voteQuestion);
        }
        return voteQuestion;
    }

    @Override
    public List<VoteQuestion> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public synchronized void remove(VoteQuestion voteQuestion) {
        data.remove(voteQuestion.getId());
    }

    @Override
    public int voteCount(Question question) {
        int votes = 0;
        for(VoteQuestion vq : data.values()){
            if(vq.getIdQuestion()==question.getId()&&vq.isVoteType()){
                votes++;
            }
            if(vq.getIdQuestion()==question.getId()&&!vq.isVoteType()){
                votes--;
            }
        }
        return votes;
    }
}
