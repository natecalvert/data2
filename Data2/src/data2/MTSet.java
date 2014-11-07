package data2;

/**
 * Empty Multi Set Bag
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public class MTSet<I extends Comparable> implements Bag<I>, Sequenced<I> {

    public void MTSet() {

    }

    public Bag empty() {
        return new MTSet();
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public boolean member(I elt) {
        return false;
    }

    public Bag add(I elt) {
        return new FullSet(elt);
    }

    public Bag remove(I elt) {
        return this;
    }

    public Bag union(Bag set) {
        return set;
    }

    public Bag inter(Bag set) {
        return this;
    }

    public Bag diff(Bag set) {
        return set;
    }

    public boolean equal(Bag set) {
        return set.isEmptyHuh();
    }

    public boolean subset(Bag set) {
        return true;
    }

    public int getCount(I elt) {
        return 0;
    }

    public Bag addN(I elt, int i) {
        return new FullSet(elt, i);
    }

    public Bag removeN(I elt, int i) {
        return this;
    }

    public Bag removeAll(I elt) {
        return this;
    }

    public Sequence<I> seq() {
        return new MTSeq();
    }
}
