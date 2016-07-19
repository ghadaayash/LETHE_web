package com.lethe.controller;

import com.lethe.form.TestClass;
import com.lethe.form.UniformBackingObjects;
import com.lethe.lethe.UniformInterpolation;
import com.lethe.ontology_handler.OntologyFile;
import com.lethe.ontology_handler.OntologyReader;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by Ghada on 6/30/16.
 */

@Controller
@SessionAttributes({"owlEntitiestems","ss","b"})

public class UniformInterpolationController {

    File savedOntology;
    //OntologyFile ss = new OntologyFile();
    //ShortFormProvider shortFormProvider = new SimpleShortFormProvider();

    @RequestMapping(value = "/uniformInterpolation", method = RequestMethod.POST, params="processForm")
    public String processEntities(@ModelAttribute("uniformBackingObjects") UniformBackingObjects uniformBackingObjects, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response,
                                  ModelMap modelMap, HttpSession session){

        List<String> selectedStr = uniformBackingObjects.getSelectedStr();
        //Set<OWLOntology> ontologies = new HashSet<>();
        OWLOntology ontology = (OWLOntology) session.getAttribute("uploadFile");
        //ontologies.add(ontology);
        //new
        BidirectionalShortFormProviderAdapter b = (BidirectionalShortFormProviderAdapter) session.getAttribute("b");
        OntologyFile ss = (OntologyFile) session.getAttribute("ss");
        Set<OWLEntity> entities = ss.entityForm(b,selectedStr);
        System.out.println("String type: " + selectedStr);
        System.out.println("Entity type: " + entities);
        System.out.println("------------------------");

        String selectedMethod = uniformBackingObjects.getForgettingMethod();
        System.out.println(selectedMethod);
        UniformInterpolation uniformInterpolation = new UniformInterpolation();
        if ("alchTBox".equals(selectedMethod)) {
            OWLOntology resultedOntology = uniformInterpolation.alchInterpolation(ontology, entities);
            //for-testingVOWL
            modelMap.addAttribute("ontology", resultedOntology);
            //
            OntologyReader test = new OntologyReader();
            savedOntology = test.saveOntology(resultedOntology);
            session.setAttribute("downloadFile", savedOntology);
            String content = test.readFile(savedOntology);

            modelMap.addAttribute("resultedOntology", content);
        } else if ("shqTbox".equals(selectedMethod)) {
            OWLOntology resultedOntology = uniformInterpolation.shqInterpolation(ontology, entities);
            //for-testingVOWL
            session.setAttribute("ontology", resultedOntology);
            //
            OntologyReader test = new OntologyReader();
            savedOntology = test.saveOntology(resultedOntology);
            session.setAttribute("downloadFile", savedOntology);
            String content = test.readFile(savedOntology);

            modelMap.addAttribute("resultedOntology", content);
        } else if ("alcAbox".equals(selectedMethod)) {
            OWLOntology resultedOntology = uniformInterpolation.alcInterpolation(ontology, entities);
            //for-testingVOWL
            modelMap.addAttribute("ontology", resultedOntology);
            //
            OntologyReader test = new OntologyReader();
            savedOntology = test.saveOntology(resultedOntology);
            session.setAttribute("downloadFile", savedOntology);
            String content = test.readFile(savedOntology);

            modelMap.addAttribute("resultedOntology", content);
        }

        session.removeAttribute("uploadFile");
        //session.removeAttribute("ss");
        //session.removeAttribute("b");

        return "UniformInterpolation";
    }

}
