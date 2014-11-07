package data2;

/**
 * Interface for sequences
 *
 * @author Nate Calvert
 *
 * @param <I> A generic type object
 */
public interface Sequence<I extends Comparable> {

    /**
     * Indicates where the sequence is located
     * <p>
     * Example: [x, y, z].here() = x
     *
     * @return A generic object, where the sequence is located
     */
    public I here();

    /**
     * Checks if the sequence has a next value
     * <p>
     * Example: [x, y, z].hasNext() = true
     *
     * @return A Boolean, true if there is a next, false otherwise
     */
    public boolean hasNext();

    /**
     * Returns the sequence after here
     *<p>
     * Example: [x, y, z].next() = [y, z]
     *
     * @return A Sequence, of all I after here
     */
    public Sequence<I> next();

    /**
     * Creates a string version of the sequence
     * <p>
     * Example: [x, y, z].toStringSeq() = "x y z"
     *
     * @return A String, representing the sequence
     */
    public String toStringSeq();
}
