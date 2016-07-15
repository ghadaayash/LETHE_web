package com.lethe.form;

import com.lethe.ontology_handler.OntologyFile;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Set;

/**
 * Created by ghadahalghamdi on 30/06/2016.
 */

//This class will hold ontology item
public class UniformBackingObjects {

    private String filename;
    private Set<OWLEntity> owlEntities;
    private CommonsMultipartFile fileData;
    private OWLOntology owlOntology;
    private OWLEntity owlEntity;
    private List<String> selectedStr;
    private String forgettingMethod;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<OWLEntity> getOwlEntities() {
        return owlEntities;
    }

    public void setOwlEntities(Set<OWLEntity> owlEntities) {
        this.owlEntities = owlEntities;
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

    public OWLEntity getOwlEntity() {
        return owlEntity;
    }

    public void setOwlEntity(OWLEntity owlEntity) {
        this.owlEntity = owlEntity;
    }

    public List<String> getSelectedStr() {
        return selectedStr;
    }

    public void setSelectedStr(List<String> selectedStr) {
        this.selectedStr = selectedStr;
    }

    public String getForgettingMethod() {
        return forgettingMethod;
    }

    public void setForgettingMethod(String forgettingMethod) {
        this.forgettingMethod = forgettingMethod;
    }

    //private UniformBackingObjects uploadItem;



}
