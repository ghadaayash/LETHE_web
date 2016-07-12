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
public class FormBackingObjects {
    private String filename;
    private Set<OWLEntity> owlEntities;
    private CommonsMultipartFile fileData;
    private OWLOntology owlOntology;
    private OWLEntity owlEntity;
    private List<String> selectedStr;
    private String owlLabel;
    private String forgettingMethod;
    //private FormBackingObjects uploadItem;



    /*public FormBackingObjects uploadItem(String s){
        return uploadItem;
    }*/

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

    public Set<OWLEntity> getOwlEntities() {
        return owlEntities;
    }

    public void setOwlEntities(Set<OWLEntity> owlEntities) {
        this.owlEntities = owlEntities;
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

    public String getOwlLabel() {
        return owlLabel;
    }

    public void setOwlLabel(String owlLabel) {
        OntologyFile s = new OntologyFile();
        BidirectionalShortFormProviderAdapter b = s.haveB();
        owlLabel = s.shortForm(b,getOwlEntity());
        this.owlLabel = owlLabel;
    }

    public String getForgettingMethod() {
        return forgettingMethod;
    }

    public void setForgettingMethod(String forgettingMethod) {
        this.forgettingMethod = forgettingMethod;
    }

    //public String owlL(OWLEntity entity){
        //return entity.getIRI().getFragment();
    //}


}
