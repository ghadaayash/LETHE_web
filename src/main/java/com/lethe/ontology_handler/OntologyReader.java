package com.lethe.ontology_handler;

import org.coode.owlapi.obo12.parser.OBO12DocumentFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLFunctionalSyntaxOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.semanticweb.owlapi.search.Searcher.annotations;
import static org.semanticweb.owlapi.vocab.OWLRDFVocabulary.RDFS_LABEL;

/**
 * Created by ghadahalghamdi on 06/07/2016.
 */
public class OntologyReader {
   // private final OWLOntology ontology;
   // private OWLDataFactory df;

    /*public OntologyReader(OWLOntology ontology, OWLDataFactory df) {
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

    /*public void testParse(InputStream s){
        try {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        //this method: builder.parse(file) take as parameter File file; can take 	parse(InputStream is),
        Document doc = builder.parse(s);

        NodeList list = doc.getElementsByTagName("*");
        System.out.println(" Elements: ");
           // System.out.println(doc.toString());
        for (int i=0; i<list.getLength(); i++) {

            Element element = (Element)list.item(i);
            System.out.println(element.getNodeName());
        }
    } catch (Exception e) {
            e.getMessage();
        }
        }*/

    public String readFile(File file)
    {
        String content = null;
        //File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return content;
    }

    public File saveOntology (OWLOntology resultedOntology){

        OWLOntologyManager manager= OWLManager.createOWLOntologyManager();
        File newOntologyFile=new File("/Users/ghadahalghamdi/Documents/LETHE_web/src/main/webapp/upload/result.owl");
        newOntologyFile=newOntologyFile.getAbsoluteFile();
        BufferedOutputStream outputStream= null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(newOntologyFile));
            manager.saveOntology(resultedOntology, (OWLDocumentFormat) new RDFXMLOntologyFormat(), outputStream);
        } catch (FileNotFoundException | OWLOntologyStorageException e) {
            e.printStackTrace();
        }

    return newOntologyFile;
    }
}
