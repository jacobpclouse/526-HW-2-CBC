import java.util.Random;
import java.io.*;
import java.util.*;
import java.security.SecureRandom;

public class OFB_Test_Snip {

    public static void main(String[] args){
        System.out.println("OFB Test");
       
        Random rd = new Random(); // creating Random object, setting max and min to 0 and 9
            int max=9,min=0;

        // generate new array of block size 16 - empty
        byte[] IV_Noonce = new byte[16]; // needs to be set outside of the block, cant be called twice ***
        byte[] Counter_for_Noonce = new byte[16]; // needs to be set outside of the block, cant be called twice ***
        // byte[] Combo_Of_IV_n_Counter = new byte[16]; // needs to be set outside of the block, cant be called twice ***
        int current_Val = 0;
        String String_Noonce = new String();
        // generate random numbers and assign them to the array - Set before encrypt for loop
        for (int b = 0; b < IV_Noonce.length; b++) {
            current_Val = (rd.nextInt(max - min + 1) + min);
            IV_Noonce[b] = (byte) (current_Val); // storing random integers in an array -- need to be 0 - 9
            String_Noonce += current_Val;
        }
        // Convert Nonce to integers for our incriment operation + string 
        long Long_Noonce = Long.parseLong(String_Noonce);
        // System.out.println(IV_Noonce);
        // System.out.println(String_Noonce);
        // System.out.println(Long_Noonce);
        



        // SAMPLE
        byte[] bloc = Arrays.copyOf(IV_Noonce,16);
        System.out.println("BLOCK: ");
        System.out.println(bloc);
        // END SAMPLE - do not include


        // Simulated encrypt block
        for (int i = 99; i < 100; i++) {
            // Getting current index to sync our counter, converting from string to bytes and cutting it at 16
            Long_Noonce += i;
            System.out.println(Long_Noonce);

            String_Noonce = String.format("%016d", Long_Noonce);
            System.out.println(String_Noonce);

            IV_Noonce = String_Noonce.getBytes();
            IV_Noonce = Arrays.copyOf(IV_Noonce, 16);
            System.out.println(IV_Noonce);

            // ENCRYPTION HERE
            // encrypt function (IV_NONCE, key)


           
            // XOR function: Reverse
            for (int a = 0; a < bloc.length; a++) {
                bloc[a] = (byte) (IV_Noonce[a] ^ bloc[a]);
                System.out.println(a);
            }
/*
*/
            // SET NEW IV TO output from the encryption
            System.out.println('Done!');
            


        }

    }


}