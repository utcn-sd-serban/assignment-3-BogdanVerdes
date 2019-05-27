package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;
import ro.utcn.sd.vba.a1.dto.QuestionTagDTO;
import ro.utcn.sd.vba.a1.model.Question;
import ro.utcn.sd.vba.a1.model.QuestionTag;
import ro.utcn.sd.vba.a1.model.Tag;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionTagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<QuestionDTO> findQuestionsByTag(Tag tag){
        return repositoryFactory.createQuestionTagRepository().
                findQuestionsByTag(tag.getName(),repositoryFactory.createQuestionRepository().findAll())
                .stream().map(QuestionDTO::ofEntity).collect(Collectors.toList());
    }

    @Transactional
    public List<Tag> findTagsByQuestion(Question question){
        return repositoryFactory.createQuestionTagRepository().
                findTagByQuestion(question,repositoryFactory.createTagRepository().findAll());
    }

    @Transactional
    public QuestionTagDTO saveQuestionTag(QuestionTag questionTag) throws SQLException {
        return QuestionTagDTO.ofEntity(repositoryFactory.createQuestionTagRepository().save(questionTag));
    }
}
