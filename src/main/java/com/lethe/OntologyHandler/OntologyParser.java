package com.lethe.OntologyHandler;

import static org.semanticweb.owlapi.vocab.OWLRDFVocabulary.RDFS_LABEL;

import org.semanticweb.owlapi.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghadahalghamdi on 06/07/2016.
 */
public class OntologyParser {
    /*private final OWLOntology ontology;
    private OWLDataFactory df;

    public OntologyParser(OWLOntology ontology, OWLDataFactory df) {
        this.ontology = ontology;
        this.df = df;
    }

    public void parseOntology()
            throws OWLOntologyCreationException {

        for (OWLClass cls : ontology.getClassesInSignature()) {
            String id = cls.getIRI().toString();
            String label = get(cls, RDFS_LABEL.toString()).get(0);
            System.out.println(label + " [" + id + "]");
        }
    }

    private List<String> get(OWLClass clazz, String property) {
        List<String> ret = new ArrayList<>();

        final OWLAnnotationProperty owlProperty = df
                .getOWLAnnotationProperty(IRI.create(property));
        for (OWLOntology o : ontology.getImportsClosure()) {
            for (OWLAnnotation annotation : annotations(
                    o.getAnnotationAssertionAxioms(clazz.getIRI()), owlProperty)) {
                if (annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral val = (OWLLiteral) annotation.getValue();
                    ret.add(val.getLiteral());
                }
            }
        }
        return ret;
    }*/
}
