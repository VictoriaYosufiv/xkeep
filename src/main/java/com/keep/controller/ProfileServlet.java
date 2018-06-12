package com.keep.controller;

import com.keep.view.IndexView;
import sun.java2d.cmm.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexView indView = IndexView.getInstance();
        indView.printresources(response, "Profile", indView.readHtmlFile("profile"), indView.readHtmlFile("top"));






    }
}
