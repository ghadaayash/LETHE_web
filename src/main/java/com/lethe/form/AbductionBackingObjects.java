package com.lethe.form;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Set;

/**
 * Created by ghadahalghamdi on 19/07/2016.
 */
public class AbductionBackingObjects {

    private String filename;
    private CommonsMultipartFile fileData;
    //private String selectedSig;
    private List<String> selectedSig;
    //private String selectedAx;
    private List<String> selectedAx;
    private OWLOntology owlOntology;
    private Set<OWLEntity> owlEntities;
    private Set<OWLAxiom> axioms;

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

    public Set<OWLAxiom> getAxioms() {
        return axioms;
    }

    public void setAxioms(Set<OWLAxiom> axioms) {
        this.axioms = axioms;
    }

    public List<String> getSelectedSig() {
        return selectedSig;
    }

    public void setSelectedSig(List<String> selectedSig) {
        this.selectedSig = selectedSig;
    }

    public List<String> getSelectedAx() {
        return selectedAx;
    }

    public void setSelectedAx(List<String> selectedAx) {
        this.selectedAx = selectedAx;
    }
}
