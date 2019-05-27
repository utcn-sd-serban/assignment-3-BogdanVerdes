package ro.utcn.sd.vba.a1.repository.api;

import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.VoteQuestion;

import java.sql.SQLException;
import java.util.List;

public interface VoteQuestionRepository {
    VoteQuestion save(VoteQuestion voteQuestion) throws SQLException;

    void remove(VoteQuestion voteQuestion) throws SQLException;

    List<VoteQuestion> findAll();

    int voteCount(Question question);
}