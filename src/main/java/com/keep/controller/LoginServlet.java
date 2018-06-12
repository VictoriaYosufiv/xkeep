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

@WebServlet(name = "LoginServlet", value = {"/login/*"}) //томкату каже коли викликатись
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        indView.print(response, "Login", indView.readHtmlFile("login-body"));


        UserDao userDao = new UserDao();
        String username = request.getParameter("username"); // прочиталось на сервері

        User user = userDao.findByUsername(username);
        System.out.println(user);

        if (user == null && username.length() > 0) {
            System.out.println("Такого користувача немає, ЗАРЕЄСТРУЙТЕСЬ");
            response.sendRedirect("/signup");

        } else {

            //check whether there is an input from a from
            boolean isEshos = (username != null && username.length() > 0);

            if (!isEshos) {
                System.out.println("Введіть логін та пароль");

            } else {
                boolean isLogin = user.loginCheck(username, request.getParameter("password"));
                if (!isLogin) {
                    System.out.println("не правильний пароль");

                    response.sendRedirect("/login");
                  //  request.setAttribute("no password", password);
                } else {
                    response.sendRedirect("/profile"); //
                System.out.println(user);                                }
            }
        }
    }



    }
