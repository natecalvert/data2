package data2;

/**
 * Interface for generating random objects
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public interface Randoms<I extends Comparable> {
    
    /**
     * Generates a random object
     * 
     * @return I, a random object
     */
    public I makeRandom();
}

