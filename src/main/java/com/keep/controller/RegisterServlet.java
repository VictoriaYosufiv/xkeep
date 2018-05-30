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

@WebServlet(name = "RegisterServlet", value = {"/register/*"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        String indexHtml = indView.setHTMLResources("index");

        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String name = request.getParameter("name");

        User user = userDao.findByUsername(username);



        if(user != null) {
            response.sendRedirect("/error1");
        } else if(password.length() < 6 || !password.equals(confirm_password)) {
            response.sendRedirect("/error2");
        } else {
            User newuser = new User();

            newuser.setUsername(username);
            newuser.setRole("user");
            newuser.setPassword(password);
            newuser.setName(name);
            newuser.setStatus("active");

            userDao.saveUser(newuser);

            response.sendRedirect("/welcome");
        }

        indView.print(response, "Registration", indView.readHtmlFile("register"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IndexView indView = IndexView.getInstance();
        String indexHtml = indView.setHTMLResources("index");

        indView.print(response, "Registration", indView.readHtmlFile("register"));
    }

    @Override
    public void init() throws ServletException {
        super.init();

        IndexView indView = IndexView.getInstance();
        indView.setPath(getServletContext().getRealPath("/html/"));
    }
}
