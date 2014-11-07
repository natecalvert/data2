package data2;

import java.util.Random;

/**
 * Randomizes Booleans
 * 
 * @author Nate Calvert
 */
public class RandomBoolean implements Randoms<Boolean> {

    public Boolean makeRandom() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        return randomNum == 0;
    }
}
