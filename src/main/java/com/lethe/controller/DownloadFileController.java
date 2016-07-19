package com.lethe.controller;

import com.lethe.form.TestClass;
import com.lethe.form.UniformBackingObjects;
import de.uni_stuttgart.vis.vowl.owl2vowl.Owl2Vowl;
import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by ghadahalghamdi on 14/07/2016.
 */
@Controller

public class DownloadFileController {


    @RequestMapping(value = "/download.do", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) {
        try {

            File file = (File)session.getAttribute("downloadFile");
            //System.out.println("---See,chick,FILE---" + file.getAbsolutePath());
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
            outStream.close();
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
