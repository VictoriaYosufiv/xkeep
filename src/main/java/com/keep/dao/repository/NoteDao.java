package com.keep.dao.repository;

import com.keep.dao.entities.Note;
import com.keep.dao.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Notes Repository
 */
public class NoteDao {

    public List<Note> getNotesByUserID(long userid){
        DataSource ds = new DataSource();
        try (
                Connection con = ds.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT n.id, n.note, n.user_id, " +
                        " n.title, n.createdDate " +
                        "FROM note n WHERE n.user_id = " + userid);
        ) {
            List<Note> notes = new ArrayList<>();
            while(rs.next()){
                Note nt = new Note(
                        rs.getLong("id"),
                        rs.getString("note"),
                        rs.getLong("user_id"),
                        rs.getString("createdDate"),
                        rs.getString("title")
                );
                notes.add(nt);
            }

            return notes;

        } catch(SQLException e){
            e.printStackTrace();
        }

       return null;
    }
}
