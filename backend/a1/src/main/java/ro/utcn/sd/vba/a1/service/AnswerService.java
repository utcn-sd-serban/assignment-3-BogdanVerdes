package ro.utcn.sd.vba.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.a1.dto.AnswerDTO;
import ro.utcn.sd.vba.a1.entity.Answer;
import ro.utcn.sd.vba.a1.entity.Question;
import ro.utcn.sd.vba.a1.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<AnswerDTO> findAnswersByQuestion(Question question) {
        List<Answer> answers = repositoryFactory.createAnswerRepository().findAllFromQuestion(question);
        for (Answer a : answers) {
            int score = repositoryFactory.createVoteAnswerRepository().voteCount(a);
            a.setScore(score);
        }
        answers.sort((a1, a2) -> -a1.getScore().compareTo(a2.getScore()));
        return answers.stream().map(AnswerDTO::ofEntity).collect(Collectors.toList());
    }

    @Transactional
    public AnswerDTO saveAnswer(AnswerDTO answerDTO) throws Exception {
        Answer answer = new Answer(answerDTO.getText(),
                answerDTO.getAuthor(),
                answerDTO.getIdq());

        Optional<Question> question = repositoryFactory.createQuestionRepository().findById(answer.getId());
        if (question.isPresent()) {
            AnswerDTO output = AnswerDTO.ofEntity(repositoryFactory.createAnswerRepository().save(answer));
            return output;
        } else {
            throw new Exception("Question not found");
        }
    }

    @Transactional
    public AnswerDTO editAnswer(int answerId, String username, String answerBody) throws SQLException {
        Optional<Answer> answerOptional = repositoryFactory.createAnswerRepository().findById(answerId);
        if (answerOptional.isPresent()) {
            if (answerOptional.get().getAuthor().equals(username)) {
                answerOptional.get().setText(answerBody);
                repositoryFactory.createAnswerRepository().save(answerOptional.get());
            }
        }
        return AnswerDTO.ofEntity(repositoryFactory.createAnswerRepository().save(answerOptional.get()));
    }

    @Transactional
    public AnswerDTO deleteAnswer(int answerId, String username) throws SQLException {
        Optional<Answer> answerOptional = repositoryFactory.createAnswerRepository().findById(answerId);
        if (answerOptional.isPresent()) {
            if (answerOptional.get().getAuthor().equals(username)) {
                repositoryFactory.createAnswerRepository().remove(answerOptional.get());
            }
        }
        return AnswerDTO.ofEntity(repositoryFactory.createAnswerRepository().save(answerOptional.get()));
    }

}
