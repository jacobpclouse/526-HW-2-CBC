import java.util.Random;
import java.io.*;
import java.util.*;
import java.security.SecureRandom;

public class test {
    // basically a counter, it works!
    public static void main(String[] args){
        System.out.println("Java Test");

        int key_integer = 000000000000;
        String key_String = String.valueOf(key_integer);
        

        System.out.println(key_integer);
        // generate random numbers and assign them to the array - Set before encrypt for loop
        for (int b = 0; b < 100; b++) {
            // key_integer = 000000000000;
            // key_integer += b;
            key_String = String.format("%020d", b);
            System.out.println("");
            System.out.println(key_integer);
            System.out.println(key_String);
            // key = key_String.getBytes();
            

        }

        byte[] key = key_String.getBytes();
        key = Arrays.copyOf(key, 16);
        System.out.println(key_integer);
        System.out.println(key);
        
    }
            
            


}


