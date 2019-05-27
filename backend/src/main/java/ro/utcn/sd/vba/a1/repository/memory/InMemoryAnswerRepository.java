package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.AnswerRepository;

import java.util.*;

@Component
public class InMemoryAnswerRepository implements AnswerRepository{
    private int currentId = 1;
    private final Map<Integer,Answer> data = new HashMap<>();

    @Override
    public synchronized Answer save(Answer answer) {
        if(answer.getId() != 0){
            data.put(answer.getId(),answer);
        }
        else{
            answer.setId(currentId++);
            data.put(answer.getId(),answer);
        }
        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Answer> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public synchronized void remove(Answer answer) {
        data.remove(answer.getId());
    }

    @Override
    public List<Answer> findAllFromQuestion(Question question){
        List<Answer> allAnswers = findAll();
        List<Answer> answers = new ArrayList<>();
        for(Answer a:allAnswers ){
            if(a.getIdq()==question.getId()){
                answers.add(a);
            }
        }
        return answers;
    }
}
