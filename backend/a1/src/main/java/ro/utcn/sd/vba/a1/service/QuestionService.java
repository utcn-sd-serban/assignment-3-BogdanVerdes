package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.dto.QuestionDTO;
import ro.utcn.sd.vba.a1.event.CreateQuestionEvent;
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
public class QuestionService {
    private final RepositoryFactory repositoryFactory;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public QuestionDTO saveQuestion(QuestionDTO questionDTO) throws SQLException {
        Question question = new Question(questionDTO.getTitle(),questionDTO.getBody(),questionDTO.getUsername());
        String tags = questionDTO.getTags();
        QuestionDTO qDTO = QuestionDTO.ofEntity(repositoryFactory.createQuestionRepository().save(question));
        String[] tagList = tags.split(",");
        for (String aTagList : tagList) {
            QuestionTag questionTag = new QuestionTag(aTagList, questionDTO.getId());
            repositoryFactory.createQuestionTagRepository().save(questionTag);
        }
        qDTO.setTags(tags);
        applicationEventPublisher.publishEvent(new CreateQuestionEvent(qDTO));
        return qDTO;
    }

    @Transactional
    public QuestionDTO findById(int id) throws SQLException {
        Optional<Question> questionOptional = repositoryFactory.createQuestionRepository().findById(id);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            int score = repositoryFactory.createVoteQuestionRepository().voteCount(question);
            question.setScore(score);
            return QuestionDTO.ofEntity(repositoryFactory.createQuestionRepository().save(question));
        }
        else return new QuestionDTO();
    }

    @Transactional
    public List<QuestionDTO> findAllQuestions(){
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        List<Tag> tags = repositoryFactory.createTagRepository().findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question q : questions){
            int score = repositoryFactory.createVoteQuestionRepository().voteCount(q);
            q.setScore(score);
            List<Tag> questionTags = repositoryFactory.createQuestionTagRepository().findTagByQuestion(q,tags);
            StringBuilder tagString= new StringBuilder();
            for(Tag t : questionTags){
                tagString.append(t.getName()).append(",");
            }
            QuestionDTO qDTO = QuestionDTO.ofEntity(q);
            qDTO.setTags(tagString.toString().substring(0,tagString.toString().length()-1));
            questionDTOList.add(qDTO);
        }
        questionDTOList.sort((c1,c2) -> -c1.getCreation().compareTo(c2.getCreation()));
        return questionDTOList;
    }

    @Transactional
    public List<QuestionDTO> findByTitle(String title){
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        List<Question> result = new ArrayList<>();
        for(Question q: questions){
            int score = repositoryFactory.createVoteQuestionRepository().voteCount(q);
            q.setScore(score);
            if(q.getTitle().equals(title)){
                result.add(q);
            }
        }
        result.sort((c1,c2) -> -c1.getCreation().compareTo(c2.getCreation()));
        return result.stream().map(QuestionDTO::ofEntity).collect(Collectors.toList());
    }
}
