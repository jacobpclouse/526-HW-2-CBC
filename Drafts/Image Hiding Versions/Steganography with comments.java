class Steganography {
    BufferedImage image; // declare a BufferedImage object called "image" as a class variable
    
    public void getMaskedImage(int bits) {
        // get the RGB values of each pixel in the image
        int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));
        
        // calculate the mask to be applied to each pixel
        int maskBits = (int)(Math.pow(2, bits)) - 1 << (8 - bits);
        int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
        
        // apply the mask to each pixel
        for (int i = 0; i < imageRGB.length; i++) {
            imageRGB[i] = imageRGB[i] & mask;
        }
        
        // update the image with the modified pixel values
        image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
    }
    
    public void encode(BufferedImage encodeImage, int encodeBits) {
        // get the RGB values of each pixel in the image and the encodeImage
        int[] encodeRGB = encodeImage.getRGB(0, 0, encodeImage.getWidth(null), encodeImage.getHeight(null), null, 0, encodeImage.getWidth(null));
        int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));
        
        // calculate masks for encoding and decoding the data
        int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
        int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
        int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
        int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
        
        // encode the data into each pixel
        for (int i = 0; i < imageRGB.length; i++) {
            int encodeData = (encodeRGB[i] & encodeMask) >>> (8 - encodeBits);
            imageRGB[i] = (imageRGB[i] & hostMask) | (encodeData & ~hostMask);
        }
        
        // update the image with the modified pixel values
        image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
    }
}
