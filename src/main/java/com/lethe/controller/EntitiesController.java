package com.lethe.controller;

import com.lethe.form.UploadItem;
import com.lethe.OntologyHandler.OntologyFile;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Ghada on 6/30/16.
 */

@Controller
@SessionAttributes({"uploadFile", "owlEntitiestems", "savedStr", "ss", "b"})
//search of the concept of second session attribute
public class EntitiesController {
    BidirectionalShortFormProviderAdapter b;

    OntologyFile ss = new OntologyFile();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();


    @RequestMapping(value = "/entities", method = RequestMethod.POST, params = "showEntities")
    public String showEntities(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response,
                               ModelMap modelMap, HttpSession session) {
        Set<OWLOntology> ontologies = new HashSet<>();
        OWLOntology ontology = (OWLOntology) modelMap.get("uploadFile");
        ontologies.add(ontology);
        b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);
        Set<OWLEntity> owlEntitySet;
        owlEntitySet = ontology.getSignature();
        uploadItem.setOwlEntities(owlEntitySet);
        session.setAttribute("b",b);
        session.setAttribute("ss",ss);
        modelMap.addAttribute("owlEntitiestems", uploadItem.getOwlEntities());
        return "index";
    }

    @RequestMapping(value = "/selectedEntities", method = RequestMethod.POST, params="selectEntities")
    public String processEntities(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response,
                                  ModelMap modelMap){
        String selectedStr = uploadItem.getSelectedStr();
        Set<OWLOntology> ontologies = new HashSet<>();
        OWLOntology ontology = (OWLOntology) modelMap.get("uploadFile");
        ontologies.add(ontology);
        String [] strings = selectedStr.split(",");
        for (int i = 0; i<strings.length;i++){
            String splitted = strings[i];
            OWLEntity entity = ss.entityForm(b,splitted);
            System.out.println("String type: " + splitted);
            System.out.println("Entity type: " + entity);
            System.out.println("------------------------");
        }
        modelMap.addAttribute("savedStr", selectedStr);

        return "index";
    }
}
