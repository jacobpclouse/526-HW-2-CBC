import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

import javax.imageio.ImageIO;

import javax.swing.*;

import java.util.Scanner; // Import the Scanner class

public class ImageHiding extends JFrame implements ActionListener {
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

  JRadioButton hostIsLSBButton, hostIsMSBButton, secretIsLSBButton, secretIsMSBButton, debugModeIsTrueButton, debugModeIsFalseButton;
  ButtonGroup hostButtonGroup, secretButtonGroup, debugButtonGroup;
  public boolean HostIsLSBVar = true, SecretIsLSBVar = true, DebugModeIsOn = false;

  Steganography s;

  public BufferedImage getHostImage() {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new File("host_image.jpg"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return img;
  }

  public BufferedImage getSecretImage() {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new File("secret_image.jpg"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return img;
  }

  public int getBits() {
    return Integer.parseInt(encodeBitsText.getText());
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    Object actionBoi = event.getActionCommand();

    if (source == encodeBitsPlus) {
      int bits = this.getBits() + 1;

      if (bits > 8) {
        bits = 8;
      }

      encodeBitsText.setText(Integer.toString(bits));

      s = new Steganography(this.getHostImage());
      s.encode(this.getSecretImage(), bits,SecretIsLSBVar,HostIsLSBVar);

      hostCanvas.setImage(s.getImage());
      hostCanvas.repaint();

      s = new Steganography(this.getSecretImage());
      s.getMaskedImage(bits,HostIsLSBVar,DebugModeIsOn);

      secretCanvas.setImage(s.getImage());
      secretCanvas.repaint();
    } else if (source == encodeBitsMinus) {
      int bits = this.getBits() - 1;

      if (bits < 0) {
        bits = 0;
      }

      encodeBitsText.setText(Integer.toString(bits));

      s = new Steganography(this.getHostImage());
      s.encode(this.getSecretImage(), bits,SecretIsLSBVar,HostIsLSBVar);

      hostCanvas.setImage(s.getImage());
      hostCanvas.repaint();

      s = new Steganography(this.getSecretImage());
      s.getMaskedImage(bits,HostIsLSBVar,DebugModeIsOn);

      secretCanvas.setImage(s.getImage());
      secretCanvas.repaint();
    }

    // Update the boolean variables based on the selected radio buttons
    if ("HostLSB".equals(actionBoi)) {
      HostIsLSBVar = true;
      System.out.println("Case 1: HOSTLBS TRUE!");

    } else if ("HostMSB".equals(actionBoi)) {
      HostIsLSBVar = false;
      System.out.println("Case 2: HOSTLBS FALSE!");

    } else if ("SecretLSB".equals(actionBoi)) {
      SecretIsLSBVar = true;
      System.out.println("Case 3: SECRETLBS TRUE!");

    } else if ("SecretMSB".equals(actionBoi)) {
      SecretIsLSBVar = false;
      System.out.println("Case 4: SECRETLBS FALSE!");
    }


    // Update the boolean variables based on the selected radio buttons -- debug mode
    if ("debugTrue".equals(actionBoi)) {
      DebugModeIsOn = true;
      System.out.println("*RUNNING IN DEBUG MODE*");

    } else if ("debugFalse".equals(actionBoi)) {
      DebugModeIsOn = false;
      System.out.println("~Debug mode is not active~");

    } 
  }

  public ImageHiding() {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    this.setTitle("Jacob Clouse Image Hiding: ");

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

    // Create the radio buttons and button groups
    hostIsLSBButton = new JRadioButton("Host is LSB", true);
    hostIsLSBButton.setActionCommand("HostLSB");
    hostIsLSBButton.addActionListener(this);

    hostIsMSBButton = new JRadioButton("Host is MSB", false);
    hostIsMSBButton.setActionCommand("HostMSB");
    hostIsMSBButton.addActionListener(this);

    secretIsLSBButton = new JRadioButton("Secret is LSB", true);
    secretIsLSBButton.setActionCommand("SecretLSB");
    secretIsLSBButton.addActionListener(this);

    secretIsMSBButton = new JRadioButton("Secret is MSB", false);
    secretIsMSBButton.setActionCommand("SecretMSB");
    secretIsMSBButton.addActionListener(this);

    // - debug mode button
    debugModeIsTrueButton = new JRadioButton("Debug Mode Is True", false);
    debugModeIsTrueButton.setActionCommand("debugTrue");
    debugModeIsTrueButton.addActionListener(this);

    debugModeIsFalseButton = new JRadioButton("Debug Mode Is False", true);
    secretIsMSBButton.setActionCommand("debugFalse");
    secretIsMSBButton.addActionListener(this);
    // - end debug

    hostButtonGroup = new ButtonGroup();
    hostButtonGroup.add(hostIsLSBButton);
    hostButtonGroup.add(hostIsMSBButton);

    secretButtonGroup = new ButtonGroup();
    secretButtonGroup.add(secretIsLSBButton);
    secretButtonGroup.add(secretIsMSBButton);

    debugButtonGroup = new ButtonGroup();
    debugButtonGroup.add(debugModeIsTrueButton);
    debugButtonGroup.add(debugModeIsFalseButton);

    imagePanel = new JPanel();
    imagePanel.setLayout(imageGridbag);
    //
    imagePanel.add(hostIsLSBButton);
    imagePanel.add(hostIsMSBButton);
    imagePanel.add(secretIsLSBButton);
    imagePanel.add(secretIsMSBButton);

    imagePanel.add(debugModeIsTrueButton);
    imagePanel.add(debugModeIsFalseButton);

    //
    JLabel hostImageLabel = new JLabel(" Host image ON LEFT ");
    JLabel secretImageLabel = new JLabel(" Secret image ON RIGHT ");

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
    host.encode(this.getSecretImage(), this.getBits(),SecretIsLSBVar,HostIsLSBVar);
    hostCanvas.setImage(host.getImage());

    Steganography secret = new Steganography(this.getSecretImage());
    secret.getMaskedImage(this.getBits(),HostIsLSBVar,DebugModeIsOn);
    secretCanvas.setImage(secret.getImage());

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();

    this.setVisible(true);
  }

  public static void main(String[] args) {
    ImageHiding frame = new ImageHiding();
    frame.setVisible(true);
  }

  public class ImageCanvas extends JPanel {
    Image img;

    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, this);
    }

    public void setImage(Image img) {
      this.img = img;
    }

    public ImageCanvas(Image img) {
      this.img = img;

      this.setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));
    }
  }
}

class Steganography {
  BufferedImage image;
  // boolean currentHostLSB;
  // boolean currentSecretLSB;

  public void getMaskedImage(int bits, boolean currentHostLSB, boolean isDebugOn) // part 1 HOST
  {
    int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));
/* This part of Steganography shouldn't affect anything, but I figured I would give it a try just in case, just turn off debug mode to skip this */  
    if (isDebugOn == false) {
    // This is how it should be run - pradeep said that we don't need to edit this
      System.out.println("Original Config: getMaskedImage");
      int maskBits = (int) (Math.pow(2, bits)) - 1 << (8 - bits);
      int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
      for (int i = 0; i < imageRGB.length; i++) {
        imageRGB[i] = imageRGB[i] & mask;
      }
      image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
    } else {
        if (currentHostLSB == true) {
          System.out.println("Debug: HOST is LSB - orig");
      // // LSB
          int maskBits = (int) (Math.pow(2, bits)) - 1 << (8 - bits);
          int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
          for (int i = 0; i < imageRGB.length; i++) {
            imageRGB[i] = imageRGB[i] & mask;
          }
        image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));

        } else if (currentHostLSB == false) {
          System.out.println("Debug: HOST is NOT LSB (its MSB)");
      // // MSB - MY CODE
          int maskBits = (int) (Math.pow(2, bits)) - 1;
          int mask = (maskBits << 24) | (maskBits << 16) | (maskBits << 8) | maskBits;
          for (int i = 0; i < imageRGB.length; i++) {
            imageRGB[i] = imageRGB[i] & mask;
          }
          image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
        }
    }


	/*
    // // System.out.println("HOST LSB: " + currentHostLSB);
    // // my code
    // // Scanner myObj = new Scanner(System.in);
    // // System.out.println("___________________________");
    // // System.out.println("*Part 1: Setting Host bits*");
    // // System.out.println("(true or false) HOST is LSB: ");
    // // boolean myHostBits = myObj.nextBoolean();
    // // this is working!!! LSB or MSB
 
	*/
  }

  public void encode(BufferedImage encodeImage, int encodeBits, boolean currentSecretLSB, boolean currentHostLSB) // part 2
  {
    int[] encodeRGB = encodeImage.getRGB(0, 0, encodeImage.getWidth(null), encodeImage.getHeight(null), null, 0,
        encodeImage.getWidth(null));
    int[] imageRGB = image.getRGB(0, 0, image.getWidth(null), image.getHeight(null), null, 0, image.getWidth(null));
// ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----


	// calculate the mask for encoding and decoding based on the case number
	int encodeByteMask, encodeMask, decodeByteMask, hostMask;
	if (currentHostLSB == true && currentSecretLSB == false) {
		// case 1: hide MSB of secret image in LSB of host image
		encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1 << (8 - encodeBits); // MSB for Secret Image
		encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
		decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
		hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
	} else if (currentHostLSB == false && currentSecretLSB == false) {
		// case 2: hide MSB of secret image in MSB of host image
		encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1 << (8 - encodeBits); // MSB for Secret Image
		encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
		decodeByteMask = 0xFF << (8 - encodeBits);
		hostMask = decodeByteMask << 24 | decodeByteMask << 16 | decodeByteMask << 8 | decodeByteMask;
	} else if (currentHostLSB == true && currentSecretLSB == true) {
		// case 3: hide LSB of secret image in LSB of host image
		encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1; // LSB for Secret Image
		encodeMask = encodeByteMask << (8 - encodeBits);
		decodeByteMask = ~encodeByteMask;
		hostMask = decodeByteMask << 24 | decodeByteMask << 16 | decodeByteMask << 8 | decodeByteMask;
	} else if (currentHostLSB == false && currentSecretLSB == true){
		// case 4: hide LSB of secret image in MSB of host image
		encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1; // LSB for Secret Image
		encodeMask = encodeByteMask << 24 | encodeByteMask << 16 | encodeByteMask << 8 | encodeByteMask;
		decodeByteMask = (~encodeByteMask) & 0xFF;
		hostMask = decodeByteMask << 24 | decodeByteMask << 16 | decodeByteMask << 8 | 0xFF;
	} else {
		System.out.println("HIT THE DEFAULT STATEMENT - SOMETHING IS WRONG");
		// default: hide MSB of secret image in LSB of host image
		encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
		encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
		decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
		hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
    }		
	
    // apply the encoding mask to the secret image pixel data
    for (int i = 0; i < encodeRGB.length; i++) {
        encodeRGB[i] = encodeRGB[i] & encodeMask;
    }
	
	// apply the decoding mask to the host image pixel data and encode the secret image data
    int encodeIndex = 0;
    for (int i = 0; i < imageRGB.length; i++) {
        int hostByte = imageRGB[i] >>> 24 & 0xFF;
        int secretByte = encodeRGB[encodeIndex] >>> (8 - encodeBits) & encodeByteMask;

        // encode the secret byte into the host byte
        int encodedByte = (secretByte << (8 - encodeBits)) | (hostByte & hostMask);
        imageRGB[i] = (imageRGB[i] & ~0xFF000000) | (encodedByte << 24);

        // increment the secret index
        encodeIndex = (encodeIndex + 1) % encodeRGB.length;
    }
	
	// output the result
    image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));
// ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

/*
    System.out.println("SECRET is NOT LSB (its MSB - orig)");
    int encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
    int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;


// -------------------------------------------------------------------------------------------------------------
	// // int decodeByteMask;
	// // if (useLSB) {
		// // decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
	// // } else {
		// // decodeByteMask = encodeByteMask;
	// // }
	// // int hostMask;
	// // if (useMSB) {
		// // hostMask = 0xFF000000;
	// // } else {
		// // hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
	// // }

	// // for (int i = 0; i < imageRGB.length; i++) {
		// // int encodeData;
		// // if (useLSB) {
			// // encodeData = encodeRGB[i] & encodeByteMask;
		// // } else {
			// // encodeData = (encodeRGB[i] & encodeMask) >>> (8 - encodeBits);
		// // }
		// // if (useMSB) {
			// // encodeData <<= 24 - encodeBits;
		// // }
		// // imageRGB[i] = (imageRGB[i] & hostMask) | (encodeData & ~hostMask);
	// // }
*/


// THIS IS WHERE THE MONEY IS
/* 
 // ORIGINAL
    System.out.println("SECRET is NOT LSB (its MSB - orig)");
    int encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
    int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
	

    int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
    int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;

	for (int i = 0; i < imageRGB.length; i++) {
      int encodeData = (encodeRGB[i] & encodeMask) >>> (8 - encodeBits);
      imageRGB[i] = (imageRGB[i] & hostMask) | (encodeData & ~hostMask);
    }
*/




/*
    // // my code
    // // Scanner myObj2 = new Scanner(System.in);
    // // System.out.println("___________________________");
    // // System.out.println("^Part 2: Setting Secret bits^");
    // // System.out.println("(true or false) SECRET is LSB: ");
    // // boolean mySecretBits = myObj2.nextBoolean();

    // if (currentSecretLSB == true) {
      // System.out.println("SECRET is LSB");
      // // LSB - MY CODE
      // int encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1;
      // int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
      // int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
      // int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
      // for (int i = 0; i < imageRGB.length; i++) {
        // int encodeData = (encodeRGB[i] & encodeMask) >>> (8 - encodeBits);
        // imageRGB[i] = (imageRGB[i] & hostMask) | (encodeData & ~hostMask);
      // }
      // image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));

    // } else if (currentSecretLSB == false) {
      // // MSB - orig
      // System.out.println("SECRET is NOT LSB (its MSB - orig)");
      // int encodeByteMask = (int) (Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
      // int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;

      // int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
      // int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;

      // for (int i = 0; i < imageRGB.length; i++) {
        // int encodeData = (encodeRGB[i] & encodeMask) >>> (8 - encodeBits);
        // imageRGB[i] = (imageRGB[i] & hostMask) | (encodeData & ~hostMask);
      // }

      // image.setRGB(0, 0, image.getWidth(null), image.getHeight(null), imageRGB, 0, image.getWidth(null));

    // }
*/
	
	
  }

  public Image getImage() {
    return image;
  }

  public Steganography(BufferedImage image) {
    this.image = image;
  }
}
