package com.keep.controller;

import com.keep.dao.entities.User;
import com.keep.dao.repository.UserDao;
import com.keep.view.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", value = {"/*"})
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
// request - запит, response - відповідь, throws - кидає
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IndexView indView = IndexView.getInstance();

        switch (request.getPathInfo()){
            case "/about":
                indView.print(response, "About", indView.readHtmlFile("about"));
                break;
            case "/profile":
                indView.print(response, "Profile", indView.readHtmlFile("user-form"));
                break;
            case "/signup":
                indView.print(response, "Signup", indView.readHtmlFile("user-form"));
                break;
            case "/welcome":
                indView.print(response, "Welcome", indView.readHtmlFile("about"));
                break;
            case "/error":
                indView.print(response, "Error", indView.readHtmlFile("error"));
                break;
            case "/":
                indView.print(response, "Keep", indView.readHtmlFile("index-body"));
                break;
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

        IndexView indView = IndexView.getInstance();
        indView.setPath(getServletContext().getRealPath("/html/"));
    }
}
