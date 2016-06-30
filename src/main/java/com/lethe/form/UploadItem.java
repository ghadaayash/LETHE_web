package com.lethe.form;

import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by ghadahalghamdi on 30/06/2016.
 */

//This class will hold ontology item
public class UploadItem {
    private String filename;
    private CommonsMultipartFile fileData;
    private OWLOntology owlOntology;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public OWLOntology getOwlOntology() {
        return owlOntology;
    }

    public void setOwlOntology(OWLOntology owlOntology) {
        this.owlOntology = owlOntology;
    }
}
