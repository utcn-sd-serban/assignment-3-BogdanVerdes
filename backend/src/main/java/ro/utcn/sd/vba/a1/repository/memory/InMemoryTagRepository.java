package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.TagRepository;

import java.util.*;

@Component
public class InMemoryTagRepository implements TagRepository{
    private final Map<String,Tag> tags = new HashMap<>();
    private final Map<String,Question> questions = new HashMap<>();

    @Override
    public synchronized Tag save(Tag tag) {
        if(!tag.getName().equals("")){
            tags.put(tag.getName(),tag);
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(tags.values());
    }

}
