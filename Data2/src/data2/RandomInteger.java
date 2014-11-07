package data2;

import java.util.Random;

/**
 * Randomizes Integers
 * 
 * @author Nate Calvert
 */
public class RandomInteger implements Randoms<Integer> {

    int min = 0;
    int max = 100;
    
    public Integer makeRandom() {
        Random randy = new Random();
        int randomNum = randy.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
