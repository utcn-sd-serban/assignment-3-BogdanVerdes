package ro.utcn.sd.vba.a1.repository.api;

import ro.utcn.sd.vba.a1.model.Answer;
import ro.utcn.sd.vba.a1.model.VoteAnswer;

import java.sql.SQLException;
import java.util.List;

public interface VoteAnswerRepository {
    VoteAnswer save(VoteAnswer voteAnswer) throws SQLException;

    void remove(VoteAnswer voteAnswer) throws SQLException;

    List<VoteAnswer> findAll();

    int voteCount(Answer answer);
}
