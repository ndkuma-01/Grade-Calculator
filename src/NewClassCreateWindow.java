import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class NewClassCreateWindow extends JFrame implements ActionListener {

    JLabel lblTitle = new JLabel("Create Class");
    JLabel lblName = new JLabel("Name Of Class: ");
    JLabel lblWeightNames = new JLabel("Name of Weights (;): ");
    JLabel lblWeights = new JLabel("Weights (;): ");
    JTextField txtName = new JTextField(10);
    JTextField txtWeightNames = new JTextField(10);
    JTextField txtWeights = new JTextField(10);
    JButton btnCreate = new JButton("Create");
    JButton btnBack= new JButton("Back");



    public NewClassCreateWindow(){
        setTitle("Create Class");
        setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(MainWindow.bckgrndColor);
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 10;
        c.ipady = 10;

        txtName.setToolTipText("Write the name of your class");
        txtWeights.setToolTipText("Enter the number weights");
        add(lblTitle,c);
        c.gridx = 0;
        c.gridy = 2;
        add(lblName,c);
        c.gridx = 0;
        c.gridy = 3;
        add(lblWeightNames,c);
        c.gridx = 0;
        c.gridy = 4;
        add(lblWeights,c);
        c.gridx = 2;
        c.gridy = 2;
        add(txtName,c);
        c.gridx = 2;
        c.gridy = 3;
        add(txtWeightNames,c);
        c.gridx = 2;
        c.gridy = 4;
        add(txtWeights,c);

        c.gridx = 2;
        c.gridy = 6;
        add(btnCreate, c);

        c.gridx = 0;
        add(btnBack,c);
        btnBack.addActionListener(this);
        btnCreate.addActionListener(this);

        lblTitle.setFont(MainWindow.fntTitle);
        lblName.setFont(MainWindow.fntText);
        lblWeights.setFont(MainWindow.fntText);
        lblWeightNames.setFont(MainWindow.fntText);
        btnBack.setFont(MainWindow.fntButton);
        btnBack.setFocusPainted(false);
        btnCreate.setFocusPainted(false);
        btnCreate.setFont(MainWindow.fntButton);
        btnBack.setBackground(MainWindow.buttonColor);
        btnCreate.setBackground(MainWindow.buttonColor);
        btnBack.setBorder(new RoundedBorder(10));
        btnCreate.setBorder(new RoundedBorder(10));
        btnBack.setPreferredSize(new Dimension(100,30));
        btnCreate.setPreferredSize(new Dimension(100,30));
        lblTitle.setForeground(MainWindow.textColor);
        lblName.setForeground(MainWindow.textColor);
        lblWeights.setForeground(MainWindow.textColor);
        lblWeightNames.setForeground(MainWindow.textColor);
        btnCreate.setForeground(MainWindow.textColor);
        btnBack.setForeground(MainWindow.textColor);

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
        if (event.getSource() == btnCreate) {
            //EXCEPTIONS TO ACCOUNT FOR
            // CORRECT TYPE
            // SAME NUMBER OF ELEMENTS FOR WEIGHTS
            // CORRECT FORMAT
            // CHECK IF FILE NAME ALREADY EXISTS (IF SO ASK TO OVERWRITE)
            // Weights can't have the same name

            InputParser ip = new InputParser();

            if(txtName.getText().equals("") || txtWeightNames.getText().equals("") || txtWeights.getText().equals("")){
                if(txtName.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Empty Field: Name");
                }
                if(txtWeightNames.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Empty Field: Weight Names");
                }
                if(txtWeights.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Empty Field: Weights");
                }
            }else{
                String className = txtName.getText();

                //Bouncer verifies all elements
                if(!ip.checkIfSemicolonsArePresent(txtWeightNames.getText())){
                    JOptionPane.showMessageDialog(this, "There are no semicolons in the weight names.");
                    return;
                }
                if(!ip.checkIfSemicolonsArePresent(txtWeights.getText())){
                    JOptionPane.showMessageDialog(this, "There are no semicolons in the weights.");
                    return;
                }
                if(!ip.checkForFileDuplicate(txtName.getText())){
                    JOptionPane.showMessageDialog(this, "There is a already an existing class with the same name.");
                    return;
                }
                if(!ip.checkIfWeightsAreAllDoubles(txtWeights.getText())){
                    JOptionPane.showMessageDialog(this, "Not all of the weights are valid numbers.");
                    return;
                }
                if (!ip.checkForFileNameOfSettings(txtName.getText())) {
                    JOptionPane.showMessageDialog(this,"Apologies but you can't have a class with the same name of \" Settings \" ");
                    return;
                }

                ArrayList<String> weightNames = new ArrayList<String>();
                ArrayList<Double> weights = new ArrayList<Double>();

                String[] tempWN = txtWeightNames.getText().split(";");
                for(int i = 0 ; i < tempWN.length; i++){
                    weightNames.add(tempWN[i].strip());
                }

                String[] tempW = txtWeights.getText().split(";");
                for (int i = 0 ; i < tempW.length; i++){
                    weights.add(Double.parseDouble(tempW[i].strip()));
                }
                if(!ip.checkForPositiveWeights(weights)){
                    JOptionPane.showMessageDialog(this, "One (or more) of the weights you entered, are zero or below that.");
                    return;
                }
                if(!ip.checkForSumOf100(weights)){
                    JOptionPane.showMessageDialog(this, "The weights you have entered do not equal 100/1.00.");
                    return;
                }
                if(!ip.checkWeightAndWeightNamesForSameNumberOfWeights(weightNames, weights)){
                    JOptionPane.showMessageDialog(this, "The weight names and weights aren't the same number");
                    return;
                }
                if(!ip.checkWeightNamesForDuplicates(weightNames)){
                    JOptionPane.showMessageDialog(this, "There are two or more weight names with the same name. Please modify so this isn't the case. ");
                    return;
                }

                    //CREATES THE CLASS FILE
                try {
                    FileWriter fw = new FileWriter(new File(Main.strMainDirectory + className + ".txt" ));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error 102: File couldn't be created.");
                    return;
                }
                //PUTS THE METADATA INTO THE CLASS FILE
                try {
                    PrintWriter pw = new PrintWriter(new File(Main.strMainDirectory+ className + ".txt"));
                    pw.write(className + '\n');
                    for (int i = 0 ; i < weightNames.size(); i++){
                        if(i != weightNames.size()-1) {
                            pw.append(weightNames.get(i) + ";;; ");
                        }else{
                            pw.append(weightNames.get(i) + '\n');
                        }
                    }
                    for (int i = 0 ; i < weights.size(); i++){
                        if(i != weights.size()-1) {
                            pw.append(weights.get(i) + ";;; ");
                        }else{
                            pw.append(weights.get(i) + "" +'\n');
                        }
                    }
                    pw.close();


                    JOptionPane.showMessageDialog(this, "Successful!");
                    new MainWindow();
                    dispose();
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Error 103: The File couldn't be written to");
                }
            }
        }
    }
}
