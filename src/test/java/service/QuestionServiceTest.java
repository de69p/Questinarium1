package service;

import org.junit.Before;
import org.junit.Test;
import repository.QuestionRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionServiceTest {
    private final String user = "postgres";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url,user,"postgres");
    }
    @Test
    public void getRndQuestionByTopicTest (){
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(connection));
        System.out.println(questionService.setRndQuestionByTopic("ENCAPSULATION"));

    }
}

