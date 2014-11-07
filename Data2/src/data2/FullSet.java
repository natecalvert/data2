package data2;

/**
 * Non-Empty Multi Set Bag
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object
 */
public class FullSet<I extends Comparable> implements Bag<I>, Sequenced<I> {

    I item;
    int count;
    Bag<I> left;
    Bag<I> right;

     /**
     * Constructor that takes data to set up the Non-Empty Bag
     * 
     * @param item A generic type object
     */
    public FullSet(I item) {
        this.item = item;
        this.count = 1;
        this.left = empty();
        this.right = empty();
    }

    /**
     * Constructor that takes data and count to set up the Non-Empty Bag
     * 
     * @param item  A generic type object
     * @param count An integer for how many times item should appear
     */
    public FullSet(I item, int count) {
        this.item = item;
        this.count = count;
        this.left = empty();
        this.right = empty();
    }

    /**
     * Constructor that takes data, a left and a right to set up the Non-Empty Bag
     * 
     * @param item  A generic type object
     * @param left  A bag whose data is less than that of item
     * @param right A bag whose data is greater than that of item
     */
    public FullSet(I item, Bag<I> left, Bag<I> right) {
        this.item = item;
        this.count = 1;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructor that takes data, count, a left and a right to set up the Non-Empty Bag
     * 
     * @param item  A generic type object
     * @param count An integer for how many times item should appear
     * @param left  A bag whose data is less than that of item
     * @param right A bag whose data is greater than that of item
     */
    public FullSet(I item, int count, Bag<I> left, Bag<I> right) {
        this.item = item;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public static Bag empty() {
        return new MTSet();
    }

    public int cardinality() {
        return count + this.left.cardinality() + this.right.cardinality();
    }

    public boolean isEmptyHuh() {
        return this.getCount(item) == 0 && left.isEmptyHuh() && right.isEmptyHuh();
    }

//    public boolean member(I elt) {
//        if (this.item.compareTo(elt) == 0) {
//            return this.count > 0;
//        } else if (this.item.compareTo(elt) > 0) {
//            return left.member(elt);
//        } else {
//            return right.member(elt);
//        }
//    }
    
    // Above just looks for elt and compares the count; basically getCount.
    public boolean member(I elt) {
        return this.getCount(elt) != 0;
    }

//    public Bag add(I elt) {
//        if (this.item.compareTo(elt) == 0) {
//            return new FullSet(this.item, this.count + 1, this.left, this.right);
//        } else if (elt.compareTo(this.item) > 0) {
//            return new FullSet(this.item, this.count, this.left, this.right.add(elt));
//        } else {
//            return new FullSet(this.item, this.count, this.left.add(elt), this.right);
//        }
//    }
    
    // addN where N = 1
    public Bag add(I elt) {
        return addN(elt, 1);
    }

//    public Bag remove(I elt) {
//        if (this.item.compareTo(elt) == 0) {
//            return new FullSet(this.item, this.count - 1, this.left, this.right);
//        } else if (elt.compareTo(this.item) > 0) {
//            return new FullSet(this.item, this.count, this.left, this.right.remove(elt));
//        } else {
//            return new FullSet(this.item, this.count, this.left.remove(elt), this.right);
//        }
//    }
    
    // removeN where N = 1
    public Bag remove(I elt) {
        return removeN(elt, 1);
    }

    public Bag union(Bag set) {
        return left.union(right.union(set).addN(item, this.getCount(item)));
    }

    public Bag inter(Bag set) {
        if (set.member(this.item)) {
            int minimum = Math.min(set.getCount(item), this.getCount(item));
            return new FullSet(this.item, minimum, this.left.inter(set), this.right.inter(set));
        } else {
            return this.left.inter(set).union(this.right.inter(set));
        }
    }

    public Bag diff(Bag set) {
        return left.union(right).diff(set.removeN(item, this.getCount(item)));
    }

    public boolean equal(Bag set) {
        return this.subset(set) && set.subset(this);
    }

    public boolean subset(Bag set) {
        return (this.getCount(item) <= set.getCount(item)
                && this.left.union(this.right).subset(set));
    }

    public int getCount(I elt) {
        if (this.item.compareTo(elt) == 0) {
            return count;
        } else if (this.item.compareTo(elt) > 0) {
            return left.getCount(elt);
        } else {
            return right.getCount(elt);
        }
    }

    public Bag addN(I elt, int n) {
        if (this.item.compareTo(elt) == 0) {
            int maximum = Math.max(0, this.count + n);
            return new FullSet(this.item, maximum, this.left, this.right);
        } else if (elt.compareTo(this.item) > 0) {
            return new FullSet(this.item, this.count, this.left, this.right.addN(elt, n));
        } else {
            return new FullSet(this.item, this.count, this.left.addN(elt, n), this.right);
        }
    }

    public Bag removeN(I elt, int n) {
        if (this.item.compareTo(elt) == 0) {
            int maximum = Math.max(0, this.count - n);
            return new FullSet(this.item, maximum, this.left, this.right);
        } else if (elt.compareTo(this.item) > 0) {
            return new FullSet(this.item, this.count, this.left, this.right.removeN(elt, n));
        } else {
            return new FullSet(this.item, this.count, this.left.removeN(elt, n), this.right);
        }
    }

//    public Bag removeAll(I elt) {
//        if (this.item.compareTo(elt) == 0) {
//            return left.union(right);
//        } else if (elt.compareTo(this.item) > 0) {
//            return new FullSet(this.item, this.count, this.left, this.right.removeAll(elt));
//        } else {
//            return new FullSet(this.item, this.count, this.left.removeAll(elt), this.right);
//        }
//    }
    
    //removeN where N = count of elt
    public Bag removeAll(I elt) {
        return removeN(elt, this.getCount(elt));
    }

    public Sequence<I> seq() {
        return new FullSeq(item, count, (new SequenceComb(this.left.seq(), this.right.seq())));
    }
}
