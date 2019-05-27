package ro.utcn.sd.vba.a1.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return repositoryFactory.createTagRepository().save(tag);
    }
}
