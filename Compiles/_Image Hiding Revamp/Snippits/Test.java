import java.util.Scanner;  // Import the Scanner class

public class Test {
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);
    System.out.println("number to test: ");
    
    int encodeBits = myObj.nextInt();

    int test_This = (int)(Math.pow(2, encodeBits));
    // ex 2 encoded bits -> 
    int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
    System.out.println("just Math.pow(2, encodebits): " + test_This);
    System.out.println(encodeByteMask);

    // System.out.println();
    int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
    System.out.println("Encode Mask: " + encodeMask);
    // int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
    // int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;

  }
}
