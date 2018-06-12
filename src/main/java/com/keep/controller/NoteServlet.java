package com.keep.controller;

import com.keep.dao.entities.Note;
import com.keep.dao.repository.NoteDao;
import com.keep.view.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NoteServlet", value = {"/note"})
public class NoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        Cookie[] cookies = request.getCookies();

        long userId = 0;

        if(cookies != null && cookies.length > 0) {
            for (Cookie temp : cookies) {
                if (temp.getName().equals("user_id")) {
                    userId = Long.parseLong(temp.getValue());
                }
            }
        }

        NoteDao dao = new NoteDao();

        List<Note> list = dao.getNotesByUserID(userId);

        indView.printNote(response, "Note", indView.readHtmlFile("note-body"), list);
    }
}
