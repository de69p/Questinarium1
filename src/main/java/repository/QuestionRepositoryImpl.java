package repository;

import model.Question;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final Connection connection;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    String findById = """
                    select * from "questions" where id = ?
            """;
    String findByTopic = """
                    select * from questions where topic = ?
            """;
    String deleteById = """
                    DELETE FROM questions WHERE id = ?
            """;
    String save = """
                    INSERT INTO questions (text, topic) VALUES (?, ?)
            """;
    String update = """
                    UPDATE questions SET text = ?, topic = ? WHERE id = ?
            """;


    @Override
    public Question get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findByTopic);
            preparedStatement.setString(1, topic);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                questions.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .text(resultSet.getString("text"))
                        .topic(resultSet.getString("topic"))
                        .build()
                );
            }
            return questions;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Question with id " + id + " was deleted");
        } catch (SQLException ex) {
            throw new RuntimeException("Error deleting question", ex);
        }
    }



    @Override
    public void save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error saving question", ex);
        }
    }


    @Override
    public void update(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.setInt(3, question.getId());
            preparedStatement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}

