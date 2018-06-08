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

@WebServlet(name = "Signup", value = {"/signup"})
public class UserFormServlet extends HttpServlet {

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // витягти поля з параметра реквеста

       // IndexView indView = IndexView.getInstance();


    //порівняти чи вже є
        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        User user = userDao.findByUsername(username);
        System.out.println(user);
        //check whether there is an input from a from
        if (username != null) {
            response.sendRedirect("/ррр"); // користувач  вже існує ДОРОБИТИ
        } else {
            User newUser = new User();
            userDao.saveUser(newUser);





            response.sendRedirect("/"); // !!!!!!!!!!!!треба доробити

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();



        indView.print(response, "Sign-up", indView.readHtmlFile("sign-up"));
    }
}
