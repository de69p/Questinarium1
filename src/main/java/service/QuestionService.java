package service;


import model.Question;
import repository.dao.QuestionRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {
    private final QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question setRndQuestionByTopic(String topic){
        List<Question> topics = questionRepository.getByTopic(topic);
        int randomNum = ThreadLocalRandom.current().nextInt(0, topics.size());
        return topics.get(randomNum);
    }

}
