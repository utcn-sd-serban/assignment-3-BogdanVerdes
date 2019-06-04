package ro.utcn.sd.vba.a1.repository.api;


import ro.utcn.sd.vba.a1.entity.Answer;
import ro.utcn.sd.vba.a1.entity.Question;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Answer save(Answer answer) throws SQLException;

    Optional<Answer> findById(int id);

    List<Answer> findAll();

    void remove(Answer answer) throws SQLException;

    List<Answer> findAllFromQuestion(Question question);

}
