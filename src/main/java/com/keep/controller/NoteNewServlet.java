package com.keep.controller;

import com.keep.dao.entities.Note;
import com.keep.dao.repository.NoteDao;
import com.keep.view.IndexView;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NoteNewServlet", value = {"/note/new/*"})
public class NoteNewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Note entity = new Note(0, request.getParameter("note"), userId, new java.sql.Date(new Date().getTime()).toString(), request.getParameter("title"));

        NoteDao dao = new NoteDao();

        dao.saveNote(entity);

        response.sendRedirect("/note");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();

        indView.print(response, "Create new note", indView.readHtmlFile("note-new-body"));
    }
}
