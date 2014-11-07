package data2;

/**
 * Interface that sets up the Multi-Set Bag
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object 
 */
public interface Bag<I extends Comparable> extends Sequenced<I> {

    /**
     * Indicates the size of set
     * <p>
     * Example: [x, y, z].cardinality() = 3
     *
     * @return An integer, representing number of elements in the set
     */
    public int cardinality();

    /**
     * Indicates if the set is empty
     * <p>
     * Example: [x, y, z].isEmptyHuh() = false
     * <p>
     * Example: [].isEmptyHuh() = true
     *
     * @return A boolean, true if the set is empty, false otherwise
     */
    public boolean isEmptyHuh();

    /**
     * Indicates if the element is a member of the set
     * <p>
     * Example: [x, y, z].member(x) = true
     * <p>
     * Example: [x, y, z].member(a) = false
     * 
     * @param elt a generic element to be searched for
     * @return A boolean, true if the element is in the set, false otherwise
     */
    public boolean member(I elt);

    /**
     * Adds an element to the set
     * <p>
     * Example: [x, y, z].add(a) = [a, x, y, z]
     * 
     * @param elt a generic element to be added
     * @return A Bag, newly made containing the new element and old set contents
     */
    public Bag add(I elt);

    /**
     * Removes an element from the set
     * <p>
     * Example: [x, y, z].remove(x) = [y, z]
     * 
     * @param elt a generic element to be removed
     * @return A Bag, newly made containing old set contents without the element
     */
    public Bag remove(I elt);

    /**
     * Creates the union of two sets
     * <p>
     * Example: [x, y, z].union([a, b, c]) = [a, b, c, x, y, z]
     * 
     * @param b a bag (multi-set) 
     * @return A Bag, newly made containing all elements from both sets
     */
    public Bag union(Bag b);

    /**
     * Creates the intersection of two sets
     * <p>
     * Example: [x, y, z].inter([a, b, x]) = [x]
     * 
     * @param b a bag (multi-set)
     * @return A Bag, newly made containing only elements that were in both sets
     */
    public Bag inter(Bag b);

    /**
     * Creates a set of the differences between two sets
     * <p>
     * Example: [x, y, z].diff([a, b, x]) = [y, z]
     * 
     * @param b a bag (multi-set)
     * @return A Bag, newly made containing elements only in this and not b
     */
    public Bag diff(Bag b);

    /**
     * Indicates if two sets are equal
     * <p>
     * Example: [x, y, z].equal([x, y, z]) = true
     * <p>
     * Example: [x, y, z].equal([a]) = false
     * 
     * @param b a bag (multi-set)
     * @return A Boolean, true if the sets are equal, false otherwise
     */
    public boolean equal(Bag b);

    /**
     * Indicates if a set is a subset of another set
     * <p>
     * Example: [x, y, z].subset([w, x, y, z]) = true
     * <p>
     * Example: [a, x, y, z].subset([x, y, z]) = false
     * 
     * @param b a bag (multi-set)
     * @return A Boolean, true if this is a subset of b, false otherwise
     */
    public boolean subset(Bag b);

    /**
     * Indicates number of times an element appears in the set
     * <p>
     * Example: [x, y, y, y, z, z].getCount([y]) = 3
     * 
     * @param elt a generic element to be counted
     * @return A Integer, the number of time the element appears in the set
     */
    public int getCount(I elt);
    
    /**
     * Adds N occurrences of an element to the set
     * <p>
     * Example: [x, y, z].addN(a, 2) = [a, a, x, y, z]
     * 
     * @param elt a generic element to be added
     * @param n   an integer number of times for element to be added
     * @return A Bag, newly made containing the new element n times and old set contents
     */
    public Bag addN(I elt, int n);

    /**
     * Removes N occurrences of an element from the set
     * <p>
     * Example: [a, a, a, x, y, z].removeN(a, 2) = [a, x, y, z]
     * 
     * @param elt a generic element to be removed
     * @param n   an integer number of times for element to be removed
     * @return A Bag, newly made with the element removed  n times and old set contents
     */
    public Bag removeN(I elt, int n);

    /**
     * Removes all occurrences of an element from the set
     * <p>
     * Example: [a, a, a, x, y, z].removeAll(a) = [x, y, z]
     * 
     * @param elt a generic element to be removed
     * @return A Bag, newly made with the element removed and old set contents
     */
    public Bag removeAll(I elt);

    /**
     * Turns a multi-set (Bag) into a sequence
     * 
     * @return A generic Sequence
     */
    public Sequence<I> seq();

    // I'm sure there's more to come...
    
//    public int sumIt();
//
//    public int sumItS(Sequence<I> as);
//
//    public String stringIt();
//
//    public String stringItS(Sequence<I> as);
//
//    public Bag<I> addInner(I elt, int n);
//
//    public boolean isRedHuh();
}
