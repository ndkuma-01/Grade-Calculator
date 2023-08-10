import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColorWindow extends JFrame implements ActionListener {

    JColorChooser colButtonCol = new JColorChooser();
    JButton btnDone = new JButton("Done");
    public ButtonColorWindow(){
        setTitle("Button Color");
        setLayout(new GridBagLayout());
        setResizable(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,10,0);
        add(colButtonCol,c);
        c.gridy = 1;

        colButtonCol.setColor(SettingsWindow.tempButtonColor);
        add(btnDone,c);

        btnDone.addActionListener(this);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == btnDone){
            SettingsWindow.tempButtonColor = colButtonCol.getColor();
            dispose();
        }
    }

}
