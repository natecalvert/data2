package data2;

/**
 * Empty Sequence
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public class MTSeq<I extends Comparable> implements Sequence<I>, Sequenced<I>{
    
    public I here() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    public Sequence<I> next() {
        return this;
    }

    public String toStringSeq() {
        return "";
    }

    public Sequence<I> seq() {
        return this;
    }
}