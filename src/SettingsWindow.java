import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SettingsWindow extends JFrame implements ActionListener, WindowListener {

    static Color tempBackgroundColor;
    static Color tempButtonColor;
    static Color tempTextColor;


    JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnlCenter = new JPanel(new BorderLayout());
    JPanel pnlCenterNorth = new JPanel(new GridBagLayout());
    JPanel pnlCenterCenter = new JPanel(new GridBagLayout());
    JPanel pnlCenterSouth = new JPanel(new BorderLayout());
    JPanel pnlCenterSouthNorth = new JPanel(new GridBagLayout());
    JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));




    JLabel lblName = new JLabel("Username: ");
    JLabel lblTitle = new JLabel("Settings");
    JLabel lblBackGroundColor = new JLabel("Background Color: ");
    JLabel lblTextColor = new JLabel("Text Color: ");
    JLabel lblButtonColor = new JLabel("Button Color: ");
    JTextArea txtAreaDescription = new JTextArea("Hello and welcome to the Grade Calculator!  \n This is a desktop application built to house your classes and their grades,  along with calculating them \n for you. Here in settings you can add your  name to the Username slot to make it a \ntad more personalized, and you can change the colors to once again make it more personalized to you!\n In the main menu you can choose to add or delete classes, or you can load a class. \n One important thing to mention is that most of the elements on your screen, when hovered over, \n it will display some helpful text about their function. So if you are ever confused, hover over something!");



    JButton btnBack = new JButton("Back");
    JButton btnFinalize = new JButton("Finalize");
    JButton btnDelete = new JButton("Delete");

    JComboBox drpClasses = new JComboBox();



    JButton btnBackground = new JButton("Choose Background Color");
    JButton btnText = new JButton("Choose Text Color");

    JButton btnButton = new JButton("Choose Button Color");

    JTextField txtName = new JTextField(10);
    JButton btnResetDefault = new JButton("Default Colors");


    public SettingsWindow(){
        setTitle("Settings");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        setResizable(false);
        tempBackgroundColor = new Color(MainWindow.bckgrndColor.getRGB());
        tempButtonColor = new Color(MainWindow.buttonColor.getRGB());
        tempTextColor = new Color(MainWindow.textColor.getRGB());
        if(!(MainWindow.lblSubTitle.getText().equals("VVV Go To Settings VVV") ||MainWindow.lblSubTitle.getText().equals("Set UserName In Settings"))) {
            txtName.setText(MainWindow.lblSubTitle.getText());
        }
        txtAreaDescription.setEditable(false);
        txtAreaDescription.setOpaque(false);
        add(pnlTitle, BorderLayout.NORTH);
        pnlTitle.add(lblTitle);
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.add(pnlCenterNorth, BorderLayout.NORTH);
        pnlCenter.add(pnlCenterCenter, BorderLayout.CENTER);
        pnlCenter.add(pnlCenterSouth, BorderLayout.SOUTH);
        pnlCenterSouth.add(pnlCenterSouthNorth, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);
        pnlSouth.add(btnBack);
        pnlSouth.add(btnFinalize);

        pnlCenterNorth.setBorder(makeTitledBorder("Name"));
        ((TitledBorder) pnlCenterNorth.getBorder()).setTitleFont(MainWindow.fntText);
        ((TitledBorder) pnlCenterNorth.getBorder()).setTitleColor(tempTextColor);
        pnlCenterNorth.repaint();
        c.gridx = 0;
        c.gridy = 0;
        pnlCenterNorth.add(lblName,c);
        c.gridx = 2;
        pnlCenterNorth.add(txtName,c);

        c.ipadx= 30;

        pnlCenterCenter.setBorder(makeTitledBorder("Colors"));
        ((TitledBorder) pnlCenterCenter.getBorder()).setTitleFont(MainWindow.fntText);
        ((TitledBorder) pnlCenterCenter.getBorder()).setTitleColor(tempTextColor);

        c.insets= new Insets(0,0,10,0);

        c.gridx = 0;
        c.gridy = 0;
        pnlCenterCenter.add(lblBackGroundColor,c);
        c.gridy = 1;
        pnlCenterCenter.add(lblButtonColor,c);
        c.gridy = 2;
        pnlCenterCenter.add(lblTextColor,c);

        c.gridx = 2;
        c.gridy = 0;
        pnlCenterCenter.add(btnBackground,c);
        c.gridy = 1;
        pnlCenterCenter.add(btnButton,c);
        c.gridy = 2;
        pnlCenterCenter.add(btnText,c);

        c.gridx = 1;
        c.gridy = 3;
        pnlCenterCenter.add(btnResetDefault, c);


        addWindowListener(this);
        pnlCenterSouth.setBorder(makeTitledBorder("Description"));
        ((TitledBorder) pnlCenterSouth.getBorder()).setTitleFont(MainWindow.fntText);
        ((TitledBorder) pnlCenterSouth.getBorder()).setTitleColor(tempTextColor);
        pnlCenterSouth.add(txtAreaDescription);

        btnBack.addActionListener(this);
        btnBackground.addActionListener(this);
        btnButton.addActionListener(this);
        btnDelete.addActionListener(this);
        btnFinalize.addActionListener(this);
        btnText.addActionListener(this);
        btnResetDefault.addActionListener(this);


        //Customizing elements
        updateElementCustomizations();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == btnBackground){
            new BackgroundColorWindow();
        }else if(event.getSource() == btnButton){
            new ButtonColorWindow();
        }else if(event.getSource() == btnText){
            new TextColorWindow();
        }else if(event.getSource() == btnBack){
            try {
                new MainWindow();
                dispose();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (event.getSource() == btnFinalize) {
            try {
                PrintWriter pw = new PrintWriter(Main.settingsPath);
                if(!(txtName.getText().isEmpty() || txtName.getText().equals("VVV Go To Settings VVV") || txtName.getText().equals("Set UserName In Settings"))){
                    pw.println(txtName.getText());
                }else{
                    pw.println("Set UserName In Settings");
                }
                pw.println("Bckgrnd Color: " + tempBackgroundColor.getRed() +  ", " + tempBackgroundColor.getGreen() + ", " + tempBackgroundColor.getBlue());
                pw.println("Btn Color: " + tempButtonColor.getRed() +  ", " + tempButtonColor.getGreen() + ", " + tempButtonColor.getBlue());
                pw.println("Txt Color: " + tempTextColor.getRed() +  ", " + tempTextColor.getGreen() + ", " + tempTextColor.getBlue());
                pw.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Main.settingsPath.setReadOnly();
            if(!(txtName.getText().isEmpty() || txtName.getText().equals("VVV Go To Settings VVV") || txtName.getText().equals("Set UserName In Settings"))) {
                MainWindow.lblSubTitle.setText(txtName.getText());
            }else{
                MainWindow.lblSubTitle.setText("Set UserName In Settings");
            }
                MainWindow.textColor = tempTextColor;
            MainWindow.buttonColor = tempButtonColor;
            MainWindow.bckgrndColor = tempBackgroundColor;
            try {
                new MainWindow();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }else if(event.getSource() == btnResetDefault){
            tempTextColor = new Color(255,255,255);
            tempButtonColor = new Color(212,103,111);
            tempBackgroundColor = new Color(31,67,56);
            updateElementCustomizations();
        }
    }



    public Border makeTitledBorder(String title){
        return BorderFactory.createTitledBorder(title);
    }

    public void updateElementCustomizations(){
        pnlCenterSouth.setBackground(tempBackgroundColor);
        pnlCenter.setBackground(tempBackgroundColor);
        pnlSouth.setBackground(tempBackgroundColor);
        pnlTitle.setBackground(tempBackgroundColor);
        pnlCenterCenter.setBackground(tempBackgroundColor);
        pnlCenterSouthNorth.setBackground(tempBackgroundColor);
        pnlCenterNorth.setBackground(tempBackgroundColor);
        pnlCenterCenter.setBorder(makeTitledBorder("Colors"));
        ((TitledBorder) pnlCenterCenter.getBorder()).setTitleFont(MainWindow.fntText);
        ((TitledBorder) pnlCenterCenter.getBorder()).setTitleColor(tempTextColor);
        pnlCenterSouth.setBorder(makeTitledBorder("Description"));
        ((TitledBorder) pnlCenterSouth.getBorder()).setTitleFont(MainWindow.fntText);
        ((TitledBorder) pnlCenterSouth.getBorder()).setTitleColor(tempTextColor);
        pnlCenterNorth.setBorder(makeTitledBorder("Name"));
        ((TitledBorder) pnlCenterNorth.getBorder()).setTitleFont(MainWindow.fntText);
        ((TitledBorder) pnlCenterNorth.getBorder()).setTitleColor(tempTextColor);

        this.getContentPane().setBackground(tempBackgroundColor);
        btnText.setBackground(tempButtonColor);
        btnText.setFont(MainWindow.fntButton);
        btnText.setFocusPainted(false);
        btnText.setBorder(new RoundedBorder(10));
        btnText.setForeground(tempTextColor);
        btnFinalize.setBackground(tempButtonColor);
        btnFinalize.setFont(MainWindow.fntButton);
        btnFinalize.setFocusPainted(false);
        btnFinalize.setBorder(new RoundedBorder(10));
        btnFinalize.setForeground(tempTextColor);

        btnDelete.setBackground(tempButtonColor);
        btnDelete.setFont(MainWindow.fntButton);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(new RoundedBorder(10));
        btnDelete.setForeground(tempTextColor);

        btnButton.setBackground(tempButtonColor);
        btnButton.setFont(MainWindow.fntButton);
        btnButton.setFocusPainted(false);
        btnButton.setBorder(new RoundedBorder(10));
        btnButton.setForeground(tempTextColor);

        btnBack.setBackground(tempButtonColor);
        btnBack.setFont(MainWindow.fntButton);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(new RoundedBorder(10));
        btnBack.setForeground(tempTextColor);

        btnBackground.setBackground(tempButtonColor);
        btnBackground.setFont(MainWindow.fntButton);
        btnBackground.setFocusPainted(false);
        btnBackground.setBorder(new RoundedBorder(10));
        btnBackground.setForeground(tempTextColor);

        btnResetDefault.setBackground(tempButtonColor);
        btnResetDefault.setFont(MainWindow.fntButton);
        btnResetDefault.setFocusPainted(false);
        btnResetDefault.setBorder(new RoundedBorder(10));
        btnResetDefault.setForeground(tempTextColor);

        lblBackGroundColor.setFont(MainWindow.fntText);
        lblBackGroundColor.setForeground(tempTextColor);
        lblButtonColor.setFont(MainWindow.fntText);
        lblButtonColor.setForeground(tempTextColor);
        txtAreaDescription.setFont(MainWindow.fntText);
        txtAreaDescription.setForeground(tempTextColor);
        lblTextColor.setFont(MainWindow.fntText);
        lblTextColor.setForeground(tempTextColor);
        lblTitle.setFont(MainWindow.fntTitle);
        lblTitle.setForeground(tempTextColor);
        lblName.setFont(MainWindow.fntText);
        lblName.setForeground(tempTextColor);
    }




    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
        updateElementCustomizations();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
