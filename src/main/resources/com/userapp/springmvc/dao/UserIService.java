package com.userapp.springmvc.dao;

import com.userapp.springmvc.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserIService implements UserDAO{

    private JdbcTemplate jdbcTemplate;

    public UserIService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(User user) {
        String sql= "INSERT INTO \"users\" (\"username\",\"password\", \"rol\") values (?,?,?)";
        return jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getRol());
    }

    @Override
    public int update(User user) {
        String sql= "UPDATE \"users\" SET \"username\"=?, \"password\"=?, \"rol\"=? where \"id\"=?";
        return jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getRol(),user.getId());
    }

    @Override
    public User get(Integer id) {
        String sql="SELECT * FROM \"users\" where \"id\"="+id;
        ResultSetExtractor<User> extractor= new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {

                if(rs.next()){
                    String username= rs.getString("username");
                    Integer rol= rs.getInt("rol");

                    return new User(id,username,rol);
                }

                return null;
            }
        };
        return  jdbcTemplate.query(sql,extractor);
    }

    @Override
    public int delete(Integer id) {
        String sql="DELETE FROM \"users\" where \"id\"="+id;
        return jdbcTemplate.update(sql);
    }



    @Override
    public List<User> list() {
        String sql= "SELECT * FROM \"users\"";
        RowMapper<User> rowMapper= new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id= rs.getInt("id");
                String username=rs.getString("username");
                Integer rol= rs.getInt("rol");
                return new User(id,username,rol);
            }
        };
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public User verification(String username) {
        String sql="SELECT * FROM \"users\" where \"username\"='"+username+"'";
        ResultSetExtractor<User> extractor= new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {

                if(rs.next()){
                    Integer id= rs.getInt("id");
                    String username= rs.getString("username");
                    String password= rs.getString("password");
                    Integer rol= rs.getInt("rol");

                    return new User(id,username,password,rol);
                }

                return null;
            }
        };
        return  jdbcTemplate.query(sql,extractor);
    }
}
