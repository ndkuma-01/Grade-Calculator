import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class ClassWindow extends JFrame implements ActionListener {
//USE BORDER LAYOUT, WITH LOADS OF PANELS. LOOK AT IMAGE IN DESKTOP. PAGE 364 OF TEXTBOOK
    JLabel lblTitle = new JLabel("");
    JComboBox drpGradeType = new JComboBox();
    JLabel lblgrade = new JLabel("Select A Type of Grade");
    JButton btnBack = new JButton("Back");
    JButton btnGrades = new JButton("Add Grades");
    JButton btnEditClass = new JButton("Edit Class");
    JButton btnClose = new JButton("Close");
    JPanel pnlCenter = new JPanel(new BorderLayout());
    JPanel pnlButtons = new JPanel(new FlowLayout());
    JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnlCenterPnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnlCenterPnlNorthGrid = new JPanel(new GridBagLayout());
    JPanel pnlCenternPnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JTable tableGrades;
    public ClassWindow() throws FileNotFoundException {
        setResizable(false);
        pnlCenterPnlNorth.setBackground(MainWindow.bckgrndColor);
        pnlCenter.setBackground(MainWindow.bckgrndColor);
        pnlButtons.setBackground(MainWindow.bckgrndColor);
        pnlTitle.setBackground(MainWindow.bckgrndColor);
        pnlCenterPnlNorthGrid.setBackground(MainWindow.bckgrndColor);
        pnlCenternPnlCenter.setBackground(MainWindow.bckgrndColor);

        //Adding the data to the table
        String[] columnNames = {"Name Of Assignment", "Points Received", "Points Total", "Weight"};
        String[][] data = getData();
        tableGrades = new JTable(data, columnNames);
        tableGrades.editingCanceled(new ChangeEvent(tableGrades));
        //Getting the name of the file in order to name the title the same

        setTitle(LoadClassPickFileWindow.selectedCourse.getName());
        lblTitle.setText(LoadClassPickFileWindow.selectedCourse.getName());

        //Doing all the positioning and adding of all the elements
        setLayout(new BorderLayout());
        add(pnlTitle, BorderLayout.NORTH);
        pnlTitle.add(lblTitle, BorderLayout.NORTH);
        lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.add(pnlCenterPnlNorth, BorderLayout.NORTH);
        pnlCenter.add(pnlCenternPnlCenter, BorderLayout.CENTER);

        pnlCenterPnlNorth.add(pnlCenterPnlNorthGrid);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.ipadx=10;
        c.ipady=10;
        pnlCenterPnlNorthGrid.add(drpGradeType, c);
        c.gridy=1;
        pnlCenterPnlNorthGrid.add(lblgrade, c);

        JScrollPane sp = new JScrollPane(tableGrades);
        sp.setBackground(MainWindow.bckgrndColor);
        tableGrades.setOpaque(true);

        sp.getViewport().setBackground(MainWindow.bckgrndColor);
        pnlCenternPnlCenter.add(sp);
        add(pnlButtons, BorderLayout.SOUTH);
        pnlButtons.add(btnBack);
        pnlButtons.add(btnGrades);
        pnlButtons.add(btnEditClass);
        tableGrades.setFillsViewportHeight(true);
        pnlButtons.add(btnClose);


        //ADDING ACTION LISTENERS
        btnBack.addActionListener(this);
        btnClose.addActionListener(this);
        btnEditClass.addActionListener(this);
        btnGrades.addActionListener(this);
        drpGradeType.addActionListener(this);



        //Calculating Grades
        try {
            drpGradeType.addItem("");
            drpGradeType.addItem("Blackboard Grade (w/o weights)");
            drpGradeType.addItem("Pure Grade (w/ weights)");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Dropdown isn't loading.");
        }



        //Customizing buttons and everything
        this.getContentPane().setBackground(MainWindow.bckgrndColor);
        lblTitle.setFont(MainWindow.fntTitle);
        lblTitle.setForeground(MainWindow.textColor);
        lblgrade.setForeground(MainWindow.textColor);
        lblgrade.setFont(MainWindow.fntSubTitle);
        tableGrades.setFont(new Font("Serif", Font.CENTER_BASELINE, 13));
        drpGradeType.setBackground(MainWindow.bckgrndColor);
        drpGradeType.setForeground(MainWindow.textColor);
        drpGradeType.setFont(MainWindow.fntButton);
        drpGradeType.setBorder(new RoundedBorder(10));
        btnGrades.setFont(MainWindow.fntButton);
        btnGrades.setBackground(MainWindow.buttonColor);
        btnGrades.setFocusPainted(false);
        btnGrades.setBorder(new RoundedBorder(10));
        btnGrades.setForeground(MainWindow.textColor);
        btnBack.setFont(MainWindow.fntButton);
        btnBack.setBackground(MainWindow.buttonColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(new RoundedBorder(10));
        btnBack.setForeground(MainWindow.textColor);
        btnEditClass.setFont(MainWindow.fntButton);
        btnEditClass.setBackground(MainWindow.buttonColor);
        btnEditClass.setFocusPainted(false);
        btnEditClass.setBorder(new RoundedBorder(10));
        btnEditClass.setForeground(MainWindow.textColor);
        btnClose.setFont(MainWindow.fntButton);
        btnClose.setBackground(MainWindow.buttonColor);
        btnClose.setFocusPainted(false);
        btnClose.setBorder(new RoundedBorder(10));
        btnClose.setForeground(MainWindow.textColor);

        setSize(650,670);
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
        if(event.getSource() == btnClose){
            if(JOptionPane.showConfirmDialog(null,"Are you sure?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        if(event.getSource() == btnEditClass){
            new EditChooserWindow();
            dispose();
        }
        if(event.getSource() == btnGrades){
            try {
                new AddGradesWindow();
                dispose();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(event.getSource() == drpGradeType){

            if (drpGradeType.getItemAt(drpGradeType.getSelectedIndex()).toString().equals("Blackboard Grade (w/o weights)")) {
                DecimalFormat df = new DecimalFormat("##.###");
                df.setRoundingMode(RoundingMode.CEILING);

                lblgrade.setText(df.format(LoadClassPickFileWindow.selectedCourse.getBlackBoardGrade()) + "%");
            } else if (drpGradeType.getItemAt(drpGradeType.getSelectedIndex()).toString().equals("Pure Grade (w/ weights)")) {
                DecimalFormat df = new DecimalFormat("##.###");
                df.setRoundingMode(RoundingMode.CEILING);

                lblgrade.setText(df.format(LoadClassPickFileWindow.selectedCourse.getPureGrade()) + "%");
            }
        }
    }

    //gets the data from the file
    public String[][] getData() throws FileNotFoundException {
        Course classFile = LoadClassPickFileWindow.selectedCourse;
        String[][] gradesForTable = new String[classFile.getGrades().size()][4];
        for (int i = 0 ; i < gradesForTable.length; i++){
            gradesForTable[i][0] = classFile.getGrades().get(i).getName();
            gradesForTable[i][1] = String.valueOf(classFile.getGrades().get(i).getPointsR());
            gradesForTable[i][2] = String.valueOf(classFile.getGrades().get(i).getPointsT());
            gradesForTable[i][3] = classFile.getGrades().get(i).getWeightName();
        }

        return gradesForTable;
    }


}
