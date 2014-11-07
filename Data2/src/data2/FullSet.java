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
    boolean isBlack;

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
     * @param item A generic type object
     * @param count An integer for how many times item should appear
     */
    public FullSet(I item, int count) {
        this.item = item;
        this.count = count;
        this.left = empty();
        this.right = empty();
    }

    /**
     * Constructor that takes data, a left and a right to set up the Non-Empty
     * Bag
     *
     * @param item A generic type object
     * @param left A bag whose data is less than that of item
     * @param right A bag whose data is greater than that of item
     */
    public FullSet(I item, Bag<I> left, Bag<I> right) {
        this.item = item;
        this.count = 1;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructor that takes data, count, a left and a right to set up the
     * Non-Empty Bag
     *
     * @param item A generic type object
     * @param count An integer for how many times item should appear
     * @param left A bag whose data is less than that of item
     * @param right A bag whose data is greater than that of item
     */
    public FullSet(I item, int count, Bag<I> left, Bag<I> right) {
        this.item = item;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructor that takes data, count, a left, a right and a color to set up the
     * Non-Empty Bag
     *
     * @param item A generic type object
     * @param count An integer for how many times item should appear
     * @param left A bag whose item is less than that of item
     * @param right A bag whose item is greater than that of item
     * @param isBlack A boolean indicating color of the node
     */
    public FullSet(I item, int count, Bag<I> left, Bag<I> right, boolean isBlack) {
        this.item = item;
        this.count = count;
        this.left = left;
        this.right = right;
        this.isBlack = isBlack;
    }

    /**
     * Gives a new empty set
     *
     * @return A Bag, a new empty set
     */
    public static Bag empty() {
        return new MTSet();
    }

    public int cardinality() {
        return count + left.cardinality() + right.cardinality();
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
        return addN(elt, 1).blacken();
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
        return set.union(left.union(right)).addN(item, this.getCount(item));
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
            return new FullSet(this.item, maximum, this.left, this.right, this.isBlack);
        } else {
            if (elt.compareTo(this.item) > 0) {
                return new FullSet(this.item, this.count, this.left, this.right.addN(elt, n), this.isBlack).balance();
            } else {
                return new FullSet(this.item, this.count, this.left.addN(elt, n), this.right, this.isBlack).balance();
            }
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

    public boolean isBlackHuh() {
        return isBlack;
    }

    public Bag blacken() {
        return new FullSet(this.item, this.count, this.left, this.right, true);
    }

    public Bag balance() {
        FullSet lefty;
        FullSet leftyleft;
        FullSet leftyright;
        FullSet righty;
        FullSet rightyleft;
        FullSet rightyright;

        if ((this.isBlackHuh()
                && (this.left instanceof FullSet)
                && (((FullSet) this.left).left instanceof FullSet)
                && !((FullSet) this.left).isBlackHuh()
                && !((FullSet) this.left).left.isBlackHuh())) {
            //cast it so compiler knows it's FullSet
            //error item because i can't get the left of left. 

            lefty = ((FullSet) this.left);
            leftyleft = ((FullSet) lefty.left);

            return new FullSet(
                    /* item */lefty.item,
                    /* count */ lefty.count,
                    /* left */ new FullSet(leftyleft.item, leftyleft.count, leftyleft.left, leftyleft.right, true),
                    /* right */ new FullSet(this.item, this.count, leftyleft.right, this.right, true),
                    /* isBlack */ false);

        } else if ((this.isBlackHuh()
                && (this.left instanceof FullSet)
                && (((FullSet) this.left).right instanceof FullSet)
                && !((FullSet) this.left).isBlackHuh()
                && !((FullSet) this.left).right.isBlackHuh())) {

            lefty = ((FullSet) this.left);
            leftyleft = ((FullSet) lefty.left);
            leftyright = ((FullSet) lefty.right);

            return new FullSet(
                    /* item */leftyright.item,
                    /* count */ leftyright.count,
                    /* left */ new FullSet(lefty.item, lefty.count, leftyleft, leftyright.left, true),
                    /* right */ new FullSet(this.item, this.count, leftyright.right, this.right, true),
                    /* isBlack */ false);

        } else if ((this.isBlackHuh()
                && (this.right instanceof FullSet)
                && (((FullSet) this.right).left instanceof FullSet)
                && !((FullSet) this.right).isBlackHuh()
                && !((FullSet) this.right).left.isBlackHuh())) {

            righty = ((FullSet) this.right);
            rightyleft = ((FullSet) righty.left);

            return new FullSet(
                    /* item */rightyleft.item,
                    /* count */ rightyleft.count,
                    /* left */ new FullSet(this.item, this.count, this.left, rightyleft.left, true),
                    /* right */ new FullSet(righty.item, righty.count, rightyleft.right, righty.right, true),
                    /* isBlack */ false);

        } else if ((this.isBlackHuh()
                && (this.right instanceof FullSet)
                && (((FullSet) this.right).right instanceof FullSet)
                && !((FullSet) this.right).isBlackHuh()
                && !((FullSet) this.right).right.isBlackHuh())) {

            righty = ((FullSet) this.right);
            rightyright = ((FullSet) righty.right);
            rightyleft = ((FullSet) righty.left);

            return new FullSet(
                    /* item */righty.item,
                    /* count */ righty.count,
                    /* left */ new FullSet(this.item, this.count, this.left, rightyleft, true),
                    /* right */ new FullSet(rightyright.item, rightyright.count, rightyright.left, rightyright.right, true),
                    /* isBlack */ false);

        } else {
            return this;
        }
    }
}
