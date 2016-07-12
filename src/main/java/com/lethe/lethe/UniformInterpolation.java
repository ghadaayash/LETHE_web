package com.lethe.lethe;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import uk.ac.man.cs.lethe.forgetting.AlcOntologyForgetter;
import uk.ac.man.cs.lethe.forgetting.AlchTBoxForgetter;
import uk.ac.man.cs.lethe.forgetting.IOWLForgetter;
import uk.ac.man.cs.lethe.forgetting.ShqTBoxForgetter;
import uk.ac.man.cs.lethe.interpolation.AlcOntologyInterpolator;
import uk.ac.man.cs.lethe.interpolation.AlchTBoxInterpolator;
import uk.ac.man.cs.lethe.interpolation.IOWLInterpolator;
import uk.ac.man.cs.lethe.interpolation.ShqTBoxInterpolator;

import java.util.Set;

/**
 * Created by ghadahalghamdi on 09/07/2016.
 */
public class UniformInterpolation {

    //Method 1 (AlchTBoxInterpolator, AlchTBoxForgetter)

    public OWLOntology alchInterpolation(OWLOntology inputOntology, Set<OWLEntity> symbols){

        IOWLInterpolator interpolator = new AlchTBoxInterpolator();

        //get selected symbols (signatures)
       // Set<OWLEntity> symbols = inputOntology.getSignature();
        OWLOntology interpolant = interpolator.uniformInterpolant(inputOntology, symbols);

        return interpolant;
    }

    public OWLOntology alchForgetteng(OWLOntology inputOntology, Set<OWLEntity> symbols){

        IOWLForgetter forgetter = new AlchTBoxForgetter();

        //get selected symbols (signatures)
        //Set<OWLEntity> symbols = inputOntology.getSignature();
        OWLOntology forgettingResult = forgetter.forget(inputOntology, symbols);

        return forgettingResult;
    }

    //Method 2 (ShqTBoxInterpolator, ShqTBoxForgetter)

    public OWLOntology shqInterpolation(OWLOntology inputOntology, Set<OWLEntity> symbols){

        IOWLInterpolator interpolator = new ShqTBoxInterpolator();

        //get selected symbols (signatures)
        //Set<OWLEntity> symbols = inputOntology.getSignature();
        OWLOntology interpolant = interpolator.uniformInterpolant(inputOntology, symbols);

        return interpolant;
    }


    public OWLOntology shqForgetteng(OWLOntology inputOntology){

        IOWLForgetter forgetter = new ShqTBoxForgetter();

        //get selected symbols (signatures)
        Set<OWLEntity> symbols = inputOntology.getSignature();
        OWLOntology forgettingResult = forgetter.forget(inputOntology, symbols);

        return forgettingResult;
    }

    //Method 3 (AlcOntologyInterpolator, AlcOntologyForgetter)

    public OWLOntology alcInterpolation(OWLOntology inputOntology, Set<OWLEntity> symbols){

        IOWLInterpolator interpolator = new AlcOntologyInterpolator();

        //get selected symbols (signatures)
        //Set<OWLEntity> symbols = inputOntology.getSignature();
        OWLOntology interpolant = interpolator.uniformInterpolant(inputOntology, symbols);

        return interpolant;
    }


    public OWLOntology alcForgetteng(OWLOntology inputOntology){

        IOWLForgetter forgetter = new AlcOntologyForgetter();

        //get selected symbols (signatures)
        Set<OWLEntity> symbols = inputOntology.getSignature();
        OWLOntology forgettingResult = forgetter.forget(inputOntology, symbols);

        return forgettingResult;
    }

}
