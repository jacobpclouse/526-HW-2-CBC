// Importing Libraries / Modules
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
// import java.util.Arrays;

// adjust the code

// main
public class IOC {
    // --- Function that computes IOC from ciphertext read from binary file ---
    public static double compute_index_of_coincidence(byte[] ciphertext) {
        int n = ciphertext.length;
        int[] freqs = new int[256];
        for (byte b : ciphertext) {
            freqs[Byte.toUnsignedInt(b)]++;
        }
        double ic = 0;
        for (int freq : freqs) {
            ic += freq * (freq - 1);
        }
        ic = ic / (n * (n - 1));
        return ic;
    }
    
    public static void main(String[] args) throws IOException {
        String filename = System.console().readLine("Give me the filename: ");
        String output_name = System.console().readLine("What do you want the output to be called?: ");
    
        // Read the contents of the file into a byte array
        byte[] ciphertext_bytes = Files.readAllBytes(Paths.get(filename));
    
        // Compute the index of coincidence
        double ic = compute_index_of_coincidence(ciphertext_bytes);
    
        // Print the result
        System.out.println("The index of coincidence is: " + ic);
    
        // Save string to file
        String text = String.format("The index of coincidence for %s is: %f", filename, ic);
        Files.write(Paths.get("My_IC_" + output_name + ".txt"), text.getBytes());
    }
}