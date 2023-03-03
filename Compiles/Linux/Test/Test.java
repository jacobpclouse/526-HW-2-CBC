import java.util.Random;
import java.io.*;
import java.util.*;
import java.security.SecureRandom;

public class Test {

    public static void main(String[] args){
        System.out.println("CBC Test");
       
        Random rd = new Random(); // creating Random object, setting max and min to 0 and 9
            int max=9,min=0;

        // generate new array of block size 16 - empty
        byte[] IV = new byte[16]; // needs to be set outside of the block, cant be called twice ***
        byte[] XORed_IV = new byte[16]; // needs to be set outside of the block, cant be called twice ***




        // generate random numbers and assign them to the array
        // FROM SITE: https://www.tutorialspoint.com/generate-a-random-array-of-integers-in-java
        // AND https://www.geeksforgeeks.org/generating-random-numbers-in-java/
        for (int i = 0; i < IV.length; i++) {
            IV[i] = (byte) (rd.nextInt(max - min + 1) + min); // storing random integers in an array -- need to be 0 - 9
            System.out.println(IV[i]); // printing each array element
        }
            // can you XOR with any number 1 to 9? or does it have to be 1s and 0s?

        System.out.println(IV); // print out array at end

        // XOR function: 
        for (int i = 0; i < IV.length; i++) {
            XORed_IV[i] = (byte) (IV[i] ^ IV[i]);
        }
        // COMPAIRING THE BYTES OBJECTS IN ARRAY, NEED TO TRANSPOSE THE BLOCK INTO AN ARRAY OBJECT FIRST

        System.out.println("The XORed Value of " + IV + " and " + IV + " is: " + XORed_IV +" !");

        // Override the Initial IV value and XORED Value
        for (int i = 0; i < IV.length; i++) {
            IV[i] = (byte) (XORed_IV[i]); // Overide Orig Value
        }

        System.out.println("Reset Values for IV: " + IV + " and the XORed_IV: " + XORed_IV);

    }


}