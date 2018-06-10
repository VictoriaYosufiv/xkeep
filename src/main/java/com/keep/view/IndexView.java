package com.keep.view;

import com.keep.dao.entities.Note;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Create Html for index page
 */
public class IndexView {

    private String path;
    private String index;

    private static IndexView ourInstance = new IndexView();

    public static IndexView getInstance() {
        return ourInstance;
    }

    private IndexView() {
    }

    /**
     * Read Html file from folder html
     * @param filename
     * @return
     */
    public String readHtmlFile(String filename){
        if(this.path == null) {
            return "";
        }
        //буде містити вміст файлу
        StringBuilder strb = new StringBuilder();

        //формуємо об'єкт, що посилається на наш файл у папкці this.path
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        // br об'єкт що може читати файл
        try(BufferedReader br = Files.newBufferedReader(file, charset)){
            String line = null;

            // поки не кінець файлу читаємо по рядку
            //коли кінець файлу br.readLine() == null і умова для
            //продовження циклу перестає виконуватись
            while((line = br.readLine()) != null){

                //зчитує рядок із файлу і добавляє до strb
                strb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //після циклу strb містить вміст файлу,
        //що повертається як результат виконання методу
        return strb.toString();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        this.index = readHtmlFile("index");
    }

    public  void print(HttpServletResponse response, String title, String body) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(this.index.replace("###title###", title).replace("###body###", body));
    }

    public void printNote(HttpServletResponse response, String title, String body, List<Note> list) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        String table = "";

        if(list != null) {
            for (Note temp : list) {
                table += "<tr><td>" + temp.getTitle() + "</td><td>" + temp.getNote() + "</td><td>";
                table += "<a href='/note/edit/" + temp.getId() + "' class='btn btn-primary'>Edit</a> ";
                table += "<a href='/note/delete/" + temp.getId() + "' class='btn btn-danger'>Delete</a></td></tr>";
            }
        }

        out.println(this.index.replace("###title###", title).replace("###body###", body).replace("###TableList###", table));
    }

    public void printEditNote(HttpServletResponse response, String title, String body, Note note) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println(this.index.replace("###title###", title).replace("###body###", body)
                .replace("###NoteTitle###", note.getTitle()).replace("###Note###", note.getNote()));
    }

    /**
     *  Додаємо по потребі css і javascript файли до
     *  html
     * @param view тип вигляду
     * @return
     */
    public String setHTMLResources(String view){

        switch (view){
            case "index":
                return this.index.replace("###top-resources###", "")
                        .replace("###bottom-resources###", "");
            case "note":
                return this.index.replace("###top-resources###", "<link rel=\"stylesheet\" href=\"/rs/css/summernote-bs4.css\">")
                        .replace("###bottom-resources###", "<script src=\"/rs/js/summernote-bs4.js\"></script>");
        }

        return  null;
    }
}
