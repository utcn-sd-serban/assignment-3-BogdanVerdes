package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.repository.api.QuestionRepository;

import java.util.*;
@Component
public class InMemoryQuestionRepository implements QuestionRepository{
    private int currentId = 1;
    private final Map<Integer,Question> data = new HashMap<>();

    @Override
    public synchronized Question save(Question question) {
        if(question.getId() != 0){
            data.put(question.getId(),question);
        }
        else{
            question.setId(currentId++);
            data.put(question.getId(),question);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public synchronized void remove(Question question) {
        data.remove(question.getId());
    }
}
