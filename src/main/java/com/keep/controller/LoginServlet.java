package com.keep.controller;

import com.keep.dao.entities.User;
import com.keep.dao.repository.UserDao;
import com.keep.view.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login/*"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        String indexHtml = indView.setHTMLResources("index");

        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        User user = userDao.findByUsername(username);

        //check whether there is an input from a from
        if(username != null && username.length() > 0 ){
            boolean isLogin = user.loginCheck(username, request.getParameter("password"));
            if (! isLogin){
                response.sendRedirect("/error");
            } else {
                String userId = String.valueOf(user.getId());
                Cookie cookie = new Cookie("user_id", userId);
                response.addCookie(cookie);
                response.sendRedirect("/note");
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
