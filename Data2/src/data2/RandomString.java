package data2;

import java.util.Random;

/**
 * Randomizes Strings
 * 
 * @author Nate Calvert
 */
public class RandomString implements Randoms<String> {

    int maxLength = 10;

    String stringElts = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    Random rand = new Random();

    public String makeRandom() {
        int length = new Random().nextInt(maxLength);
        // StringBuilders! aka "What I learned in 102."
        StringBuilder newString = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            newString.append(stringElts.charAt(new Random().nextInt(stringElts.length())));
        }
        return newString.toString();
    }
}
