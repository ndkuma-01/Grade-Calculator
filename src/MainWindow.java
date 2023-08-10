import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class MainWindow extends JFrame implements ActionListener {

    JLabel lblTitle = new JLabel("Grade Calculator");
    static JLabel lblSubTitle = new JLabel("");
    JButton btnLoadGrades = new JButton("Load Class");
    JButton btnNewOrDeleteClass = new JButton("Add/Delete Classes");
    JLabel lblPlh = new JLabel("XXXXX");
    JButton btnSettings = new JButton("Settings");
    public static Color textColor = new Color(255, 255, 255);
    public static Color buttonColor = new Color(212, 103, 111);
    public static Color bckgrndColor = new Color(31, 67, 56);

    public static final Font fntTitle = new Font("Serif", Font.BOLD, 60);

    public static final Font fntSubTitle = new Font("Serif", Font.PLAIN, 30);

    public static final Font fntText = new Font("Serif", Font.PLAIN, 20);

    public static final Font fntButton = new Font("Serif", Font.CENTER_BASELINE, 16);


    public MainWindow() throws FileNotFoundException {

        setTitle("Grade Calculator");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.getContentPane().setBackground(bckgrndColor);

        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 10;
        c.ipady = 10;


        add(lblTitle, c);
        lblTitle.setFont(fntTitle);
        lblTitle.setForeground(textColor);
        c.gridx = 2;
        c.gridy = 1;
        add(lblSubTitle, c);
        lblSubTitle.setFont(fntSubTitle);
        lblSubTitle.setForeground(Color.WHITE);
        c.gridy = 2;
        add(lblPlh);
        c.gridx = 3;
        c.gridy = 3;
        add(btnLoadGrades, c);
        c.gridx = 1;
        c.gridy = 3;
        add(btnNewOrDeleteClass, c);
        c.gridx = 2;
        add(btnSettings, c);
        lblPlh.setVisible(false);
        btnNewOrDeleteClass.setPreferredSize(new Dimension(150, 30));
        btnLoadGrades.setPreferredSize(new Dimension(100, 30));
        btnLoadGrades.addActionListener(this);
        btnNewOrDeleteClass.addActionListener(this);
        btnNewOrDeleteClass.setBackground(buttonColor);
        btnLoadGrades.setBackground(buttonColor);
        btnNewOrDeleteClass.setFocusPainted(false);
        btnLoadGrades.setFocusPainted(false);
        btnNewOrDeleteClass.setFont(fntButton);
        btnLoadGrades.setFont(fntButton);
        btnLoadGrades.setForeground(MainWindow.textColor);
        btnNewOrDeleteClass.setForeground(MainWindow.textColor);

        btnSettings.setFont(MainWindow.fntButton);
        btnSettings.setBackground(MainWindow.buttonColor);
        btnSettings.setFocusPainted(false);
        btnSettings.setBorder(new RoundedBorder(10));
        btnSettings.setPreferredSize(new Dimension(100, 30));
        btnSettings.setForeground(MainWindow.textColor);

        btnLoadGrades.setBorder(new RoundedBorder(10));
        btnNewOrDeleteClass.setBorder(new RoundedBorder(10));
        btnSettings.addActionListener(this);

        Main.classes.clear();
        Main.updt();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnNewOrDeleteClass) {
            new NewClassCreateWindow();
            dispose();
        }
        if (event.getSource() == btnLoadGrades) {
            if (Main.classes == null) {
                JOptionPane.showMessageDialog(this, "You Have No Classes");
            } else {
                new LoadClassPickFileWindow();
                dispose();
            }
        }

        if (event.getSource() == btnSettings) {
            Main.settingsPath.setWritable(true);
            new SettingsWindow();
            dispose();
        }
    }


}
