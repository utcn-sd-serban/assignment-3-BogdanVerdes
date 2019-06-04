package ro.utcn.sd.vba.a1.repository.api;

public interface RepositoryFactory {
    QuestionRepository createQuestionRepository();
    AnswerRepository createAnswerRepository();
    UserRepository createUserRepository();
    TagRepository createTagRepository();
    QuestionTagRepository createQuestionTagRepository();
    VoteAnswerRepository createVoteAnswerRepository();
    VoteQuestionRepository createVoteQuestionRepository();
}
