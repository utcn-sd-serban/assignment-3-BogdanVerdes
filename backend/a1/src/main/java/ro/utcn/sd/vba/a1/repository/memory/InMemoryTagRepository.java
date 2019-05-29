package ro.utcn.sd.vba.a1.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.entity.Tag;
import ro.utcn.sd.vba.a1.repository.api.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryTagRepository implements TagRepository {
    private final List<Tag> tags = new ArrayList<>();

    @Override
    public synchronized Tag save(Tag tag) {
        boolean exists = false;
        for (Tag t : tags) {
            if (t.getName().equals(tag.getName()))
                exists = true;
        }
        if (!exists)
            tags.add(tag);
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(tags);
    }

    public Optional<Tag> findByName(String tagName) {
        for (Tag t : tags) {
            if (t.getName().equals(tagName))
                return Optional.of(t);
        }
        return Optional.empty();
    }

}
