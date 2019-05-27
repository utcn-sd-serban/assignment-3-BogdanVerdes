package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.QuestionTag;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionTagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Question> findQuestionsByTag(Tag tag){
        return repositoryFactory.createQuestionTagRepository().
                findQuestionsByTag(tag.getName(),repositoryFactory.createQuestionRepository().findAll());
    }

    @Transactional
    public List<Tag> findTagsByQuestion(Question question){
        return repositoryFactory.createQuestionTagRepository().
                findTagByQuestion(question,repositoryFactory.createTagRepository().findAll());
    }

    @Transactional
    public QuestionTag saveQuestionTag(QuestionTag questionTag) throws SQLException {
        return repositoryFactory.createQuestionTagRepository().save(questionTag);
    }
}
