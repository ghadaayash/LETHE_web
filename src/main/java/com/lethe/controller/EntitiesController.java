package com.lethe.controller;

import com.lethe.form.UploadItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Ghada on 6/30/16.
 */

@Controller
@SessionAttributes({"uploadFile"})
public class EntitiesController {

  //  @RequestMapping(value="/entities", method = RequestMethod.GET)
    //model attribute here is the setters and getters of ontology. because the {"uploadFile"} is of type ontology
    //the owl ontology setters and getters is a class that differ from OWLOntology interface, but let's assume:


    /*public String showEntities(Model model){
          model.addAtrribute(new OWL_Ontology());
          return "index";
          }

    @RequestMapping(value="/entities", method = RequestMethod.POST)
     public String showEntities(@ModelAttribute OWL_Ontology owl
          OWLOntology ont = (OWLOntology) session.getAttribute("uploadFile");
          owl.setOntology(ont);
          ont = owl.getOntology();
    */


   // public String showEntities(, @ModelAttribute UploadItem uploadItem){

    //}
}
