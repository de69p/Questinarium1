package repository;

import model.Question;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionRepositoryImplTest {
    private final String user = "postgres";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url,user,"");
    }
    @Test
    public void getTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        System.out.println(questionRepository.get(2));
    }

    @Test
    public void getByTopicTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        System.out.println(questionRepository.getByTopic("OOP"));
    }

    @Test
    public void deleteTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        questionRepository.delete(7);
    }

    @Test
    public void saveTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        questionRepository.save(Question.builder().id(10).text("test saved text").topic("Save").build());
    }

    @Test
    public void updateTest(){
        QuestionRepositoryImpl questionRepository = new QuestionRepositoryImpl(connection);
        questionRepository.update(Question.builder().id(10).text("UPDATE saved text").topic("UPDATE").build());
    }



}
