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

@WebServlet(name = "NoteEditServlet", value = {"/note/edit/*"})
public class NoteEditServlet extends HttpServlet {

    private NoteDao _dao;

    public NoteEditServlet() {
        _dao = new NoteDao();
    }

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

        long id;

        try {
            id = Long.parseLong(request.getPathInfo().replace("/", ""));
        } catch (NumberFormatException e) {
            response.sendRedirect("/note");
            return;
        }

        Note entity = _dao.getNote(id);

        if(entity == null) {
            System.out.println("Not found note with id " + id);
            response.sendRedirect("/note");
            return;
        }

        if(entity.getUser_id() != userId) {
            System.out.println("Access denied");
            response.sendRedirect("/note");
            return;
        }

        entity.setTitle(request.getParameter("title"));
        entity.setNote(request.getParameter("note"));

        _dao.updateNote(entity);

        response.sendRedirect("/note");
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

        long id;

        try {
            id = Long.parseLong(request.getPathInfo().replace("/", ""));
        } catch (NumberFormatException e) {
            response.sendRedirect("/note");
            return;
        }

        Note entity = _dao.getNote(id);

        if(entity == null) {
            System.out.println("Not found note with id " + id);
            response.sendRedirect("/note");
            return;
        }

        if(entity.getUser_id() != userId) {
            System.out.println("Access denied");
            response.sendRedirect("/note");
            return;
        }

        indView.printEditNote(response, "Edit note", indView.readHtmlFile("note-edit-body"), entity);
    }
}
