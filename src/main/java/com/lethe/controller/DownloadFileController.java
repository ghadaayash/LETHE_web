package com.lethe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by ghadahalghamdi on 14/07/2016.
 */
public class DownloadFileController {

   /* @RequestMapping(value = "/download.do", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response, File file) {
try{
        String filePath = file.getAbsolutePath();
        /// / get absolute path of the application
        ServletContext context = request.getSession().getServletContext();
        //String appPath = context.getRealPath("");
        //System.out.println("appPath = " + appPath);

        //make a method that gives the file that we want to download as a parameter, and also takes servletContext,

        // construct the complete absolute path of the file
        //String fullPath = appPath + filePath;
        String fullPath = filePath;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();}catch (IOException e){e.getMessage();}

    }*/
}
