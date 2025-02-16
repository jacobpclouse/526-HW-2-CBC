import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import javax.imageio.ImageIO;

import javax.swing.*;

public class ImageHiding extends JFrame implements ActionListener
{
 BufferedImage hostImage;
 BufferedImage secretImage;

 JPanel controlPanel;
 JPanel imagePanel;

 JTextField encodeBitsText;
 JButton encodeBitsPlus;
 JButton encodeBitsMinus;

 JTextField nBitsText;
 JButton nBitsPlus;
 JButton nBitsMinus;

 ImageCanvas hostCanvas;
 ImageCanvas secretCanvas;

 Steganography s;

 public BufferedImage getHostImage()
 {
  BufferedImage img = null;

  try
  {
   img = ImageIO.read(new File("host_image.jpg"));
  }
  catch (IOException ioe) { ioe.printStackTrace(); }

  return img;
 }

 public BufferedImage getSecretImage()
 {
  BufferedImage img = null;

  try
  {
   img = ImageIO.read(new File("secret_image.jpg"));
  }
  catch (IOException ioe) { ioe.printStackTrace(); }

  return img;
 }

 public int getBits()
 {
  return Integer.parseInt(encodeBitsText.getText());
 }

 public void actionPerformed(ActionEvent event)
 {
  Object source = event.getSource();

  if (source == encodeBitsPlus)
  {
   int bits = this.getBits() + 1;

   if (bits > 8) { bits = 8; }

   encodeBitsText.setText(Integer.toString(bits));

   s = new Steganography(this.getHostImage());
   s.encode(this.getSecretImage(), bits);

   hostCanvas.setImage(s.getImage());
   hostCanvas.repaint();

   s = new Steganography(this.getSecretImage());
   s.getMaskedImage(bits);
  // s.getMaskedImage(bits,this.radioButton1b()); // changed -- added radioButton 1b

   secretCanvas.setImage(s.getImage());
   secretCanvas.repaint();
  }
  else if (source == encodeBitsMinus)
  {
   int bits = this.getBits() - 1;

   if (bits < 0) { bits = 0; }

   encodeBitsText.setText(Integer.toString(bits));

   s = new Steganography(this.getHostImage());
   s.encode(this.getSecretImage(), bits);

   hostCanvas.setImage(s.getImage());
   hostCanvas.repaint();

   s = new Steganography(this.getSecretImage());
   s.getMaskedImage(bits);
  // s.getMaskedImage(bits,this.radioButton1b()); // changed -- added radioButton 1b


   secretCanvas.setImage(s.getImage());
   secretCanvas.repaint();
  }
 }

 public ImageHiding()
 {
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();
  this.setTitle("Image Hiding Demo");

  Container container = this.getContentPane();

  this.setLayout(layout);

  this.add(new JLabel("Bits to encode into host image:"));

  encodeBitsText = new JTextField("0", 5);
  encodeBitsText.setEditable(false);

  gbc.weightx = -1.0;
  layout.setConstraints(encodeBitsText, gbc);
  this.add(encodeBitsText);

  encodeBitsPlus = new JButton("+");
  encodeBitsPlus.addActionListener(this);

  encodeBitsMinus = new JButton("-");
  encodeBitsMinus.addActionListener(this);

  gbc.weightx = 1.0;
  layout.setConstraints(encodeBitsPlus, gbc);
  this.add(encodeBitsPlus);

  gbc.gridwidth = GridBagConstraints.REMAINDER;
  layout.setConstraints(encodeBitsMinus, gbc);
  this.add(encodeBitsMinus);

  GridBagLayout imageGridbag = new GridBagLayout();
  GridBagConstraints imageGBC = new GridBagConstraints();

  imagePanel = new JPanel();
  imagePanel.setLayout(imageGridbag);



  // my code----------------------
  ButtonGroup group1 = new ButtonGroup();
  // Create two radio buttons and add them to the ButtonGroup and JPanel
  JRadioButton radioButton1a = new JRadioButton("MSB of S");
  JRadioButton radioButton1b = new JRadioButton("LSB of S");
  radioButton1a.setSelected(true); // Set the default selection to radioButton1a
  group1.add(radioButton1a);
  group1.add(radioButton1b);
  imagePanel.add(radioButton1a);
  imagePanel.add(radioButton1b);

    // Add an ItemListener to the first radio button field to detect when a radio button is selected
  ItemListener listener1 = new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        // Print the selected radio button's text to the console
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("Selected option from radio button field 1: " + ((JRadioButton) e.getSource()).getText());
        }
      }
  };
  radioButton1a.addItemListener(listener1);
  radioButton1b.addItemListener(listener1);





    // Create a new ButtonGroup for the second radio button field
  ButtonGroup group2 = new ButtonGroup();

  // Create two radio buttons and add them to the ButtonGroup and JPanel
  JRadioButton radioButton2a = new JRadioButton("MSB of H");
  JRadioButton radioButton2b = new JRadioButton("LSB of H");
  radioButton2a.setSelected(true); // Set the default selection to radioButton2a
  group2.add(radioButton2a);
  group2.add(radioButton2b);
  imagePanel.add(radioButton2a);
  imagePanel.add(radioButton2b);

  // Add an ItemListener to the second radio button field to detect when a radio button is selected
  ItemListener listener2 = new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        // Print the selected radio button's text to the console
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("Selected option from radio button field 2: " + ((JRadioButton) e.getSource()).getText());
        }
      }
  };
  radioButton2a.addItemListener(listener2);
  radioButton2b.addItemListener(listener2);
// - --  -- 


  JLabel hostImageLabel = new JLabel("Host image On Left");
  JLabel secretImageLabel = new JLabel("Secret image On Right");

  imagePanel.add(hostImageLabel);

  imageGBC.gridwidth = GridBagConstraints.REMAINDER;
  imageGridbag.setConstraints(secretImageLabel, imageGBC);
  imagePanel.add(secretImageLabel);

  hostCanvas = new ImageCanvas(this.getHostImage());  
  secretCanvas = new ImageCanvas(this.getSecretImage());

  imagePanel.add(hostCanvas);
  imagePanel.add(secretCanvas);

  gbc.gridwidth = GridBagConstraints.REMAINDER;
  layout.setConstraints(imagePanel, gbc);
  this.add(imagePanel);

  Steganography host = new Steganography(this.getHostImage());
  host.encode(this.getSecretImage(), this.getBits());
  hostCanvas.setImage(host.getImage());

  Steganography secret = new Steganography(this.getSecretImage());
  secret.getMaskedImage(this.getBits());
  // secret.getMaskedImage(this.getBits(),this.radioButton1b()); // changed -- added radioButton 1b
  secretCanvas.setImage(secret.getImage());

  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.pack();

  this.setVisible(true);
 }

 public static void main(String[] args)
 {
  ImageHiding frame = new ImageHiding();
  frame.setVisible(true);
 }

 public class ImageCanvas extends JPanel
 { 
  Image img;

  public void paintComponent(Graphics g)
  {
   g.drawImage(img, 0, 0, this);
  }

  public void setImage(Image img)
  {
   this.img = img;
  }

  public ImageCanvas(Image img)
  {
   this.img = img;

   this.setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));
  }
 }
}

class Steganography
{
 BufferedImage image;
// FOR ORIG IMAGE (IE HOST IMAGE) -- should be LSB ORIG
 public void getMaskedImage(int bits)
 {
  int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));

  // LSB
    System.out.println("We know that radio button 1b is selected and LSB of H is being executed");
    int maskBits = (int)(Math.pow(2, bits)) - 1 << (8 - bits);
    int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;



// // if lsb button is selected
//   if (ImageHiding.radioButton1b.isSelected()) {
//   // LSB
//     System.out.println("We know that radio button 1b is selected and LSB of H is being executed");
//     int maskBits = (int)(Math.pow(2, bits)) - 1 << (8 - bits);
//     int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
//   // --- 
//   } else {
//     // MSB - My code
//     System.out.println("ELSE radio button 1a is selected and MSB of H is being executed");
//     int maskBits = (int)(Math.pow(2, bits)) - 1;
//     int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
//   };






  for (int i = 0; i < imageRGB.length; i++)
  {
   imageRGB[i] = imageRGB[i] & mask;
  }

  image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
 }
// PRETTY SURE ITS BELOW FOR LSB / MSB -- original does: Hide MSB of Secret in LSB of Host
// HE CALLED OUT ENCODE IN CLASS AS THE ONE YOU SHOULD FOCUS ON
// encoded image being passed in looks to always be the secret image
 public void encode(BufferedImage encodeImage, int encodeBits)
 {
  // SHOULD BE MSB
  int[] encodeRGB = encodeImage.getRGB(0, 0, encodeImage.getWidth(null), encodeImage.getHeight(null), null, 0, encodeImage.getWidth(null));
  int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));
  System.out.println("  Encoded RGB: " + encodeRGB);
  System.out.println("    Image RGB: " + imageRGB);
// BELOW IS IT!!! MSB S 
  int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
  int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
  System.out.println("encodeByteMask: " + encodeByteMask);
  System.out.println("    encodeMask: " + encodeMask);

  int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
  int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
  System.out.println("decodeByteMask: " + decodeByteMask);
  System.out.println("      hostMask: " + hostMask);
  System.out.println(" ");

// --- HAVE IT PRINT OUT THE VALUES BEFORE AND AFTER SHIFT SO YOU SEE WHAT IS GOING ON

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
