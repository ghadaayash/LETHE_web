package com.lethe.lethe;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import uk.ac.man.cs.lethe.interpolation.AlcOntologyInterpolator;
import uk.ac.man.cs.lethe.interpolation.AlchTBoxInterpolator;
import uk.ac.man.cs.lethe.interpolation.IOWLInterpolator;
import uk.ac.man.cs.lethe.interpolation.ShqTBoxInterpolator;
import uk.ac.man.cs.lethe.logicalDifference.LogicalDifferenceComputer;

import java.util.Set;

/**
 * Created by ghadahalghamdi on 09/07/2016.
 */
public class LogicalDifferences {

    //aclh
    //Compute Logical Difference for common signature of the two ontologies:
    public Set<OWLLogicalAxiom> computeDiff_coms_alch(OWLOntology ontology1, OWLOntology ontology2, int approximationLevel)
    {
        //get interpolator based on?
        IOWLInterpolator interpolator = new AlchTBoxInterpolator();
        LogicalDifferenceComputer ldc = new LogicalDifferenceComputer(interpolator);
        Set<OWLLogicalAxiom> diff = ldc.logicalDifference(ontology1, ontology2, approximationLevel);

        return diff;
    }

    // Compute Logical Difference for specified signature (specified signature based on which ontology?)
    public Set<OWLLogicalAxiom> computeDiff_s_alch(OWLOntology ontology1, OWLOntology ontology2, Set<OWLEntity> signature, int approximationLevel)
    {
        //get interpolator based on?
        IOWLInterpolator interpolator = new AlchTBoxInterpolator();
        LogicalDifferenceComputer ldc = new LogicalDifferenceComputer(interpolator);
        Set<OWLLogicalAxiom> diff = ldc.logicalDifference(ontology1, ontology2, signature, approximationLevel);

        return diff;
    }

    //shq
    //Compute Logical Difference for common signature of the two ontologies:
    public Set<OWLLogicalAxiom> computeDiff_coms_shq(OWLOntology ontology1, OWLOntology ontology2, int approximationLevel)
    {
        //get interpolator based on?
        IOWLInterpolator interpolator = new ShqTBoxInterpolator();
        LogicalDifferenceComputer ldc = new LogicalDifferenceComputer(interpolator);
        Set<OWLLogicalAxiom> diff = ldc.logicalDifference(ontology1, ontology2, approximationLevel);

        return diff;
    }

    // Compute Logical Difference for specified signature (specified signature based on which ontology?)
    public Set<OWLLogicalAxiom> computeDiff_s_shq(OWLOntology ontology1, OWLOntology ontology2, Set<OWLEntity> signature, int approximationLevel)
    {
        //get interpolator based on?
        IOWLInterpolator interpolator = new ShqTBoxInterpolator();
        LogicalDifferenceComputer ldc = new LogicalDifferenceComputer(interpolator);
        Set<OWLLogicalAxiom> diff = ldc.logicalDifference(ontology1, ontology2, signature, approximationLevel);

        return diff;
    }

    //acl
    //Compute Logical Difference for common signature of the two ontologies:
    public Set<OWLLogicalAxiom> computeDiff_coms_alc(OWLOntology ontology1, OWLOntology ontology2, int approximationLevel)
    {
        //get interpolator based on?
        IOWLInterpolator interpolator = new AlcOntologyInterpolator();
        LogicalDifferenceComputer ldc = new LogicalDifferenceComputer(interpolator);
        Set<OWLLogicalAxiom> diff = ldc.logicalDifference(ontology1, ontology2, approximationLevel);

        return diff;
    }

    // Compute Logical Difference for specified signature (specified signature based on which ontology?)
    public Set<OWLLogicalAxiom> computeDiff_s_alc(OWLOntology ontology1, OWLOntology ontology2, Set<OWLEntity> signature, int approximationLevel)
    {
        //get interpolator based on?
        IOWLInterpolator interpolator = new AlcOntologyInterpolator();
        LogicalDifferenceComputer ldc = new LogicalDifferenceComputer(interpolator);
        Set<OWLLogicalAxiom> diff = ldc.logicalDifference(ontology1, ontology2, signature, approximationLevel);

        return diff;
    }
}
