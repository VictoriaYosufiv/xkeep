package com.keep.dao.repository;

import com.keep.dao.entities.Note;
import com.keep.dao.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Notes Repository
 */
public class NoteDao {


    //search notes using query

    public List<Note> getSearchNotes(long userid, String request){
        DataSource ds = new DataSource();

        //request to the database

        String query = "SELECT n.id, n.note, n.user_id, n.title, n.createdDate FROM note n WHERE n.user_id = ? AND (LOWER(n.title) LIKE '%" + request.toLowerCase() + "%' OR LOWER(n.note) LIKE '%" + request.toLowerCase() + "%')";

        try {
            Connection con = ds.getConnection();

            //search request

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, String.valueOf(userid));

            ResultSet rs = stmt.executeQuery();

            //search results to collection

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

            //return search results to method's output

            return notes;

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //method to get all the notes

    public Note getNote(long id){
        DataSource ds = new DataSource();
        try (
                Connection con = ds.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT n.id, n.note, n.user_id, " +
                        " n.title, n.createdDate " +
                        "FROM note n WHERE n.id = " + id);
        ) {
            Note entity = null;

            if(rs.next()){
                entity = new Note(
                        rs.getLong("id"),
                        rs.getString("note"),
                        rs.getLong("user_id"),
                        rs.getString("createdDate"),
                        rs.getString("title")
                );
            }

            return entity;

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //method to get all the user's notes

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

    //notes editing functionality

    public void updateNote(Note entity) {
        DataSource ds = new DataSource();
        String query = "UPDATE note SET title = ?, note = ? WHERE id = " + entity.getId();

        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(query);
        ) {
            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getNote());

            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //delete note from the database

    public void deleteNote(long id) {
        DataSource ds = new DataSource();
        String query = "DELETE FROM note WHERE id = " + id;

        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(query);
        ) {
            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //create new note

    public void saveNote(Note entity) {
        DataSource ds = new DataSource();
        String query = "INSERT INTO note(title, note, createdDate, user_id) VALUES (?, ?, ?, ?)";

        try (
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
        ) {
            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getNote());
            stmt.setString(3, entity.getCreatedDate());
            stmt.setString(4, String.valueOf(entity.getUser_id()));

            stmt.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
