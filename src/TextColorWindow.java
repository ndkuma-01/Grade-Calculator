import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextColorWindow extends JFrame implements ActionListener {

    JColorChooser colTextCol = new JColorChooser();
    JButton btnDone = new JButton("Done");
    public TextColorWindow(){
        setTitle("Text Color");
        setResizable(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,10,0);
        add(colTextCol,c);
        c.gridy = 1;
        colTextCol.setColor(SettingsWindow.tempTextColor);

        add(btnDone,c);

        btnDone.addActionListener(this);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == btnDone){
            SettingsWindow.tempTextColor = colTextCol.getColor();
            dispose();
        }
    }

}
