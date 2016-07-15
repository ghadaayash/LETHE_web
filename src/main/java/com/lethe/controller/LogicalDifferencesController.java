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
import java.util.Set;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * Created by ghadahalghamdi on 12/07/2016.
 */

@Controller
public class LogicalDifferencesController {

    //new
    BidirectionalShortFormProviderAdapter b;
    OntologyFile ss = new OntologyFile();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
    File newEntailements;
    //-new

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new LogicalBackingObjects());
        return "LogicalDifferences";
    }

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.POST, params = "firstUpload")
    public String uploadFirstFile(LogicalBackingObjects logicalBackingObjects, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                  ModelMap modelMap) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return "LogicalDifferences";
        }

        System.err.println("-------------------------------------------");
        OntologyFile uploadOntology = new OntologyFile();
        MultipartFile file = logicalBackingObjects.getFirstFileData();
        OWLOntology ontologyI = logicalBackingObjects.getFirstOwlOntology();
        OWLOntology ontology = uploadOntology.uplodFile(file, ontologyI);
        //Set<OWLOntology> ontologies = new HashSet<>();
        //ontologies.add(ontology);
        //b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);
        //Set<OWLEntity> owlEntitySet;
        //owlEntitySet = ontology.getSignature();
        //logicalBackingObjects.setOwlEntities(owlEntitySet);
        //session.setAttribute("b",b);
        //session.setAttribute("ss",ss);
        session.setAttribute("firstUploadFile", ontology);
        //modelMap.addAttribute("owlEntitiestems", logicalBackingObjects.getOwlEntities());

        return "LogicalDifferences";
    }

    @RequestMapping(value = "/logicalDifferences", method = RequestMethod.POST, params = "secondUpload")
    public String uploadSecondFile(LogicalBackingObjects logicalBackingObjects, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                  ModelMap modelMap) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }
            return "LogicalDifferences";
        }

        System.err.println("-------------------------------------------");
        OntologyFile uploadOntology = new OntologyFile();
        MultipartFile file = logicalBackingObjects.getSecondFileData();
        OWLOntology ontologyI = logicalBackingObjects.getSecondOwlOntology();
        OWLOntology ontology = uploadOntology.uplodFile(file, ontologyI);
        //Set<OWLOntology> ontologies = new HashSet<>();
        //ontologies.add(ontology);
        //b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);
        //Set<OWLEntity> owlEntitySet;
        //owlEntitySet = ontology.getSignature();
        //logicalBackingObjects.setOwlEntities(owlEntitySet);
        //session.setAttribute("b",b);
        //session.setAttribute("ss",ss);
        session.setAttribute("secondUploadFile", ontology);
        //modelMap.addAttribute("owlEntitiestems", logicalBackingObjects.getOwlEntities());

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

        LogicalDifferences logicalDifferences = new LogicalDifferences();
        if("alchTBox".equals(forgettingMethod)){
            Set<OWLLogicalAxiom> axioms = logicalDifferences.computeDiff_coms_alch(firstOntology, secondOntology, approximationLevel);
            OntologyReader reader = new OntologyReader();
            newEntailements = reader.saveAxioms(axioms);
            String content = reader.readFile(newEntailements);
            modelMap.addAttribute("resultedAxioms", content);

        }else if ("shqTbox".equals(forgettingMethod)){
            Set<OWLLogicalAxiom> axioms = logicalDifferences.computeDiff_coms_shq(firstOntology, secondOntology, approximationLevel);
            OntologyReader reader = new OntologyReader();
            newEntailements = reader.saveAxioms(axioms);
            String content = reader.readFile(newEntailements);
            modelMap.addAttribute("resultedAxioms", content);

        }else if ("alcAbox".equals(forgettingMethod)){
            Set<OWLLogicalAxiom> axioms = logicalDifferences.computeDiff_coms_alc(firstOntology, secondOntology, approximationLevel);
            OntologyReader reader = new OntologyReader();
            newEntailements = reader.saveAxioms(axioms);
            String content = reader.readFile(newEntailements);
            modelMap.addAttribute("resultedAxioms", content);
        }
        return "LogicalDifferences";
    }

    @RequestMapping(value = "/downloadEntailments.do", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        String filePath = newEntailements.getAbsolutePath();
        ServletContext context = request.getSession().getServletContext();
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

    }

}
