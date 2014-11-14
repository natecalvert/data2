package data2;

import java.util.Random;

/**
 * Testing for Bag and Sequence methods
 * 
 * @author Nate Calvert
 * 
 * @param <I> A generic type object 
 */
public class Tests<I extends Comparable> {

    Randoms<I> randy;
    int maxRandySize = 10;
    static int repeat = 100;

    public Tests(Randoms<I> randy) {
        this.randy = randy;
    }

    public static Bag empty() {
        return new MTSet();
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public Bag<I> randBag(int size) {
        if (size == 0) {
            return empty();
        } else {
            return randBag(size - 1).addN(randy.makeRandom(), randInt(1, maxRandySize));
        }
    }

    // Empty & isEmptyHuh
    //   If the bag is empty, then isEmptyHuh must return true.
    //   If the bag is not empty, then isEmptyHuh must return false.
    public void empty_isEmptyHuh(int count) throws Exception {
        for (int i = 0; i < repeat; i++) {
            if (count == 0) {
                Bag e = empty();
                if (!e.isEmptyHuh()) {
                    throw new Exception("Test failed. MTBag is not empty.");
                }
            } else {
                int length = randInt(1, maxRandySize);
                Bag n = randBag(length);
                if (n.isEmptyHuh()) {
                    throw new Exception("Test failed. FullBag is empty");
                }
            }
        }
    }

    // Cardinality & isEmptyHuh
    //   If cardinality = 0, then isEmptyHuh must return true.
    //   If cardinality != 0, then isEmptyHuh must return false.
    public void isEmptyHuh_cardinality() throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            if (!b.isEmptyHuh() && (b.cardinality() == 0)) {
                throw new Exception("Test failed. FullBag had cardinality of 0.");
            }
            if (b.isEmptyHuh() && (b.cardinality() != 0)) {
                throw new Exception("Test failed. MTBag had non-0 cardinality. ");
            }
        }
    }

    // Cardinality, Remove & getCount
    //   After removing an element from a bag, the cardinality must
    //   be less than or equal to the original cardinality.
    public void cardinality_remove_getCount() throws Exception {
        for (int i = 0; i < repeat; i++) {
            I randomElt = randy.makeRandom();
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            int newCard = b.remove(randomElt).cardinality();
            if (b.getCount(randomElt) >= 1 && newCard != b.cardinality() - 1) {
                throw new Exception("Test failed. Cardinality didn't decrease on removal.");
            }
            if (b.getCount(randomElt) == 0 && newCard != b.cardinality()) {
                throw new Exception("Test failed. Cardinality changed when nothing was removed.");
            }
        }
    }

    // Cardinality & Add
    //   After adding an element to a bag, the cardinality must
    //   be greater than or equal to the original cardinality.
    public void cardinality_add() throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            int currentCard = b.cardinality();
            if (b.add(randy.makeRandom()).cardinality() != currentCard + 1) {
                throw new Exception("Test failed. Cardinality didn't incease on addition.");
            }
            if (b.add(randy.makeRandom()).cardinality() == currentCard) {
                throw new Exception("Test failed. Addition didn't work.");
            }
        }
    }

    // Cardinality & AddN
    //   After adding N elements to a bag, the cardinality must
    //   be N greater than or equal to the original cardinality.
    public void cardinality_addN(int x) throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            int initialCard = b.cardinality();
            if (b.addN(randy.makeRandom(), x).cardinality() != initialCard + x) {
                throw new Exception("Test failed. Card didn't increase properly.");
            } else if (x != 0 && b.addN(randy.makeRandom(), x).cardinality() == initialCard) {
                throw new Exception("Test failed. Adding didn't increase cardinality.");
            }
        }
    }

    // Add & Member
    //   After adding an element to a bag, member must 
    //   return true when called on the added element.
    public void add_member() throws Exception {
        for (int i = 0; i < repeat; i++) {
            I x = randy.makeRandom();
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            if (b.add(x).member(x)) {
                // Working!
            } else {
                throw new Exception("Test failed. X was not found to be a member.");
            }
        }
    }

    // Add, Remove, getCount & Equal
    //   After adding an element to a bag, the count must be 1 greater.
    //   After removing that element from the bag, the count must the original value.
    //   After adding and then removing an element, the original bag must be returned.
    public void add_remove_equal() throws Exception {
        for (int i = 0; i < repeat; i++) {
            I x = randy.makeRandom();
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            Bag bAdd = b.add(x);
            Bag bRemove = bAdd.remove(x);
            if (bAdd.getCount(x) - 1 != b.getCount(x)) {
                throw new Exception("Test failed. Count didn't increase from adding.");
            }
            if (bRemove.getCount(x) != b.getCount(x)) {
                throw new Exception("Test failed. Count didn't return to original after removing added elt.");
            }
            if (!b.equal(bRemove)) {
                throw new Exception("Test failed. Adding then removing didn't yield original.");
            }
        }
    }

    // AddN, RemoveN, getCount & Equal
    //   After adding N elements to a bag, the count must be N greater.
    //   After removing N elements from the bag, the count must be N smaller.
    //   After adding and then removing N elements, the original bag must be returned.
    public void addN_removeN_equal(int x) throws Exception {
        for (int i = 0; i < repeat; i++) {
            I y = randy.makeRandom();
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            Bag bAdd = b.addN(y, x);
            Bag bRemove = bAdd.removeN(y, x);
            if (bAdd.getCount(y) - x != b.getCount(y)) {
                throw new Exception("Test failed. Count didn't increase from adding - vN.");
            }
            if (bRemove.getCount(y) != b.getCount(y)) {
                throw new Exception("Test failed. Count didn't return to original after removing added elt - vN.");
            }
            if (!b.equal(bRemove)) {
                throw new Exception("Test failed. Adding then removing didn't yield original - vN.");
            }
        }
    }
    
    // RemoveAll, Member & getCount
    //   After removing all of an element from the bag, the count must be 0.
    //   After removing all of an element from the bag, member must be false.
    public void member_removeAll() throws Exception {
        for (int i = 0; i < repeat; i++) {
            I x = randy.makeRandom();
            int length = randInt(0, maxRandySize);
            int randomInt = randInt(0, 10);
            Bag b = randBag(length);
            Bag bb = b.addN(x, randomInt);
            if (bb.removeAll(x).member(x)) {
                throw new Exception("Test failed. Elt is still in the bag.");
            }
            if (bb.removeAll(x).getCount(x) != 0) {
                throw new Exception("Test failed. RemoveAll didn't remove them all.");
            }
        }
    }

    // Union, Member & Add
    //   If an element is added to two bags, the union of the bags after
    //   the addition must contain the elemtent, so member must return true.
    public void member_union() throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            int length2 = randInt(0, maxRandySize);
            I x = randy.makeRandom();
            Bag b = randBag(length);
            Bag bb = randBag(length2);
            Bag bX = b.add(x);
            Bag bbX = bb.add(x);
            Bag u1 = (bX.union(bbX));
            Bag u2 = (bbX.union(bX));
            if (!u1.member(x) && !u2.member(x)) {
                throw new Exception("Test failed. X not in unions.");
            }
        }
    }

    // Diff, Equal & isEmptyHuh
    //    If two bags are equal, the difference of the two must be empty.
    //    If two bags are not equal, the difference of the two is non-empty.
    public void diff_equal() throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            Bag bb = randBag(length);
            if (b.diff(bb).isEmptyHuh() && bb.diff(b).isEmptyHuh() && !b.equal(bb) && bb.equal(b)) {
                throw new Exception("Test failed. Bags with no diff not seen as equal");
            } else if (b.equal(bb) && !b.diff(bb).isEmptyHuh() && !bb.diff(b).isEmptyHuh()) {
                throw new Exception("Test failed. Equal bags have a diff.");
            }
        }
    }

    // Inter, Member, Add & isEmptyHuh
    //    If an element is in two bags, then the intersect of the bags
    //    must be non-empty.
    public void inter_member() throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            I x = randy.makeRandom();
            Bag b = randBag(length);
            Bag bb = randBag(length);
            Bag bX = b.add(x);
            Bag bbX = bb.add(x);
            if (bX.member(x) && bbX.member(x)
                    && bX.inter(bbX).isEmptyHuh()) {
                throw new Exception("Test failed. Empty intersection of member'd bags.");
            }
        }
    }

    // Subset & Union
    //   The subset of a bag on the union of it with another bag
    //   must contain the bag. (aka subset must be true)
    public void union_subset() throws Exception {
        for (int i = 0; i < repeat; i++) {
            int length = randInt(0, maxRandySize);
            Bag b = randBag(length);
            Bag bb = randBag(length);
            if (!b.subset(b.union(bb)) || !bb.subset(b.union(bb))) {
                throw new Exception("Test failed. Bag not a subset of bag union'd to another. ");
            }
        }
    }
}
