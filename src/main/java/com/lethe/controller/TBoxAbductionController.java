package com.lethe.controller;

import com.lethe.form.AbductionBackingObjects;
import com.lethe.form.LogicalBackingObjects;
import com.lethe.form.UniformBackingObjects;
import com.lethe.lethe.TboxAbduction;
import com.lethe.ontology_handler.OntologyFile;
import com.lethe.ontology_handler.OntologyReader;
import org.semanticweb.owlapi.model.OWLAxiom;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.man.cs.lethe.abduction.Explanation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ghadahalghamdi on 19/07/2016.
 */

@Controller
@SessionAttributes({"owlEntitiestems","ss","b"})
public class TBoxAbductionController {

    //new
    BidirectionalShortFormProviderAdapter b;
    OntologyFile ss = new OntologyFile();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
    //-new

    @RequestMapping(value = "/tboxAbduction", method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new AbductionBackingObjects());
        return "TBoxAbduction";
    }

    @RequestMapping(value = "/tboxAbduction", method = RequestMethod.POST, params = "upload")
    public String uploadAndShowSignatures(AbductionBackingObjects abductionBackingObjects, BindingResult result,
                         HttpServletRequest request, HttpServletResponse response, HttpSession session,
                         ModelMap modelMap) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return "TBoxAbduction";
        }
        // Some type of file processing...
        System.err.println("-------------------------------------------");
        OntologyFile uploadOntology = new OntologyFile();
        MultipartFile file = abductionBackingObjects.getFileData();
        OWLOntology ontologyI = abductionBackingObjects.getOwlOntology();
        OWLOntology ontology = uploadOntology.uplodFile(file, ontologyI);
        Set<OWLOntology> ontologies = new HashSet<>();
        ontologies.add(ontology);
        b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);
        Set<OWLEntity> owlEntitySet;
        Set<OWLAxiom> axioms;
        //get entities (symbols)
        owlEntitySet = ontology.getSignature();
        //getAxioms
        axioms = ontology.getTBoxAxioms(false);
        abductionBackingObjects.setOwlEntities(owlEntitySet);
        abductionBackingObjects.setAxioms(axioms);
        String straxioms = axioms.toString();
        session.setAttribute("b",b);
        session.setAttribute("ss",ss);
        session.setAttribute("uploadFile", ontology);
        modelMap.addAttribute("owlEntitiestems", abductionBackingObjects.getOwlEntities());
        //modelMap.addAttribute("axioms", abductionBackingObjects.getAxioms());
        session.setAttribute("axioms", straxioms);
        session.removeAttribute("owlEntitiestems");
        // ..........................................
       return "TBoxAbduction";
    }

    @RequestMapping(value = "/tboxAbduction", method = RequestMethod.POST, params = "processForm")
    public String showAxioms(AbductionBackingObjects abductionBackingObjects, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session,
                              ModelMap modelMap) {
        OWLOntology ontology = (OWLOntology) session.getAttribute("uploadFile");
        //convert from string to axioms
        //this can be done through parsing the selected string to an owl axiom
        //method that take string as parameter and those string

        //GET SELECTED ENTITIES
        List<String> selectedEntity = abductionBackingObjects.getSelectedSig();
        BidirectionalShortFormProviderAdapter b = (BidirectionalShortFormProviderAdapter) session.getAttribute("b");
        OntologyFile ss = (OntologyFile) session.getAttribute("ss");
        Set<OWLEntity> entities = ss.entityForm(b,selectedEntity);
        //GET SELECTED AXIOMS
        List<String> selectedAxiom = abductionBackingObjects.getSelectedAx();
        //System.out.println("*-*-*-*-selected axioms " + selectedAxiom);
        OntologyReader reader = new OntologyReader();
        Set<OWLAxiom> axioms = reader.fromStringtoOWLAxiom(selectedAxiom);
        System.out.println("*-*-*-*-axioms " + axioms);
        TboxAbduction tboxAbduction = new TboxAbduction();
        Set<Explanation> explanations = tboxAbduction.useAbduction(ontology,entities,axioms);
        File savedExplination = reader.saveText(explanations.toString());
        session.setAttribute("downloadFile", savedExplination);
        System.out.println("*-*-*-*-explinations " + explanations);
        modelMap.addAttribute("explanations", explanations);

        return "TBoxAbduction";
    }

}
