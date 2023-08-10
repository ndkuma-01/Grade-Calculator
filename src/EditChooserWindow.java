import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EditChooserWindow extends JFrame implements ActionListener, ListSelectionListener {

    JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel pnlCenter = new JPanel(new GridBagLayout());


    JLabel lblTitle = new JLabel("Editing Grades");

    JList<String> lstGrades = new JList<>();

    DefaultListModel model;

    JLabel lblName = new JLabel("Name");
    JTextField txtName = new JTextField(10);
    JTextField txtPoints = new JTextField(10);
    JLabel lblPnts = new JLabel("Points");
    JLabel lblWeight = new JLabel("Weight");
    JComboBox drpWeights = new JComboBox();
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");

    JButton btnFinalize = new JButton("Finalize");
    JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btnBack = new JButton("Back");

    private ArrayList<Assignment> tempChangeToAssignments;

    public EditChooserWindow() {
        setResizable(false);
        setTitle("Editing Grades");
        setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();

        tempChangeToAssignments = new ArrayList<>(LoadClassPickFileWindow.selectedCourse.getGrades());

        add(pnlNorth, BorderLayout.NORTH);
        pnlNorth.add(lblTitle);
        add(pnlSouth, BorderLayout.SOUTH);
        pnlSouth.add(btnBack);
        pnlSouth.add(btnFinalize);
        add(pnlCenter, BorderLayout.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 30;
        c.ipady = 10;

        c.gridwidth = 1;
        c.gridheight = 4;
        pnlCenter.add(new JScrollPane(lstGrades), c);
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        pnlCenter.add(lblName, c);
        c.gridx = 4;
        pnlCenter.add(txtName, c);

        c.gridy = 1;
        c.gridx = 3;
        pnlCenter.add(lblPnts, c);
        c.gridx = 4;
        pnlCenter.add(txtPoints, c);

        c.gridy = 2;
        c.gridx = 3;
        pnlCenter.add(lblWeight, c);
        c.gridx = 4;
        pnlCenter.add(drpWeights, c);
        c.gridy = 3;
        c.gridx = 3;
        c.insets = new Insets(0,10,0,0);
        pnlCenter.add(btnDelete, c);
        c.gridx = 4;
        pnlCenter.add(btnUpdate, c);


        model = updateList();
        lstGrades.setModel(model);
        updateDropDown();


        lstGrades.addListSelectionListener(this);


        btnDelete.addActionListener(this);
        btnFinalize.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnBack.addActionListener(this);


        //Customizing the elements
        pnlSouth.setBackground(MainWindow.bckgrndColor);
        pnlCenter.setBackground(MainWindow.bckgrndColor);
        pnlNorth.setBackground(MainWindow.bckgrndColor);
        lblTitle.setFont(MainWindow.fntTitle);
        lblTitle.setForeground(MainWindow.textColor);
        lblName.setFont(MainWindow.fntText);
        lblName.setForeground(MainWindow.textColor);
        lblPnts.setFont(MainWindow.fntText);
        lblPnts.setForeground(MainWindow.textColor);
        lblWeight.setFont(MainWindow.fntText);
        lblWeight.setForeground(MainWindow.textColor);

        lstGrades.setFont(new Font("Serif", Font.CENTER_BASELINE, 13));

        drpWeights.setBackground(MainWindow.bckgrndColor);
        drpWeights.setForeground(MainWindow.textColor);
        drpWeights.setFont(MainWindow.fntButton);
        drpWeights.setBorder(new RoundedBorder(5));
        btnBack.setFont(MainWindow.fntButton);
        btnBack.setBackground(MainWindow.buttonColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(new RoundedBorder(10));
        btnBack.setForeground(MainWindow.textColor);
        btnUpdate.setFont(MainWindow.fntButton);
        btnUpdate.setBackground(MainWindow.buttonColor);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorder(new RoundedBorder(10));
        btnUpdate.setForeground(MainWindow.textColor);
        btnFinalize.setFont(MainWindow.fntButton);
        btnFinalize.setBackground(MainWindow.buttonColor);
        btnFinalize.setFocusPainted(false);
        btnFinalize.setBorder(new RoundedBorder(10));
        btnFinalize.setForeground(MainWindow.textColor);
        btnDelete.setFont(MainWindow.fntButton);
        btnDelete.setBackground(MainWindow.buttonColor);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(new RoundedBorder(10));
        btnDelete.setForeground(MainWindow.textColor);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnDelete) {
            int[] indices = lstGrades.getSelectedIndices();

            for (int i = 0; i < indices.length; i++) {
                tempChangeToAssignments.remove(indices[i] - i);
            }
            model = updateList();
            try {
                lstGrades.setModel(model);
            } catch (Exception e) {}
            updateDropDown();
        } else if (event.getSource() == btnUpdate) {

            if(txtName.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "The name field is empty");
                return;
            }
            if(txtPoints.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "The points field is empty");
                return;
            }

            InputParser ip = new InputParser();

            if(!ip.checkIfSlashIsPresent(txtPoints.getText())){
                JOptionPane.showMessageDialog(this, "Incorrectly Formatted. There must be a slash in between the points.");
                return;
            }
            if(!ip.checkIfEditingPointsAreOnlyTwoFields(txtPoints.getText())){
                JOptionPane.showMessageDialog(this, "There must be only two points. An amount received and a total. Separate the two with a single slash.");
                return;
            }

            if(!ip.checkIfEditingPointsFieldIsDoubles(txtPoints.getText())){
                JOptionPane.showMessageDialog(this, "Both points field must be proper numbers.");
                return;
            }

            int currIndex = -1;
            try {
                currIndex = lstGrades.getSelectedIndex();

                if (!tempChangeToAssignments.get(currIndex).getName().equals(txtName.getText())) {
                    tempChangeToAssignments.get(currIndex).setName(txtName.getText());
                }
                if (!(tempChangeToAssignments.get(currIndex).getPointsR() == Double.parseDouble(txtPoints.getText().split(" / ")[0].strip()))) {
                    tempChangeToAssignments.get(currIndex).setPointsR(Double.parseDouble(txtPoints.getText().split(" / ")[0].strip()));
                }
                if (!(tempChangeToAssignments.get(currIndex).getPointsT() == Double.parseDouble(txtPoints.getText().split(" / ")[1].strip()))) {
                    tempChangeToAssignments.get(currIndex).setPointsT(Double.parseDouble(txtPoints.getText().split(" / ")[1].strip()));
                }
                String selectedWeight = drpWeights.getItemAt(drpWeights.getSelectedIndex()).toString().split(" \\(")[0];
                if (!(tempChangeToAssignments.get(currIndex).getWeightName().equals(selectedWeight))) {
                    tempChangeToAssignments.get(currIndex).setWeightName(selectedWeight);
                }

                model = updateList();
                try {
                    lstGrades.setModel(model);
                } catch (Exception e) {

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Remember to select a grade to change!");
                return;
            }


        } else if (event.getSource() == btnFinalize) {
            LoadClassPickFileWindow.selectedCourse.setGrades(tempChangeToAssignments);
            try {
                LoadClassPickFileWindow.selectedCourse.updateFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                new ClassWindow();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        } else if (event.getSource() == btnBack) {
            try {
                new ClassWindow();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
    }


    public DefaultListModel updateList() {

        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < tempChangeToAssignments.size(); i++) {
            model.addElement(tempChangeToAssignments.get(i).getName() + " (" + tempChangeToAssignments.get(i).getPointsR() + "/" + tempChangeToAssignments.get(i).getPointsT()
                    + ") - " + tempChangeToAssignments.get(i).getWeightName());
        }
        return model;
    }

    public void updateDropDown() {
        Course currentCourse = LoadClassPickFileWindow.selectedCourse;
        for (int i = 0; i < currentCourse.getWeightNames().size(); i++) {
            drpWeights.addItem(currentCourse.getWeightNames().get(i) + " (" + currentCourse.getWeights().get(i) + ")");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int[] indices = lstGrades.getSelectedIndices();
        Assignment currAssignment = LoadClassPickFileWindow.selectedCourse.getGrades().get(indices[0]);
        txtName.setText(currAssignment.getName());
        txtPoints.setText(currAssignment.getPointsR() + " / " + currAssignment.getPointsT());
        int index = 0;
        for (int i = 0; i < LoadClassPickFileWindow.selectedCourse.getWeightNames().size(); i++) {
            if (LoadClassPickFileWindow.selectedCourse.getWeightNames().get(i).equals(currAssignment.getWeightName())) {
                index = i;
                break;
            }
        }
        drpWeights.setSelectedIndex(index);
    }
}
