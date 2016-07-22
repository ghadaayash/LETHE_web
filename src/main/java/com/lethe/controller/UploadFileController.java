package com.lethe.controller;

import com.lethe.form.UniformBackingObjects;
import com.lethe.ontology_handler.OntologyFile;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
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
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ghadahalghamdi on 30/06/2016.
 */
@Controller
public class UploadFileController {

    //new
    BidirectionalShortFormProviderAdapter b;
    OntologyFile ss = new OntologyFile();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
    //-new

    private String uploadFolderPath;
    ServletConfig config;

    public String getUploadFolderPath() {
        return uploadFolderPath;
    }

    public void setUploadFolderPath(String uploadFolderPath) {
        this.uploadFolderPath = uploadFolderPath;
    }

    @RequestMapping(value = "/uniformInterpolation", method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new UniformBackingObjects());
        return "UniformInterpolation";
    }

    @RequestMapping(value = "/uniformInterpolation", method = RequestMethod.POST, params = "upload")
    public String create(UniformBackingObjects uniformBackingObjects, BindingResult result,
                         HttpServletRequest request, HttpServletResponse response, HttpSession session,
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
        MultipartFile file = uniformBackingObjects.getFileData();
        OWLOntology ontologyI = uniformBackingObjects.getOwlOntology();
        OWLOntology ontology = uploadOntology.uplodFile(file, ontologyI);
        Set<OWLOntology> ontologies = new HashSet<>();
        ontologies.add(ontology);
        b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);
        Set<OWLEntity> owlEntitySet;
        owlEntitySet = ontology.getSignature();
        uniformBackingObjects.setOwlEntities(owlEntitySet);
        session.setAttribute("b",b);
        session.setAttribute("ss",ss);
        session.setAttribute("uploadFile", ontology);
        modelMap.addAttribute("owlEntitiestems", uniformBackingObjects.getOwlEntities());
        //session.removeAttribute("owlEntitiestems");
            // ..........................................
        //session.removeAttribute("ss");
        //session.removeAttribute("b");

        return "UniformInterpolation";
    }

    }
