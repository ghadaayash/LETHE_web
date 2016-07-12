package com.lethe.controller;

import com.lethe.form.FormBackingObjects;
import com.lethe.lethe.UniformInterpolation;
import com.lethe.ontology_handler.OntologyFile;
import com.lethe.ontology_handler.OntologyReader;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
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
@SessionAttributes({"uploadFile", "owlEntitiestems", "resultedOntology", "ss", "b"})

public class EntitiesController {
    BidirectionalShortFormProviderAdapter b;
    File savedOntology;
    OntologyFile ss = new OntologyFile();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();

    @RequestMapping(value = "/selectedEntities", method = RequestMethod.POST, params="processForm")
    public String processEntities(@ModelAttribute("formBackingObjects") FormBackingObjects formBackingObjects, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response,
                                  ModelMap modelMap){

        List<String> selectedStr = formBackingObjects.getSelectedStr();
        //Set<OWLOntology> ontologies = new HashSet<>();
        OWLOntology ontology = (OWLOntology) modelMap.get("uploadFile");
        //ontologies.add(ontology);
        //new
        BidirectionalShortFormProviderAdapter b = (BidirectionalShortFormProviderAdapter) modelMap.get("b");
        Set<OWLEntity> entities = ss.entityForm(b,selectedStr);
        System.out.println("String type: " + selectedStr);
        System.out.println("Entity type: " + entities);
        System.out.println("------------------------");

        String selectedMethod = formBackingObjects.getForgettingMethod();
        System.out.println(selectedMethod);
        UniformInterpolation uniformInterpolation = new UniformInterpolation();
        if ("alchTBox".equals(selectedMethod)) {
            OWLOntology resultedOntology = uniformInterpolation.alchInterpolation(ontology, entities);
            OntologyReader test = new OntologyReader();
            savedOntology = test.saveOntology(resultedOntology);
            String content = test.readFile(savedOntology);

            modelMap.addAttribute("resultedOntology", content);
        } else if ("shqTbox".equals(selectedMethod)) {
            OWLOntology resultedOntology = uniformInterpolation.shqInterpolation(ontology, entities);
            OntologyReader test = new OntologyReader();
            savedOntology = test.saveOntology(resultedOntology);
            String content = test.readFile(savedOntology);

            modelMap.addAttribute("resultedOntology", content);
        } else if ("alcAbox".equals(selectedMethod)) {
            OWLOntology resultedOntology = uniformInterpolation.alcInterpolation(ontology, entities);
            OntologyReader test = new OntologyReader();
            savedOntology = test.saveOntology(resultedOntology);
            String content = test.readFile(savedOntology);

            modelMap.addAttribute("resultedOntology", content);
        }


        return "UniformInterpolation";
    }

    @RequestMapping(value = "/download.do", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        String filePath = savedOntology.getAbsolutePath();
        /// / get absolute path of the application
        ServletContext context = request.getSession().getServletContext();
        String appPath = context.getRealPath("");
        //System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        //String fullPath = appPath + filePath;
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
