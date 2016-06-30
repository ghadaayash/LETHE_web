package com.lethe.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.lethe.form.UploadItem;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;

/**
 * Created by ghadahalghamdi on 30/06/2016.
 */
@Controller
public class UploadFileController {
    private String uploadFolderPath;
    ServletConfig config;

    public String getUploadFolderPath() {
        return uploadFolderPath;
    }

    public void setUploadFolderPath(String uploadFolderPath) {
        this.uploadFolderPath = uploadFolderPath;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new UploadItem());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(UploadItem uploadItem, BindingResult result,
                         HttpServletRequest request, HttpServletResponse response,
                         HttpSession session) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return "index";
        }

        // Some type of file processing...
        System.err.println("-------------------------------------------");
        try {
            //this for one file
            //MultipartFile file;
            MultipartFile file = uploadItem.getFileData();
            //OWLOntology ontology = uploadItem.getOwlOntology();
            String fileName = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                if (file.getSize() > 10000) {
                    System.out.println("File Size:::" + file.getSize());
                    return "/index";
                }
                System.out.println("size::" + file.getSize());
                fileName = request.getSession().getServletContext().getRealPath("") + "/upload/"
                        + file.getOriginalFilename();
               // OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
               // File convFile = new File(fileName);
               // file.transferTo(convFile);
               // ontology = manager.loadOntologyFromOntologyDocument(convFile.getAbsoluteFile());
                outputStream = new FileOutputStream(fileName);
                System.out.println("fileName:" + file.getOriginalFilename());

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }

            // ..........................................
            session.setAttribute("uploadFile", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/forms/uploadfileindex";
    }



    }
