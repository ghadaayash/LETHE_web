package com.lethe.controller;

import com.lethe.form.FormBackingObjects;
import com.lethe.ontology_handler.OntologyFile;
import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ghadahalghamdi on 30/06/2016.
 */
@Controller
@SessionAttributes({"uploadFile"})
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
        model.addAttribute(new FormBackingObjects());
        return "UniformInterpolation";
    }

    @RequestMapping(method = RequestMethod.POST, params = "upload")
    public String create(@ModelAttribute("formBackingObjects") FormBackingObjects formBackingObjects, BindingResult result,
                         HttpServletRequest request, HttpServletResponse response,
                         ModelMap modelMap) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return "UniformInterpolation";
        }

        // Some type of file processing...
        System.err.println("-------------------------------------------");
        OntologyFile uploadOntology = new OntologyFile();
        MultipartFile file = formBackingObjects.getFileData();
        OWLOntology ontologyI = formBackingObjects.getOwlOntology();
        OWLOntology ontology = uploadOntology.uplodFile(file, ontologyI);

            // ..........................................
            modelMap.addAttribute("uploadFile", ontology);
        return "UniformInterpolation";
    }

    }
