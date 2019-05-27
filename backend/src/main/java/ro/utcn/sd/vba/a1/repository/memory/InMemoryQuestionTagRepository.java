package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.QuestionTag;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.QuestionTagRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryQuestionTagRepository implements QuestionTagRepository{
    Map<Integer,QuestionTag> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public QuestionTag save(QuestionTag questionTag) {
        if(questionTag.getId()==0){
            questionTag.setId(currentId++);
            data.put(questionTag.getId(),questionTag);
        }
        else{
            data.put(questionTag.getId(),questionTag);
        }
        return questionTag;
    }

    @Override
    public List<Question> findQuestionsByTag(String tag, List<Question> questionList) {
        List<Question> result = new ArrayList<>();
        for(QuestionTag qt: data.values()){
            if(qt.getNameTag().equals(tag)){
                for(Question q: questionList){
                    if(q.getId()==qt.getIdQuestion())
                        result.add(q);
                }

            }
        }
        return result;
    }
    @Override
    public List<Tag> findTagByQuestion(Question question, List<Tag> allTags){
        List<Tag> tags = new ArrayList<>();
        for(Tag t:allTags){
            for(QuestionTag qt: data.values()){
                if(t.getName().equals(qt.getNameTag())){
                    tags.add(t);
                }
            }
        }
        return tags;
    }
}
