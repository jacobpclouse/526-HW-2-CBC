import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/*
 * How to compile and run this program:
 * 1) Open a bash terminal or gitbash terminal in the parent directory of this program
 * 2) Compile IOC.java with this command: javac IOC.java
 * 3) Copy the output files that you want to measure the IOC of into the same directory as IOC.java
 * 4) Run this program with this command: java IOC.java
 * 5) Paste in the name of the file you want to measure (ex: test_encrypted_file.txt)
 * 6) The console should print out your IOC value
 * 
 */


// needs to stay named IOC to run
public class IOC {
    // computes IOC - this formula / code was found online at: https://www.dcode.fr/index-coincidence
    public static double get_IOC(byte[] ciphertext) {
        int CipherLength = ciphertext.length;
        int[] frequencys = new int[256];
        double IndexOfCoincidence = 0;
        for (byte b : ciphertext) {
            frequencys[Byte.toUnsignedInt(b)]++;
        }
        for (int firstPart : frequencys) 
        {
            IndexOfCoincidence += firstPart * (firstPart - 1);
        }
        IndexOfCoincidence = IndexOfCoincidence / (CipherLength * (CipherLength - 1));

        System.out.println("IOC = " + IndexOfCoincidence);
        return IndexOfCoincidence;
    }
    
    public static void main(String[] args) throws IOException {
        // get file from user
        String filename = System.console().readLine("Input filename = ");
    
        // open file, read it, calc IOC and output the results to console
        byte[] ciphertext_bytes = Files.readAllBytes(Paths.get(filename));
        get_IOC(ciphertext_bytes);
    }
}