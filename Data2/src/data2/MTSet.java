package data2;

/**
 * Empty Multi Set Bag
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public class MTSet<I extends Comparable> implements Bag<I>, Sequenced<I> {
    boolean isBlack;
    
    public void MTSet() {
        this.isBlack = true;
    }
    
    public void MTSet(Boolean isBlack) {
        this.isBlack = isBlack;
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
        return this.addN(elt, 1).blacken();
    }

    public Bag remove(I elt) {
        return this;
    }

    public Bag union(Bag set) {
        return set;
    }

    public Bag inter(Bag set) {
        return empty();
    }

    public Bag diff(Bag set) {
        return set;
    }

    public boolean equal(Bag set) {
        return set.cardinality() == this.cardinality();
    }

    public boolean subset(Bag set) {
        return true;
    }

    public int getCount(I elt) {
        return 0;
    }

    public Bag addN(I elt, int i) {
        return new FullSet(elt, i, empty(), empty(), false);
    }

    public Bag removeN(I elt, int i) {
        return this.remove(elt);
    }

    public Bag removeAll(I elt) {
        return this.remove(elt);
    }

    public Sequence<I> seq() {
        return new MTSeq();
    }
    
    public Bag balance(){
        return this;
    }
    
    public boolean isBlackHuh(){
        return isBlack;
    }
    
    public Bag blacken(){
        return new MTSet();
    }
}