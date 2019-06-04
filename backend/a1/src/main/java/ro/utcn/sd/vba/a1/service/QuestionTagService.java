package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;
import ro.utcn.sd.vba.a1.dto.QuestionTagDTO;
import ro.utcn.sd.vba.a1.entity.Question;
import ro.utcn.sd.vba.a1.entity.QuestionTag;
import ro.utcn.sd.vba.a1.entity.Tag;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionTagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<QuestionDTO> findQuestionsByTag(Tag tag){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = repositoryFactory.createQuestionTagRepository().
                findQuestionsByTag(tag.getName(),repositoryFactory.createQuestionRepository().findAll());
        for(Question q : questions){
            QuestionDTO qDTO = QuestionDTO.ofEntity(q);
            List<Tag> tags = findTagsByQuestion(q);
            StringBuilder tagString = new StringBuilder();
            for (Tag t : tags) {
                tagString.append(t.getName()).append(",");
            }
            qDTO.setTags(tagString.toString().substring(0, tagString.toString().length() - 1));
            if(!questionDTOList.contains(qDTO))
                questionDTOList.add(qDTO);
        }
        return questionDTOList;
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
