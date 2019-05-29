package ro.utcn.sd.vba.a1.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.entity.Tag;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> findAllTags(){
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public Tag addTag(Tag tag) throws SQLException {
        List<Tag> tags = repositoryFactory.createTagRepository().findAll();
        boolean ok = true;
        for(Tag t: tags){
            if(t.getName().equals(tag.getName())){
                ok = false;
            }
        }
        if(ok) {
            return repositoryFactory.createTagRepository().save(tag);
        }
        else return tag;
    }
}
