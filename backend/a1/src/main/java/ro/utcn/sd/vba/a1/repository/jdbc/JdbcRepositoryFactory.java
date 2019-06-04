package ro.utcn.sd.vba.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.a1.repository.api.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty (name = "a1.repository-type",havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory{
    private final JdbcQuestionRepository questionRepository;
    private final JdbcAnswerRepository answerRepository;
    private final JdbcUserRepository userRepository;
    private final JdbcTagRepository tagRepository;
    private final JdbcQuestionTagRepository questionTagRepository;
    private final JdbcVoteAnswerRepository voteAnswerRepository;
    private final JdbcVoteQuestionRepository voteQuestionRepository;

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepository;
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return answerRepository;
    }

    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }

    @Override
    public TagRepository createTagRepository() {
        return tagRepository;
    }

    @Override
    public QuestionTagRepository createQuestionTagRepository() {
        return questionTagRepository;
    }

    @Override
    public VoteAnswerRepository createVoteAnswerRepository() {
        return voteAnswerRepository;
    }

    @Override
    public VoteQuestionRepository createVoteQuestionRepository() {
        return voteQuestionRepository;
    }

}
