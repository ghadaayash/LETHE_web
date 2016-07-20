package com.lethe.ontology_handler;

import org.coode.owlapi.functionalparser.OWLFunctionalSyntaxOWLParser;
import org.coode.owlapi.functionalparser.OWLFunctionalSyntaxParser;
import org.coode.owlapi.functionalparser.ParseException;
import org.coode.owlapi.obo12.parser.OBO12DocumentFormat;
import org.coode.owlapi.owlxmlparser.OWLXMLParser;
import org.coode.owlapi.rdfxml.parser.RDFXMLParser;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.*;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;


/**
 * Created by ghadahalghamdi on 06/07/2016.
 */
public class OntologyReader {

    public String readFile(File file) {
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

    public File saveOntology(OWLOntology resultedOntology) {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File newOntologyFile = new File("/Users/ghadahalghamdi/Documents/LETHE_web/src/main/webapp/upload/result.owl");
        newOntologyFile = newOntologyFile.getAbsoluteFile();
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(newOntologyFile));
            manager.saveOntology(resultedOntology, new RDFXMLOntologyFormat(), outputStream);
        } catch (FileNotFoundException | OWLOntologyStorageException e) {
            e.printStackTrace();
        }

        return newOntologyFile;
    }

    public File saveAxioms(Set<OWLLogicalAxiom> resultedAxioms) {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();
        File newOntologyFile = new File("/Users/ghadahalghamdi/Documents/LETHE_web/src/main/webapp/upload/newEntailments.owl");
        newOntologyFile=newOntologyFile.getAbsoluteFile();
        BufferedOutputStream outputStream = null;
        try {
            OWLOntology ontology = manager.createOntology();
            for (Iterator<OWLLogicalAxiom> axiomIterator = resultedAxioms.iterator(); axiomIterator.hasNext(); ) {
                OWLAxiom axiom = axiomIterator.next();
                manager.addAxiom(ontology, axiom);
                outputStream = new BufferedOutputStream(new FileOutputStream(newOntologyFile));
                manager.saveOntology(ontology, new OWLXMLOntologyFormat(), outputStream);
            }

        } catch (FileNotFoundException | OWLOntologyCreationException | OWLOntologyStorageException e) {
            e.printStackTrace();
            }
        return newOntologyFile;
    }

    public Set<OWLAxiom> fromStringtoOWLAxiom(List<String> axiomstrings){

        Set<OWLAxiom> owlAxioms = new HashSet<>();
        try{
            OWLOntologyManager translationManager = OWLManager.createOWLOntologyManager();
            OWLOntology ontology = translationManager.createOntology();
            DefaultPrefixManager nsm = new DefaultPrefixManager();
        for (Iterator iterator = axiomstrings.iterator(); iterator.hasNext(); ){
            String axiomstring = (String) iterator.next();
            InputStream in = new ByteArrayInputStream(axiomstring.getBytes());
            OWLFunctionalSyntaxParser parser = new OWLFunctionalSyntaxParser(in);
            parser.setUp(ontology, new OWLOntologyLoaderConfiguration());
            parser.setPrefixes(nsm);
            OWLAxiom axiom = parser.Axiom();
            //owlAxioms.add(axiom);
            translationManager.addAxiom(ontology, axiom);
        }   owlAxioms = ontology.getTBoxAxioms(false);
    }catch (OWLOntologyCreationException | ParseException e){e.printStackTrace();}

        return owlAxioms;
    }

    public File saveText(String content){
        File file = new File("/Users/ghadahalghamdi/Documents/LETHE_web/src/main/webapp/upload/explanation.owl");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }catch (IOException e){e.printStackTrace();}
        return file;
    }
}
