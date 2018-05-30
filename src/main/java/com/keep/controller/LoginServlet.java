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

@WebServlet(name = "LoginServlet", value = {"/login/*"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        String indexHtml = indView.setHTMLResources("index");

        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        User user = userDao.findByUsername(username);

        System.out.println(user);

        //check whether there is an input from a from
        if(username != null && username.length() > 0 ){
            boolean isLogin = user.loginCheck(username, request.getParameter("password"));
            if (! isLogin){
                response.sendRedirect("/error");
            } else {
                response.sendRedirect("/welcome");
            }
        }

        indView.print(response, "Login", indView.readHtmlFile("login"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IndexView indView = IndexView.getInstance();
        String indexHtml = indView.setHTMLResources("index");

        indView.print(response, "Login", indView.readHtmlFile("login"));
    }

    @Override
    public void init() throws ServletException {
        super.init();

        IndexView indView = IndexView.getInstance();
        indView.setPath(getServletContext().getRealPath("/html/"));
    }
}