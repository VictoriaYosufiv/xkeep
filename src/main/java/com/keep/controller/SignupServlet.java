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

@WebServlet(name = "Signup", value = {"/sign-up"})
public class SignupServlet extends HttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

                // витягти поля з параметра реквеста
        UserDao userDao = new UserDao();
        String username = request.getParameter("username");


        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String role = request.getParameter("role");

        System.out.println("зтягнутий username " + username);

        //порівняти чи вже є
        User user = userDao.findByUsername(username);

        System.out.println("user з порівнянння " + user);
        //check whether there is an input from a from
        //if(user != null) {
        //            response.sendRedirect("/error1");
        //        } else if(password.length() < 6 || !password.equals(confirm_password)) {
        //            response.sendRedirect("/error2");


        //  if (user != null && username.length() > 0) {


        if (user != null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setStatus(status);
            newUser.setRole(role);

            userDao.saveUser(newUser);

            System.out.println("редагований юзер " + newUser);

            response.sendRedirect("/login"); // користувач  вже існує
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setName(name);
            newUser.setStatus(status);
            newUser.setRole(role);

            userDao.saveUser(newUser);

            System.out.println("новий юзер " + newUser);

            response.sendRedirect("/login");

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        indView.print(response, "Sign-up", indView.readHtmlFile("sign-up"));


    }
}