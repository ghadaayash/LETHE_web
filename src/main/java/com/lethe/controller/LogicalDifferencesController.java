package com.lethe.controller;

import com.lethe.form.LogicalBackingObjects;
import com.lethe.lethe.LogicalDifferences;
import com.lethe.ontology_handler.OntologyFile;
import com.lethe.ontology_handler.OntologyReader;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by ghadahalghamdi on 12/07/2016.
 */

@Controller
@SessionAttributes({"firstUploadFile","secondUploadFile","logicalDifferencesOption"})
public class LogicalDifferencesController {

    //new
    BidirectionalShortFormProviderAdapter b;
    OntologyFile ss = new OntologyFile();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
    File newEntailements;
    OntologyFile uploadOntology = new OntologyFile();
    //-new

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new LogicalBackingObjects());
        return "LogicalDifferences";
    }

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.POST, params = "Upload")
    public String uploadOntologies(LogicalBackingObjects logicalBackingObjects, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                  ModelMap modelMap) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return "LogicalDifferences";
        }
        //FIRST MAKE THE USER UPLOAD ALL THE ONTOLOGIES,
        // THEN MAKE HIM or HER CHOOSE WHICH ONTOLOGY TO GET THE SIGNATURES FROM

        System.err.println("-------------------------------------------");

        MultipartFile firstFileDatafile = logicalBackingObjects.getFirstFileData();
        MultipartFile secondFileDatafile = logicalBackingObjects.getSecondFileData();
        OWLOntology ontologyI = logicalBackingObjects.getFirstOwlOntology();
        OWLOntology firstOntology = uploadOntology.uplodFile(firstFileDatafile, ontologyI);
        OWLOntology ontologyI2 = logicalBackingObjects.getSecondOwlOntology();
        OWLOntology secondOntology = uploadOntology.uplodFile(secondFileDatafile, ontologyI2);
        session.setAttribute("firstUploadFile", firstOntology);
        session.setAttribute("secondUploadFile", secondOntology);
        System.out.println("------------first ontology: " + firstOntology);
        System.out.println("------------second ontology: " + secondOntology);

        return "LogicalDifferences";
    }

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.POST, params = "showSignatures")
    public String showSignatures(LogicalBackingObjects logicalBackingObjects, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                 ModelMap modelMap){
        OWLOntology firstOntology = (OWLOntology) session.getAttribute("firstUploadFile");
        OWLOntology secondOntology = (OWLOntology) session.getAttribute("secondUploadFile");
        System.out.println("--------" + firstOntology);
        System.out.println("--------" + secondOntology);
        String logicalDifferencesOption = logicalBackingObjects.getLogicalDifferencesOption();
        session.setAttribute("logicalDifferencesOption", logicalDifferencesOption);
        if ("specificSig".equals(logicalDifferencesOption)) {
            String signaturesOption = logicalBackingObjects.getSignaturesOption();
            if ("firstOntologysig".equals(signaturesOption)) {
                Set<OWLOntology> firstontologies = new HashSet<>();
                firstontologies.add(firstOntology);
                b = new BidirectionalShortFormProviderAdapter(firstontologies, shortFormProvider);
                Set<OWLEntity> owlEntitySet;
                owlEntitySet = firstOntology.getSignature();
                logicalBackingObjects.setOwlEntities(owlEntitySet);
                session.setAttribute("b", b);
                session.setAttribute("ss", ss);
                session.setAttribute("firstUploadFile", firstOntology);
                modelMap.addAttribute("owlEntitiestems", logicalBackingObjects.getOwlEntities());
            } else if ("secondOntologysig".equals(signaturesOption)) {
                Set<OWLOntology> secondontologies = new HashSet<>();
                secondontologies.add(secondOntology);
                b = new BidirectionalShortFormProviderAdapter(secondontologies, shortFormProvider);
                Set<OWLEntity> owlEntitySet;
                owlEntitySet = secondOntology.getSignature();
                logicalBackingObjects.setOwlEntities(owlEntitySet);
                session.setAttribute("b", b);
                session.setAttribute("ss", ss);
                session.setAttribute("secondUploadFile", secondOntology);
                modelMap.addAttribute("owlEntitiestems", logicalBackingObjects.getOwlEntities());

            }

        }

        return "LogicalDifferences";
    }

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.POST, params = "processForm")
    public String processForm(LogicalBackingObjects logicalBackingObjects, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session,
                              ModelMap modelMap){

        OWLOntology firstOntology = (OWLOntology) session.getAttribute("firstUploadFile");
        OWLOntology secondOntology = (OWLOntology) session.getAttribute("secondUploadFile");

        int approximationLevel = logicalBackingObjects.getApproximationLevel();
        String forgettingMethod = logicalBackingObjects.getForgettingMethod();
        System.out.println(forgettingMethod);


        //prepare everything,  if the user selected strings from signatures box:

        Set<OWLLogicalAxiom> axioms = new HashSet<>();
        LogicalDifferences logicalDifferences = new LogicalDifferences();
        String logicalDifferencesOption = (String) session.getAttribute("logicalDifferencesOption");
        System.out.println("----------" + logicalDifferencesOption);
        if("alchTBox".equals(forgettingMethod)){
           if("commonSig".equals(logicalDifferencesOption)){
               System.out.println("----------" + logicalDifferencesOption);
               axioms = logicalDifferences.computeDiff_coms_alch(firstOntology, secondOntology, approximationLevel);
           }else{
               List<String> selectedStr = logicalBackingObjects.getSelectedStr();
               BidirectionalShortFormProviderAdapter b = (BidirectionalShortFormProviderAdapter) session.getAttribute("b");
               OntologyFile ss = (OntologyFile) session.getAttribute("ss");
               Set<OWLEntity> entities = ss.entityForm(b,selectedStr);
               System.out.println("String type: " + selectedStr);
               System.out.println("Entity type: " + entities);
               System.out.println("------------------------");
               System.out.println(selectedStr);
               axioms = logicalDifferences.computeDiff_s_alch(firstOntology, secondOntology, entities, approximationLevel);
           }

        }else if ("shqTbox".equals(forgettingMethod)){
            if("commonSig".equals(logicalDifferencesOption)){
                axioms = logicalDifferences.computeDiff_coms_shq(firstOntology, secondOntology, approximationLevel);
            }else{
                List<String> selectedStr = logicalBackingObjects.getSelectedStr();
                BidirectionalShortFormProviderAdapter b = (BidirectionalShortFormProviderAdapter) session.getAttribute("b");
                OntologyFile ss = (OntologyFile) session.getAttribute("ss");
                Set<OWLEntity> entities = ss.entityForm(b,selectedStr);
                System.out.println("String type: " + selectedStr);
                System.out.println("Entity type: " + entities);
                System.out.println("------------------------");
                axioms = logicalDifferences.computeDiff_s_shq(firstOntology, secondOntology, entities, approximationLevel);
            }

        }else if ("alcAbox".equals(forgettingMethod)){
            if("commonSig".equals(logicalDifferencesOption)){
                axioms = logicalDifferences.computeDiff_coms_alc(firstOntology, secondOntology, approximationLevel);
            }else{
                List<String> selectedStr = logicalBackingObjects.getSelectedStr();
                BidirectionalShortFormProviderAdapter b = (BidirectionalShortFormProviderAdapter) session.getAttribute("b");
                OntologyFile ss = (OntologyFile) session.getAttribute("ss");
                Set<OWLEntity> entities = ss.entityForm(b,selectedStr);
                System.out.println("String type: " + selectedStr);
                System.out.println("Entity type: " + entities);
                System.out.println("------------------------");
                axioms = logicalDifferences.computeDiff_s_alc(firstOntology, secondOntology, entities, approximationLevel);
            }
        }

        OntologyReader reader = new OntologyReader();
        newEntailements = reader.saveAxioms(axioms);
        session.setAttribute("downloadFile", newEntailements);
        String content = reader.readFile(newEntailements);
        modelMap.addAttribute("resultedAxioms", content);
        return "LogicalDifferences";
    }
}
