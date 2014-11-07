package data2;

/**
 * Interface that allows objects to be sequenced
 *
 * @author Nate Calvert
 *
 * @param <I> A generic type object
 */
public interface Sequenced<I extends Comparable> {

    /**
     * A method to turn the object into a sequence 
     * <p>
     * Example: {x, y, z}.seq() = [x, y, z]
     *
     * @return A Sequence, containing the objects in this
     */
    public Sequence<I> seq();
}
