import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonExample {
   public static void main(String[] args) {
      // Create a new JFrame
      JFrame frame = new JFrame("Radio Button Example");

      // Create a new JPanel
      JPanel panel = new JPanel();

      // Create a new ButtonGroup for the first radio button field
      ButtonGroup group1 = new ButtonGroup();

      // Create two radio buttons and add them to the ButtonGroup and JPanel
      JRadioButton radioButton1a = new JRadioButton("Option 1a");
      JRadioButton radioButton1b = new JRadioButton("Option 1b");
      radioButton1a.setSelected(true); // Set the default selection to radioButton1a
      group1.add(radioButton1a);
      group1.add(radioButton1b);
      panel.add(radioButton1a);
      panel.add(radioButton1b);

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
      JRadioButton radioButton2a = new JRadioButton("Option 2a");
      JRadioButton radioButton2b = new JRadioButton("Option 2b");
      radioButton2a.setSelected(true); // Set the default selection to radioButton2a
      group2.add(radioButton2a);
      group2.add(radioButton2b);
      panel.add(radioButton2a);
      panel.add(radioButton2b);

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

      // Add the JPanel to the JFrame and set its size and visibility
      frame.add(panel);
      frame.setSize(300, 150);
      frame.setVisible(true);
   }
}
