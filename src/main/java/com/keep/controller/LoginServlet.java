package com.keep.controller;

import com.keep.dao.entities.User;
import com.keep.dao.repository.UserDao;
import com.keep.view.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login/*"}) //томкату каже коли викликатись
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        String indexHtml = indView.setHTMLResources("index");

        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        User user = userDao.findByUsername(username);

        //check whether there is an input from a form
        if(username != null && username.length() > 0 ){
            boolean isLogin = user.loginCheck(username, request.getParameter("password"));
            if (! isLogin){
                response.sendRedirect("/error");
            } else {
                String userId = String.valueOf(user.getId());

                // cookies functionality
                // once user gets verified a cookie uploads to user's computer
                // subsequently, every time user tries to access /note page
                // servlet looks for the user id in the cookie
                // and displays only notes related to the user with the user id saved in the cookie

<<<<<<< HEAD
            } else {
                boolean isLogin = user.loginCheck(username, request.getParameter("password"));
                if (!isLogin) {
                    System.out.println("не правильний пароль");

                    response.sendRedirect("/login");
                  //  request.setAttribute("no password", password);
                } else {
                    response.sendRedirect("/profile"); //
                System.out.println(user);                                }
=======
                Cookie cookie = new Cookie("user_id", userId);
                response.addCookie(cookie);
                response.sendRedirect("/note");
>>>>>>> remotes/origin/marty
            }
        }


        //draft. left it dhere as it doesn't provide any functionality yet, however make sense

//        if (user == null && username.length() > 0) {
//            System.out.println("Такого користувача немає, ЗАРЕЄСТРУЙТЕСЬ");
//            response.sendRedirect("/signup");
//
//        } else {
//
//            //check whether there is an input from a from
//            boolean isEshos = (username != null && username.length() > 0);
//
//            if (!isEshos) {
//                System.out.println("Введіть логін та пароль");
//
//            } else {
//                boolean isLogin = user.loginCheck(username, request.getParameter("password"));
//                if (!isLogin) {
//                    System.out.println("не правильний пароль");
//                    response.sendRedirect("/login");
//
//                } else {
//                    response.sendRedirect("/note"); // !!!!!!!!!!!!треба доробити
//                    System.out.println(user);                                }
//            }
//        }

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
