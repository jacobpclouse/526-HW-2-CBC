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
        byte[] Combo_Of_IV_n_Counter = new byte[16]; // needs to be set outside of the block, cant be called twice ***

        // generate random numbers and assign them to the array - Set before encrypt for loop
        for (int b = 0; b < IV_Noonce.length; b++) {
            IV_Noonce[b] = (byte) (rd.nextInt(max - min + 1) + min); // storing random integers in an array -- need to be 0 - 9
        }





        // Simulated encrypt block
        // for (int z = 0; z < 100; z++) {
            
            


        // }

    }


}