package com.keep.dao.repository;

import com.keep.dao.entities.User;

import java.sql.*;

public class UserDao {


    /**
     * Find a user
     * @param username
     * @return User or null
     */
    public User findByUsername(String username) {

        DataSource ds = new DataSource();
        try (
                Connection con = ds.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT usr.id, usr.username, usr.name, " +
                        " usr.password, usr.role, usr.status " +
                    "FROM user usr WHERE usr.username = '" + username + "'");
        ) {
            if(rs.next()){
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getString("role")
                );

                return user;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void saveUser(User user){
        DataSource ds = new DataSource();

        String sql;
        if(user.getId() == 0L){

            sql = "INSERT INTO user (username, password, name, status, role)  " +
                    "VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE user SET username=?, password=?, name=?, status=?, role=? " +
                    " WHERE id = " + user.getId();
        }

        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
        ){
                stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getStatus());
            stmt.setString(5, user.getRole());

            stmt.execute();
        } catch(SQLException e){
        e.printStackTrace();
    }
    }
}
