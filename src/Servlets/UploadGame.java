package Servlets;

import WebUI.MultipleGamesManager;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "UploadGame", urlPatterns = {"/UploadGame"})
@MultipartConfig(location="/", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadGame extends HttpServlet {
    private static final String SUFFIX = ".xml";

    private void checkAndRedirectBySession(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            HttpSession session = request.getSession();
            if(session.getAttribute("PlayerName") == null){
                request.getRequestDispatcher("/Login/index.html").forward(request,response);
            }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkAndRedirectBySession(request,response);
        String LoadingErrorString = "";
        if(request.getParameter("gameName") != null){
            final Part filePart = request.getPart("gameFile");
            File gameFile = stream2File(filePart.getInputStream(), request.getSession().getAttribute("PlayerName").toString());
            String gameName = request.getParameter("gameName").toString();
            LoadingErrorString  = MultipleGamesManager.getInstance().AddNewGame(gameName ,gameFile);
        }
        else{
            LoadingErrorString  = "Please specify game name and select a file";
        }

        if(LoadingErrorString != null && LoadingErrorString.length() > 0){
            LoadingErrorString = LoadingErrorString.replace(System.getProperty("line.separator"),"<br>");
            request.setAttribute("LoadingError",LoadingErrorString);
            request.getRequestDispatcher("/GameLoad/ErrorLoading.jsp").forward(request,response);
        }
        else{
            request.getRequestDispatcher("/GameLoad/LoadSuccess.jsp").forward(request,response);
        }

    }

    private File stream2File(InputStream in, String userName) throws IOException{
        final File tmpFile = File.createTempFile(userName,SUFFIX);
        byte[] buffer = new byte[in.available()];
        in.read(buffer);
        OutputStream out = new FileOutputStream(tmpFile);
        out.write(buffer);
        return tmpFile;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
