package ro.utcn.sd.vba.a1.repository.api;

import ro.utcn.sd.vba.a1.model.Tag;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag) throws SQLException;

    List<Tag> findAll();

}
