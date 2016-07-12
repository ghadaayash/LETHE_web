package com.lethe.lethe;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import uk.ac.man.cs.lethe.abduction.Explanation;
import uk.ac.man.cs.lethe.abduction.OWLAbducerSignatureBased;
import uk.ac.man.cs.lethe.interpolation.AlchTBoxInterpolatorC;

import java.util.Set;

/**
 * Created by ghadahalghamdi on 09/07/2016.
 */
public class TboxAbduction {

    //OWLAbducerSignatureBased abducer;

    public Set<Explanation> useAbduction(OWLOntology ontology, Set<OWLEntity> signature, Set<OWLAxiom> observations ){

        AlchTBoxInterpolatorC interpolator = new AlchTBoxInterpolatorC();
        OWLAbducerSignatureBased abducer = new OWLAbducerSignatureBased(interpolator, ontology);
        abducer.setAbducibles(signature);

        Set<Explanation> explanation_s = abducer.abduce(observations);

        return explanation_s;
    }

    // Each Explanation object contains a set of TBox axioms which is sufficient to be added to the ontology in order
    // to entail the observation.

    //Explanation explanation = ...
    //Set<OWLAxiom> axioms = explanation.getAxioms();

}
