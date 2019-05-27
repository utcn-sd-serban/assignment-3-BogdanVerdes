package ro.utcn.sd.vba.a1.repository.api;

import ro.utcn.sd.vba.a1.model.Question;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    Question save(Question question) throws SQLException;

    Optional<Question> findById(int id);

    List<Question> findAll();

    void remove(Question question) throws SQLException;
}
