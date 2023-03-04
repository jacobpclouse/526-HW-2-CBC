import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageEncoder {

    public static void main(String[] args) throws IOException {
        // Load the host and secret images
        BufferedImage hostImage = ImageIO.read(new File("hostImage.png"));
        BufferedImage secretImage = ImageIO.read(new File("secretImage.png"));
        
        // Option 1: encode MSB of secret image in LSB of host image
        BufferedImage encodedImage1 = encodeMSBInLSB(hostImage, secretImage);
        ImageIO.write(encodedImage1, "png", new File("encodedImage1.png"));

        // Option 2: encode MSB of secret image in MSB of host image
        BufferedImage encodedImage2 = encodeMSBInMSB(hostImage, secretImage);
        ImageIO.write(encodedImage2, "png", new File("encodedImage2.png"));
        
        // Option 3: encode LSB of secret image in LSB of host image
        BufferedImage encodedImage3 = encodeLSBInLSB(hostImage, secretImage);
        ImageIO.write(encodedImage3, "png", new File("encodedImage3.png"));
        
        // Option 4: encode LSB of secret image in MSB of host image
        BufferedImage encodedImage4 = encodeLSBInMSB(hostImage, secretImage);
        ImageIO.write(encodedImage4, "png", new File("encodedImage4.png"));
    }




    private static BufferedImage encodeMSBInLSB(BufferedImage hostImage, BufferedImage secretImage) {
        BufferedImage encodedImage = new BufferedImage(hostImage.getWidth(), hostImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < hostImage.getWidth(); x++) {
            for (int y = 0; y < hostImage.getHeight(); y++) {
                Color hostColor = new Color(hostImage.getRGB(x, y));
                Color secretColor = new Color(secretImage.getRGB(x, y));
                
                int encodedRed = (hostColor.getRed() & 254) | ((secretColor.getRed() & 128) >> 7);
                int encodedGreen = (hostColor.getGreen() & 254) | ((secretColor.getGreen() & 128) >> 7);
                int encodedBlue = (hostColor.getBlue() & 254) | ((secretColor.getBlue() & 128) >> 7);
                
                Color encodedColor = new Color(encodedRed, encodedGreen, encodedBlue); // ... to lsb
                encodedImage.setRGB(x, y, encodedColor.getRGB());
            }
        }
        
        return encodedImage;
    }
    



    private static BufferedImage encodeMSBInMSB(BufferedImage hostImage, BufferedImage secretImage) {
        BufferedImage encodedImage = new BufferedImage(hostImage.getWidth(), hostImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < hostImage.getWidth(); x++) {
            for (int y = 0; y < hostImage.getHeight(); y++) {
                Color hostColor = new Color(hostImage.getRGB(x, y));
                Color secretColor = new Color(secretImage.getRGB(x, y));
                
                int encodedRed = (hostColor.getRed() & 254) | ((secretColor.getRed() & 128) >> 7); // MSB to ...  128 >>
                int encodedGreen = (hostColor.getGreen() & 254) | ((secretColor.getGreen() & 128) >> 7);
                int encodedBlue = (hostColor.getBlue() & 254) | ((secretColor.getBlue() & 128) >> 7);
            int encodedAlpha = (hostColor.getAlpha() & 254) | ((secretColor.getAlpha() & 128) >> 7);
            
            Color encodedColor = new Color(encodedRed, encodedGreen, encodedBlue, encodedAlpha);
            encodedImage.setRGB(x, y, encodedColor.getRGB());
        }
    }
    
    return encodedImage;
}




private static BufferedImage encodeLSBInLSB(BufferedImage hostImage, BufferedImage secretImage) {
    BufferedImage encodedImage = new BufferedImage(hostImage.getWidth(), hostImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    for (int x = 0; x < hostImage.getWidth(); x++) {
        for (int y = 0; y < hostImage.getHeight(); y++) {
            Color hostColor = new Color(hostImage.getRGB(x, y));
            Color secretColor = new Color(secretImage.getRGB(x, y));
            
            int encodedRed = (hostColor.getRed() & 254) | ((secretColor.getRed() & 1) << 7);
            int encodedGreen = (hostColor.getGreen() & 254) | ((secretColor.getGreen() & 1) << 7);
            int encodedBlue = (hostColor.getBlue() & 254) | ((secretColor.getBlue() & 1) << 7);
            
            Color encodedColor = new Color(encodedRed, encodedGreen, encodedBlue); // ... to lsb
            encodedImage.setRGB(x, y, encodedColor.getRGB());
        }
    }
    
    return encodedImage;
}




private static BufferedImage encodeLSBInMSB(BufferedImage hostImage, BufferedImage secretImage) {
    BufferedImage encodedImage = new BufferedImage(hostImage.getWidth(), hostImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    for (int x = 0; x < hostImage.getWidth(); x++) {
        for (int y = 0; y < hostImage.getHeight(); y++) {
            Color hostColor = new Color(hostImage.getRGB(x, y));
            Color secretColor = new Color(secretImage.getRGB(x, y));
            
            int encodedRed = (hostColor.getRed() & 254) | ((secretColor.getRed() & 1) << 7); // LSB to ... 
            int encodedGreen = (hostColor.getGreen() & 254) | ((secretColor.getGreen() & 1) << 7);
            int encodedBlue = (hostColor.getBlue() & 254) | ((secretColor.getBlue() & 1) << 7);
            int encodedAlpha = (hostColor.getAlpha() & 254) | ((secretColor.getAlpha() & 1) << 7);
            
            Color encodedColor = new Color(encodedRed, encodedGreen, encodedBlue, encodedAlpha);
            encodedImage.setRGB(x, y, encodedColor.getRGB());
        }
    }
    
    return encodedImage;
}
