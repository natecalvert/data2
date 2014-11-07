package data2;

/**
 * Non-Empty Sequence
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public class FullSeq<I extends Comparable> implements Sequence<I>, Sequenced<I>{
    
    I item;
    int count;
    Sequence<I> next;
    
    FullSeq(I item, int count, Sequence<I> next) {
        this.item = item;
        this.count = count;
        this.next = next;
    }
    
    public I here() {
        return this.item;
    }

    public boolean hasNext() {
        return true;
    }

    public Sequence<I> next() {
        if (count > 1) {
            return new FullSeq(item, count - 1, next);
        } else {
            return next;
        }
    }

    public String toStringSeq() {
        return "" + this.item;
    }

    public Sequence<I> seq() {
        return this;
    }
}