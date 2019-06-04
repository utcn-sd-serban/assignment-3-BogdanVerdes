package ro.utcn.sd.vba.a1.repository.api;

import ro.utcn.sd.vba.a1.entity.Question;
import ro.utcn.sd.vba.a1.entity.QuestionTag;
import ro.utcn.sd.vba.a1.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface QuestionTagRepository {
    QuestionTag save(QuestionTag questionTag) throws SQLException;
    List<Question> findQuestionsByTag(String tag,List<Question> questionList);
    List<Tag> findTagByQuestion(Question question,List<Tag> allTags);
    List<QuestionTag> findAll();
}
