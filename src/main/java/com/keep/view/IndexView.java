package com.keep.view;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        StringBuilder strb = new StringBuilder();
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        try(BufferedReader br = Files.newBufferedReader(file, charset)){
            String line = null;
            while((line = br.readLine()) != null){
                strb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
