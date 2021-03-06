package com.lethe.ontology_handler;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxParserFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLParser;
import org.semanticweb.owlapi.io.StreamDocumentSource;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ghadahalghamdi on 04/07/2016.
 */
public class OntologyFile {

    Set<OWLOntology> ontologies = new HashSet<>();
    ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    BidirectionalShortFormProviderAdapter b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);

    public OWLOntology uplodFile(MultipartFile file, OWLOntology ontology){
        try {
            String fileName = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                System.out.println("size::" + file.getSize());
                fileName = "/Users/ghadahalghamdi/Documents/LETHE_web/src/main/webapp/upload/"
                        + file.getOriginalFilename();
                File convFile = new File(fileName);
                file.transferTo(convFile);
                ontology = manager.loadOntologyFromOntologyDocument(convFile.getAbsoluteFile());
                //OWLDataFactory dataFactory = manager.getOWLDataFactory();
                //OntologyReader reader = new OntologyReader();
                //String co = reader.readFile(convFile);
                //System.out.println(co);
                outputStream = new FileOutputStream(fileName);
                System.out.println("fileName:" + file.getOriginalFilename());
                System.out.println("Number of Axioms: "+ ontology.getAxiomCount());
                //uploadItem.setOwlOntology(ontology);
                ontologies.add(ontology);
                int readBytes = 0;
                byte[] buffer = file.getBytes();
                while ((readBytes = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
                //manager.removeOntology(ontology);
            }

            // ..........................................
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ontology;}

    /*public OWLOntology uploadOntologywithAxioms(MultipartFile file, OWLOntology ontology){
        try {
            String fileName = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                System.out.println("size::" + file.getSize());
                fileName = "/Users/ghadahalghamdi/Documents/LETHE_web/src/main/webapp/upload/"
                        + file.getOriginalFilename();
                File convFile = new File(fileName);
                file.transferTo(convFile);
                ontology = manager.loadOntologyFromOntologyDocument(convFile.getAbsoluteFile());
                OWLParser parser = new ManchesterOWLSyntaxParserFactory().createParser(manager);
                // Create an input stream from the ontology, and use the parser to read its
                // contents into the ontology.
                inputStream = new ByteArrayInputStream( file.getBytes());
                    parser.parse( new StreamDocumentSource( inputStream ), ontology );
                    // Iterate over the axioms of the ontology. There are more than just the subclass
                    // axiom, because the class declarations are also axioms.  All in all, there are
                    // four:  the subclass axiom and three declarations of named classes.
                    //System.out.println( "== All Axioms: ==" );
                    // for ( OWLAxiom axiom : ontology.getTBoxAxioms( false ) ) {
                    //   System.out.println( axiom );
                    // }
                    outputStream = new FileOutputStream(fileName);
                    System.out.println("fileName:" + file.getOriginalFilename());
                    System.out.println("Number of Axioms: "+ ontology.getAxiomCount());
                    //uploadItem.setOwlOntology(ontology);
                    ontologies.add(ontology);
                    int readBytes = 0;
                    byte[] buffer = file.getBytes();
                    while ((readBytes = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
                    //manager.removeOntology(ontology);
                }

                // ..........................................

        }catch (Exception e) {
                e.printStackTrace();
            }
            return ontology;}*/

    public BidirectionalShortFormProviderAdapter haveB()
    {
        b = new BidirectionalShortFormProviderAdapter(ontologies, shortFormProvider);
        return b;
    }

    public String shortForm(BidirectionalShortFormProviderAdapter b, OWLEntity e){
        String s;
        s = b.getShortForm(e);
        return s;
    }

    //make this method return set of OWLEntities
    public Set<OWLEntity> entityForm(BidirectionalShortFormProviderAdapter b, List<String> shortForm){
        Set<OWLEntity> owlEntities = new HashSet<>();
        OWLEntity entity;
        for (String str : shortForm){
        entity = b.getEntity(str);
        owlEntities.add(entity);}

        return owlEntities;
    }

}
