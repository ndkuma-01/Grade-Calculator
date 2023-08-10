import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.awt.SystemColor.window;

public class AddGradesWindow extends JFrame implements ActionListener {
    JLabel lblTitle = new JLabel("");
    JTable tblGrades = new JTable();
    JLabel lblAssignName = new JLabel("Assignment Name: ");
    JLabel lblPointsReceived = new JLabel("Points Received:  ");
    JLabel lblTotalPoints = new JLabel("Total Points:     ");
    JLabel lblWeight = new JLabel("Weight:          ");

    JTextField txtAssignName = new JTextField(10);
    JTextField txtPointsReceived = new JTextField(10);
    JTextField txtTotalPoints = new JTextField(10);
    JComboBox drpWeight = new JComboBox();



    JPanel pnlCenter = new JPanel(new BorderLayout());
    JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnlCenterPnlFlowCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel pnlSouthPnlBorder = new JPanel(new BorderLayout());

    JPanel pnlSouthPnlNorthFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnlSouthPnlCenterFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel pnlSouthPnlSouthBorder = new JPanel(new BorderLayout());
    JPanel pnlSouthPnlSouthBorderNorthFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JButton btnBack = new JButton("Back");
    JButton btnAdd = new JButton("Add Grade");

    static DefaultTableModel model = new DefaultTableModel();


    public AddGradesWindow() throws FileNotFoundException {
        setResizable(false);


        setTitle(LoadClassPickFileWindow.selectedCourse.getName());
        lblTitle.setText(LoadClassPickFileWindow.selectedCourse.getName());
        setLayout(new BorderLayout());
        //Adding the data to the table
        String[] columnNames = {"Name Of Assignment", "Points Received", "Points Total", "Weight"};
        model.setColumnIdentifiers(columnNames);
        refreshTable();
        tblGrades.setModel(model);


        //ADDING ELEMENTS
        add(pnlNorth, BorderLayout.NORTH);
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        add(pnlCenter, BorderLayout.CENTER);

        JScrollPane sp = new JScrollPane(tblGrades);

        pnlCenter.add(sp, BorderLayout.NORTH);
        pnlCenter.add(pnlCenterPnlFlowCenter, BorderLayout.CENTER);
        pnlCenterPnlFlowCenter.add(lblAssignName);
        pnlCenterPnlFlowCenter.add(txtAssignName);
        pnlCenter.add(pnlSouthPnlBorder, BorderLayout.SOUTH);
        pnlSouthPnlBorder.add(pnlSouthPnlNorthFlow, BorderLayout.NORTH);
        pnlSouthPnlNorthFlow.add(lblPointsReceived);
        pnlSouthPnlNorthFlow.add(txtPointsReceived);
        pnlSouthPnlBorder.add(pnlSouthPnlCenterFlow, BorderLayout.CENTER);
        pnlSouthPnlCenterFlow.add(lblTotalPoints);
        pnlSouthPnlCenterFlow.add(txtTotalPoints);
        pnlSouthPnlBorder.add(pnlSouthPnlSouthBorder, BorderLayout.SOUTH);
        pnlSouthPnlSouthBorder.add(pnlSouthPnlSouthBorderNorthFlow, BorderLayout.NORTH);
        pnlSouthPnlSouthBorderNorthFlow.add(lblWeight);
        pnlSouthPnlSouthBorderNorthFlow.add(drpWeight);
        add(pnlSouth, BorderLayout.SOUTH);
        pnlSouth.add(btnBack);
        pnlSouth.add(btnAdd);


        btnAdd.setForeground(MainWindow.textColor);
        btnBack.setForeground(MainWindow.textColor);

        btnAdd.addActionListener(this);
        btnBack.addActionListener(this);

        //Adding the weights to the dropdown
        ArrayList<String> weightNames = LoadClassPickFileWindow.selectedCourse.getWeightNames();
        ArrayList<Double> weights = LoadClassPickFileWindow.selectedCourse.getWeights();
        for (int i = 0; i < weightNames.size(); i++) {
            drpWeight.addItem(weightNames.get(i) + " (" + weights.get(i) + ")");
        }




        //Customizing elements
        tblGrades.setFont(MainWindow.fntButton);

        pnlSouth.setBackground(MainWindow.bckgrndColor);
        pnlSouthPnlBorder.setBackground(MainWindow.bckgrndColor);
        pnlCenter.setBackground(MainWindow.bckgrndColor);
        pnlSouthPnlSouthBorder.setBackground(MainWindow.bckgrndColor);
        pnlSouthPnlSouthBorderNorthFlow.setBackground(MainWindow.bckgrndColor);
        pnlSouthPnlCenterFlow.setBackground(MainWindow.bckgrndColor);
        pnlSouthPnlNorthFlow.setBackground(MainWindow.bckgrndColor);
        pnlNorth.setBackground(MainWindow.bckgrndColor);
        pnlCenterPnlFlowCenter.setBackground(MainWindow.bckgrndColor);
        lblTitle.setFont(MainWindow.fntTitle);
        lblTitle.setForeground(MainWindow.textColor);
        lblAssignName.setFont(MainWindow.fntText);
        lblAssignName.setForeground(MainWindow.textColor);
        lblPointsReceived.setFont(MainWindow.fntText);
        lblPointsReceived.setForeground(MainWindow.textColor);
        lblTotalPoints.setFont(MainWindow.fntText);
        lblTotalPoints.setForeground(MainWindow.textColor);
        lblWeight.setFont(MainWindow.fntText);
        lblWeight.setForeground(MainWindow.textColor);

        drpWeight.setBackground(MainWindow.bckgrndColor);
        drpWeight.setForeground(MainWindow.textColor);
        drpWeight.setFont(MainWindow.fntButton);
        drpWeight.setBorder(new RoundedBorder(1));

        btnAdd.setFont(MainWindow.fntButton);
        btnAdd.setBackground(MainWindow.buttonColor);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorder(new RoundedBorder(10));
        btnBack.setFont(MainWindow.fntButton);
        btnBack.setBackground(MainWindow.buttonColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(new RoundedBorder(10));


        setSize(650, 760);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent event){
        File classFile = new File(Main.strMainDirectory + lblTitle.getText() + ".txt");
        if (event.getSource() == btnBack){
            try {
                model.setRowCount(0);
                new ClassWindow();
                dispose();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if (event.getSource() == btnAdd){
            InputParser ip = new InputParser();

            if (txtAssignName.getText().equals("")){
                JOptionPane.showMessageDialog(this, "The assignment name field is empty.");
                return;
            } else if (txtPointsReceived.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "The points received field is empty");
                return;
            }else if(txtTotalPoints.getText().equals("")){
                JOptionPane.showMessageDialog(this, "The total points field is empty");
                return;
            }

            if(!ip.checkIfPointIsDouble(txtPointsReceived.getText())){
                JOptionPane.showMessageDialog(this, "The points received field is not a properly formatted number");
                return;
            }

            if(!ip.checkIfPointIsDouble(txtTotalPoints.getText())){
                JOptionPane.showMessageDialog(this, "The total points field is not a properly formatted number");
                return;
            }
            String assignName = txtAssignName.getText();
            double pointsReceived = Double.parseDouble(txtPointsReceived.getText());
            double totalPoints = Double.parseDouble(txtTotalPoints.getText());
            String weight = "" + drpWeight.getItemAt(drpWeight.getSelectedIndex()).toString().split(" \\(")[0];



            LoadClassPickFileWindow.selectedCourse.getGrades().add(new Assignment(assignName, pointsReceived, totalPoints, weight));
            try {
                LoadClassPickFileWindow.selectedCourse.updateFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                refreshTable();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void getData() throws FileNotFoundException {
        Course classFile = LoadClassPickFileWindow.selectedCourse;
        String[][] gradesForTable = new String[classFile.getGrades().size()][4];
        for (int i = 0 ; i < gradesForTable.length; i++){
            gradesForTable[i][0] = classFile.getGrades().get(i).getName();
            gradesForTable[i][1] = String.valueOf(classFile.getGrades().get(i).getPointsR());
            gradesForTable[i][2] = String.valueOf(classFile.getGrades().get(i).getPointsT());
            gradesForTable[i][3] = classFile.getGrades().get(i).getWeightName();
            model.addRow(gradesForTable[i]);
        }
    }

    public void refreshTable() throws FileNotFoundException {
        DefaultTableModel model = (DefaultTableModel) tblGrades.getModel();
        model.setRowCount(0);
        getData();
    }
}
