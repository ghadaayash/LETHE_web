package com.lethe.form;

import com.lethe.ontology_handler.OntologyFile;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Set;

/**
 * Created by ghadahalghamdi on 13/07/2016.
 */
public class LogicalBackingObjects {

    private String firstFilename;
    private String secondFilename;
    private Set<OWLEntity> owlEntities;
    private CommonsMultipartFile firstFileData;
    private CommonsMultipartFile secondFileData;
    private int approximationLevel;
    private OWLOntology firstOwlOntology;
    private OWLOntology secondOwlOntology;
    private OWLEntity owlEntity;
    private List<String> selectedStr;
    private String forgettingMethod;
    private String logicalDifferencesOption;
    private String signaturesOption;
    private String newFilename;
    private CommonsMultipartFile newFileData;
    private OWLOntology newOwlOntology;


    public String getFirstFilename() {
        return firstFilename;
    }

    public void setFirstFilename(String firstFilename) {
        this.firstFilename = firstFilename;
    }

    public String getSecondFilename() {
        return secondFilename;
    }

    public void setSecondFilename(String secondFilename) {
        this.secondFilename = secondFilename;
    }

    public Set<OWLEntity> getOwlEntities() {
        return owlEntities;
    }

    public void setOwlEntities(Set<OWLEntity> owlEntities) {
        this.owlEntities = owlEntities;
    }

    public CommonsMultipartFile getFirstFileData() {
        return firstFileData;
    }

    public void setFirstFileData(CommonsMultipartFile firstFileData) {
        this.firstFileData = firstFileData;
    }

    public CommonsMultipartFile getSecondFileData() {
        return secondFileData;
    }

    public void setSecondFileData(CommonsMultipartFile secondFileData) {
        this.secondFileData = secondFileData;
    }

    public int getApproximationLevel() {
        return approximationLevel;
    }

    public void setApproximationLevel(int approximationLevel) {
        this.approximationLevel = approximationLevel;
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

    public OWLOntology getFirstOwlOntology() {
        return firstOwlOntology;
    }

    public void setFirstOwlOntology(OWLOntology firstOwlOntology) {
        this.firstOwlOntology = firstOwlOntology;
    }

    public OWLOntology getSecondOwlOntology() {
        return secondOwlOntology;
    }

    public void setSecondOwlOntology(OWLOntology secondOwlOntology) {
        this.secondOwlOntology = secondOwlOntology;
    }

    public String getLogicalDifferencesOption() {
        return logicalDifferencesOption;
    }

    public void setLogicalDifferencesOption(String logicalDifferencesOption) {
        this.logicalDifferencesOption = logicalDifferencesOption;
    }

    public String getSignaturesOption() {
        return signaturesOption;
    }

    public void setSignaturesOption(String signaturesOption) {
        this.signaturesOption = signaturesOption;
    }

    public String getNewFilename() {
        return newFilename;
    }

    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }

    public CommonsMultipartFile getNewFileData() {
        return newFileData;
    }

    public void setNewFileData(CommonsMultipartFile newFileData) {
        this.newFileData = newFileData;
    }

    public OWLOntology getNewOwlOntology() {
        return newOwlOntology;
    }

    public void setNewOwlOntology(OWLOntology newOwlOntology) {
        this.newOwlOntology = newOwlOntology;
    }
}
