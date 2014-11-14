package data2;

/**
 * Combines Sequences
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public class SequenceComb<I extends Comparable> implements Sequence<I>, Sequenced<I>{
    
    Sequence<I> left;
    Sequence<I> right;
    
    /**
     * Constructor that takes a left and a right sequence, to combine sequences
     * 
     * @param left A sequence
     * @param right A sequence
     */
    public SequenceComb(Sequence<I> left, Sequence<I> right) {
        this.left = left;
        this.right = right;
    }
    
    public I here() {
        if (this.left.hasNext()) {
            return this.left.here();
        } else {
            return this.right.here();
        }
    }
    public boolean hasNext() {
        return this.left.hasNext() || this.right.hasNext();
    }
    
    public Sequence<I> next() {
        if (this.left.hasNext()) {
            return new SequenceComb(this.left.next(), this.right);
        } else {
            return this.right.next();
        }
    }
    
    public String toStringSeq() {
        return this.left.toStringSeq() + " " + this.right.toStringSeq();
    }

    // Maybe Remove? Don't need 'implement sequenced' here?
    public Sequence<I> seq() {
        return this;
    }
}
