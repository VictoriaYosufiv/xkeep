package com.keep.controller;

import com.keep.model.User;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IndexView indView = IndexView.getInstance();

        switch (request.getPathInfo()){
            case "/about":
                indView.print(response, "About", indView.readHtmlFile("about"));
                break;
            case "/login":
                User user = new User("igor@lyutak.com", "1122", "Igor");
                String username = request.getParameter("username");
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
                break;

            case "/profile":
                indView.print(response, "Profile", indView.readHtmlFile("about"));
                break;
            case "/signup":
                indView.print(response, "Signup", indView.readHtmlFile("about"));
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
