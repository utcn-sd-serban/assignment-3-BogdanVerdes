package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.VoteAnswer;
import ro.utcn.sd.vba.a1.repository.api.VoteAnswerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryVoteAnswerRepository implements VoteAnswerRepository{
    private int currentId = 1;
    private final Map<Integer,VoteAnswer> data = new HashMap<>();
    @Override
    public synchronized VoteAnswer save(VoteAnswer voteAnswer) {
        if(voteAnswer.getId() != 0){
            data.put(voteAnswer.getId(),voteAnswer);
        }
        else{
            voteAnswer.setId(currentId++);
            data.put(voteAnswer.getId(),voteAnswer);
        }
        return voteAnswer;
    }

    @Override
    public synchronized void remove(VoteAnswer voteAnswer) {
        data.remove(voteAnswer.getId());
    }

    @Override
    public List<VoteAnswer> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public int voteCount(Answer answer) {
        int votes = 0;
        for(VoteAnswer va : data.values()){
            if(va.getIdAnswer()==answer.getId()&&va.isVoteType()){
                votes++;
            }
            if(va.getIdAnswer()==answer.getId()&&!va.isVoteType()){
                votes--;
            }
        }
        return votes;
    }
}
