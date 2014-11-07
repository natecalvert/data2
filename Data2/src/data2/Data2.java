package data2;

import static data2.Tests.*;

/**
 * Runs Tests
 * 
 * @author Nate Calvert
 */
public class Data2 {

    public static void main(String[] args) throws Exception {

        int runTests = 1000;
        for (int i = 0; i < runTests; i++) {

            Tests inty = new Tests(new RandomInteger());
            Tests stringy = new Tests(new RandomString());
            Tests booly = new Tests(new RandomBoolean());

            int n = randInt(0, 1);
            inty.empty_isEmptyHuh(n);
            stringy.empty_isEmptyHuh(n);
            booly.empty_isEmptyHuh(n);

            inty.isEmptyHuh_cardinality();
            stringy.isEmptyHuh_cardinality();
            booly.isEmptyHuh_cardinality();

            inty.cardinality_remove_getCount();
            stringy.cardinality_remove_getCount();
            booly.cardinality_remove_getCount();

            inty.cardinality_add();
            stringy.cardinality_add();
            booly.cardinality_add();

            int m = randInt(0, 10);
            inty.cardinality_addN(m);
            stringy.cardinality_addN(m);
            booly.cardinality_addN(m);

            inty.add_member();
            stringy.add_member();
            booly.add_member();

            inty.add_remove_equal();
            stringy.add_remove_equal();
            booly.add_remove_equal();

            int k = randInt(0, 5);
            inty.addN_removeN_equal(k);
            stringy.addN_removeN_equal(k);
            booly.addN_removeN_equal(k);

            inty.member_removeAll();
            stringy.member_removeAll();
            booly.member_removeAll();

            inty.member_union();
            stringy.member_union();
            booly.member_union();

            inty.diff_equal();
            stringy.diff_equal();
            booly.diff_equal();

            inty.inter_member();
            stringy.inter_member();
            booly.inter_member();

            inty.union_subset();
            stringy.union_subset();
            booly.union_subset();
        }
        System.out.println("");
        System.out.println("Tests passed " + runTests * repeat + " times.");
        System.out.println("");
    }
}
