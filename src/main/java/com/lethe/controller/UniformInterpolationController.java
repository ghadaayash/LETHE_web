package com.lethe.controller;

import com.lethe.form.TestClass;
import com.lethe.form.UniformBackingObjects;
import com.lethe.lethe.UniformInterpolation;
import com.lethe.ontology_handler.OntologyFile;
import com.lethe.ontology_handler.OntologyReader;
import de.uni_stuttgart.vis.vowl.owl2vowl.Owl2Vowl;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
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
    OWLOntology resultedOntology;
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
            resultedOntology = uniformInterpolation.alchInterpolation(ontology, entities);

        } else if ("shqTbox".equals(selectedMethod)) {
            resultedOntology = uniformInterpolation.shqInterpolation(ontology, entities);

        } else if ("alcAbox".equals(selectedMethod)) {
            resultedOntology = uniformInterpolation.alcInterpolation(ontology, entities);
        }

        OntologyReader test = new OntologyReader();
        savedOntology = test.saveOntology(resultedOntology);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        try {
            OWLOntology newontology = manager.loadOntologyFromOntologyDocument(savedOntology.getAbsoluteFile());
            IRI iri = manager.getOntologyDocumentIRI(newontology);
            System.out.println("\n______" + iri.toString());
            Owl2Vowl owl2Vowl = new Owl2Vowl(newontology, iri.toString());
            String s = owl2Vowl.getJsonAsString();
            System.out.println("______" + s);
            modelMap.addAttribute("jsonText", s);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        session.setAttribute("downloadFile", savedOntology);
        String content = test.readFile(savedOntology);

        modelMap.addAttribute("resultedOntology", content);
        session.removeAttribute("uploadFile");
        //session.removeAttribute("ss");
        //session.removeAttribute("b");

        return "UniformInterpolation";
    }

}
