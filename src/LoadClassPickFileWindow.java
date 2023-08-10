import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LoadClassPickFileWindow extends JFrame implements ActionListener {

    //Make it so that when the file is selected it will go through and double check the file for problems

    JComboBox drpClasses = new JComboBox();
    JButton btnBack = new JButton("Back");
    JButton btnSelect = new JButton("Select");
    JLabel lblTitle = new JLabel("Choose Class");

    static Course selectedCourse;

    public LoadClassPickFileWindow(){
        setDefaultCloseOperation(3);
        setTitle("Load Class");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.getContentPane().setBackground(MainWindow.bckgrndColor);
        setResizable(false);


        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 10;
        c.ipady = 10;
        add(lblTitle,c);
        c.gridy = 1;
        add(drpClasses,c);
        c.gridy=2;
        c.gridx = 0;
        add(btnBack,c);
        c.gridx = 2;
        add(btnSelect,c);

        updte();
        btnBack.addActionListener(this);
        btnSelect.addActionListener(this);

        lblTitle.setFont(MainWindow.fntTitle);
        lblTitle.setForeground(MainWindow.textColor);

        btnSelect.setFont(MainWindow.fntButton);
        btnSelect.setBackground(MainWindow.buttonColor);
        btnSelect.setBorder(new RoundedBorder(10));
        btnSelect.setPreferredSize(new Dimension(100,30));
        btnSelect.setFocusPainted(false);
        btnBack.setFont(MainWindow.fntButton);
        btnBack.setBackground(MainWindow.buttonColor);
        btnBack.setBorder(new RoundedBorder(10));
        btnBack.setPreferredSize(new Dimension(100,30));
        btnBack.setFocusPainted(false);
        drpClasses.setBackground(MainWindow.bckgrndColor);
        drpClasses.setForeground(MainWindow.textColor);
        drpClasses.setFont(MainWindow.fntButton);
        drpClasses.setBorder(new RoundedBorder(10));
        btnBack.setForeground(MainWindow.textColor);
        btnSelect.setForeground(MainWindow.textColor);




        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == btnBack){
            try {
                new MainWindow();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
        if(event.getSource() == btnSelect){
            selectedCourse = Main.classes.get(drpClasses.getSelectedIndex());
            try {
                new ClassWindow();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
    }



    public void updte(){
        if(drpClasses.getItemCount() ==0) {
            for (int i = 0; i < Main.classes.size(); i++) {
                drpClasses.addItem(Main.classes.get(i).getName());
            }
        }
    }
}
