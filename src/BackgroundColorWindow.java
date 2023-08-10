import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundColorWindow extends JFrame implements ActionListener {

    JColorChooser colBackGroundCol = new JColorChooser();
    JButton btnDone = new JButton("Done");
    public BackgroundColorWindow(){
      setTitle("Background Color");
      setLayout(new GridBagLayout());
      setResizable(false);
      GridBagConstraints c = new GridBagConstraints();

      c.gridx = 0;
      c.gridy = 0;
      c.insets = new Insets(0,0,10,0);
      add(colBackGroundCol,c);
      c.gridy = 1;
      colBackGroundCol.setColor(SettingsWindow.tempBackgroundColor);
      add(btnDone,c);

      btnDone.addActionListener(this);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == btnDone){
            SettingsWindow.tempBackgroundColor = colBackGroundCol.getColor();
            System.out.println(SettingsWindow.tempBackgroundColor);
            System.out.println(SettingsWindow.tempButtonColor);
            System.out.println(SettingsWindow.tempTextColor);
            System.out.println();
            dispose();
        }
    }

}
