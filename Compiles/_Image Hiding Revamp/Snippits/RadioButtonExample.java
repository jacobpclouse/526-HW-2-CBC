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

      // Create a new ButtonGroup
      ButtonGroup group = new ButtonGroup();

      // Create four radio buttons and add them to the ButtonGroup and JPanel
      JRadioButton radioButton1 = new JRadioButton("Option 1");
      JRadioButton radioButton2 = new JRadioButton("Option 2");
      JRadioButton radioButton3 = new JRadioButton("Option 3");
      JRadioButton radioButton4 = new JRadioButton("Option 4");
      group.add(radioButton1);
      group.add(radioButton2);
      group.add(radioButton3);
      group.add(radioButton4);
      panel.add(radioButton1);
      panel.add(radioButton2);
      panel.add(radioButton3);
      panel.add(radioButton4);

      // Add an ItemListener to each radio button to detect when it is selected
      ItemListener listener = new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            // Print the selected radio button's text to the console
            if (e.getStateChange() == ItemEvent.SELECTED) {
               System.out.println(((JRadioButton) e.getSource()).getText());
            }
         }
      };
      radioButton1.addItemListener(listener);
      radioButton2.addItemListener(listener);
      radioButton3.addItemListener(listener);
      radioButton4.addItemListener(listener);

      // Add the JPanel to the JFrame and set its size and visibility
      frame.add(panel);
      frame.setSize(300, 150);
      frame.setVisible(true);
   }
}
