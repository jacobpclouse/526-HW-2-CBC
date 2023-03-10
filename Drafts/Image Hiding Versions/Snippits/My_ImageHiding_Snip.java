class Steganography
{
 BufferedImage image;
// FOR ORIG IMAGE (IE HOST IMAGE) -- should be LSB ORIG
 public void getMaskedImage(int bits) // <-- add an extra variable for our radio buttons
 {
  int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));


// TO LSB -- ORIGINAL
  int maskBits = (int)(Math.pow(2, bits)) - 1 << (8 - bits);
  int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
// --- 

// TO MSB - mine
  int maskBits = (int)(Math.pow(2, bits)) - 1; 
  int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;


  for (int i = 0; i < imageRGB.length; i++)
  {
   imageRGB[i] = imageRGB[i] & mask;
  }

  image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
 }
// PRETTY SURE ITS BELOW FOR LSB / MSB -- original does: Hide MSB of Secret in LSB of Host
// HE CALLED OUT ENCODE IN CLASS AS THE ONE YOU SHOULD FOCUS ON
// encoded image being passed in looks to always be the secret image
 public void encode(BufferedImage encodeImage, int encodeBits) // <-- add an extra variable for our radio buttons
 {

  int[] encodeRGB = encodeImage.getRGB(0, 0, encodeImage.getWidth(null), encodeImage.getHeight(null), null, 0, encodeImage.getWidth(null));
  int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));

// MSB to ... --- ORIGINAL S 
  int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
  int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;

  int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
  int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;

// --- 

// LSB to ... --- MINE
  int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1;
  int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;

  int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
  int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
// ---

  for (int i = 0; i < imageRGB.length; i++)
  {
   int encodeData = (encodeRGB[i] & encodeMask) >>> (8 - encodeBits);
   imageRGB[i] = (imageRGB[i] & hostMask) | (encodeData & ~hostMask);
  }

  image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
 }

 public Image getImage()
 {
  return image;
 }

 public Steganography(BufferedImage image)
 {
  this.image = image;
 }
}
