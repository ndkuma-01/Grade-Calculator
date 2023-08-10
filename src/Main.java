import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static File mainDirectory;
    static String strMainDirectory;

    static ArrayList<Course> classes =  new ArrayList<Course>();

    static File settingsPath;

    static String Username;

    public static void main(String[] args) throws IOException {
        strMainDirectory = System.getProperty("user.home") + "\\AppData\\Local\\GraderTron\\";
        mainDirectory = new File(strMainDirectory);

        if (!mainDirectory.exists()){
            mainDirectory.mkdir();
        }else{
            updt();
        }

        if(new File(strMainDirectory + "Settings.txt").exists()){
            getSettings();
            settingsPath = new File(strMainDirectory + "Settings.txt");
        }else{
          new File(strMainDirectory + "Settings.txt").createNewFile();
          settingsPath = new File(strMainDirectory + "Settings.txt");
          writeDefaultSettings();
          getSettings();
        }

        new MainWindow();

    }

    public static void updt() throws FileNotFoundException {
        for (int i = 0 ; i < mainDirectory.listFiles().length; i++){

            File currClass = mainDirectory.listFiles()[i];
            if(currClass.getName().equals("Settings.txt")){
                continue;
            }
            classes.add(Main.createClass(currClass));
        }
    }
    public static Course createClass(File currClass) throws FileNotFoundException {
        Scanner stdin = new Scanner(currClass);
        String name = stdin.nextLine();
        String[] tempWeightNames = stdin.nextLine().split(";;;");
        ArrayList<String> weightNames = new ArrayList<String>();
        for (int i = 0; i < tempWeightNames.length; i++) {
            tempWeightNames[i] = tempWeightNames[i].strip();
            weightNames.add(tempWeightNames[i]);
        }
        String[] strWeights = stdin.nextLine().strip().split(";;;");
        double[] tempWeights = new double[strWeights.length];
        ArrayList<Double> weights = new ArrayList<Double>();
        for (int i = 0; i < strWeights.length; i++) {
            strWeights[i] = strWeights[i].strip();
            tempWeights[i] = Double.parseDouble(strWeights[i]);
            weights.add(tempWeights[i]);
        }

        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        try {
            while (stdin.hasNextLine()) {
                String[] currLine = stdin.nextLine().split(";;;");
                String assignName = currLine[0].strip();
                double receivedPoints = Double.parseDouble(currLine[1].strip());
                double totalPoints = Double.parseDouble(currLine[2].strip());
                String weight = currLine[3].strip();
                assignments.add(new Assignment(assignName, receivedPoints, totalPoints, weight));
            }
        }catch(Exception e){
        }
        return new Course(name, weightNames, weights, assignments);
    }

    public static void getSettings() throws FileNotFoundException {
        Scanner stdin = new Scanner(new File(strMainDirectory + "Settings.txt"));
        Username =stdin.nextLine();
        String[] rgbBackGroundColor = stdin.nextLine().split(":")[1].split(",");
        String[] rgbBtnColor = stdin.nextLine().split(":")[1].split(",");
        String[] rgbTxtColor = stdin.nextLine().split(":")[1].split(",");
        int[] rgbBckgrndCol = new int[3];
        int[] rgbBtnCol = new int[3];
        int[] rgbTxtCol = new int[3];

        for(int i = 0 ; i < 3; i++){
            rgbBckgrndCol[i] = Integer.parseInt(rgbBackGroundColor[i].strip());
            rgbBtnCol[i] = Integer.parseInt(rgbBtnColor[i].strip());
            rgbTxtCol[i] = Integer.parseInt(rgbTxtColor[i].strip());
        }
        MainWindow.lblSubTitle.setText(Username);
        MainWindow.bckgrndColor = new Color(rgbBckgrndCol[0], rgbBckgrndCol[1], rgbBckgrndCol[2]);
        MainWindow.buttonColor = new Color(rgbBtnCol[0], rgbBtnCol[1], rgbBtnCol[2]);
        MainWindow.textColor = new Color(rgbTxtCol[0], rgbTxtCol[1], rgbTxtCol[2]);

    }


    public static void writeDefaultSettings() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(strMainDirectory + "Settings.txt"));
        pw.println("VVV Go To Settings VVV");
        pw.println("Bckgrnd Color: 31, 67, 56");
        pw.println("Btn Color: 212, 103, 111");
        pw.println("Txt Color: 255, 255, 255");
        pw.close();
    }


}
