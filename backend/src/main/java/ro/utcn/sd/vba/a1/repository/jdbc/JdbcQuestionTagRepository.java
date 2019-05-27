package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.QuestionTag;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.QuestionTagRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcQuestionTagRepository implements QuestionTagRepository{
    private final JdbcTemplate template;

    @Override
    public QuestionTag save(QuestionTag questionTag) throws SQLException {
        if(questionTag.getId() !=0){
            update(questionTag);
        } else {
            int id = insert(questionTag);
            questionTag.setId(id);
        }
        return questionTag;
    }

    @Override
    public List<Question> findQuestionsByTag(String tag, List<Question> questionList) {
        return template.query("SELECT Question.* FROM Question INNER JOIN Tags_has_Question thq " +
                "ON Question.idQuestion = thq.Question_idQuestion " +
                "INNER JOIN Tag ON thq.Tags_Name = Tag.Name AND Tag.Name = ? ",new QuestionMapper(),tag);
    }

    @Override
    public List<Tag> findTagByQuestion(Question question, List<Tag> allTags) {
        return template.query("SELECT Tag.* FROM Tag INNER JOIN Tags_has_Question thq ON thq.Tags_Name = Tag.Name" +
                 "INNER JOIN Question ON Question.idQuestion = thq.Question_idQuestion AND Question.idQuestion = ? ",
                new TagMapper(),question.getId());
    }
    private Integer insert(QuestionTag questionTag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert((DataSource) template);
        insert.setTableName("Tags_has_Question");
        insert.usingGeneratedKeyColumns("idTagsQuestion");
        Map<String,Object> data = new HashMap<>();
        data.put("Tags_Name",questionTag.getNameTag());
        data.put("Question_idQuestion",questionTag.getIdQuestion());
        return insert.executeAndReturnKey(data).intValue();

    }

    private void update(QuestionTag questionTag) throws SQLException {
        template.update("UPDATE Tags_has_Question SET Tags_Name = ?, Question_idQuestion = ? WHERE idTagsQuestion = ?",
                questionTag.getNameTag(),
                questionTag.getIdQuestion(),
                questionTag.getId());
    }
}
