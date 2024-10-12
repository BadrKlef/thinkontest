package com.thinkontest.demo.repository;

import com.thinkontest.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("phoneNumber")
            );
        }
    };

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
    }

    public int save(User user) {
        String sql = "INSERT INTO users (username, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }

    public int update(Long id, User user) {
        String sql = "UPDATE users SET username = ?, firstName = ?, lastName = ?, email = ?, phoneNumber = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), id);
    }

    public int delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
