import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Course {

    private String name;

    private ArrayList<String> weightNames;

    private ArrayList<Double> weights;

    private HashMap<String, Double> weightMap = new HashMap<String, Double>();

    private ArrayList<Assignment> grades;

    File path;
    public Course(String name, ArrayList<String> weightNames, ArrayList<Double> weights, ArrayList<Assignment> grades) {
        this.name = name;
        this.weightNames = weightNames;
        this.weights = weights;
        this.grades = grades;
        this.path = new File(Main.strMainDirectory + "" + this.name + ".txt");

        for(int i = 0 ; i < weightNames.size(); i++) {
            weightMap.put(weightNames.get(i), weights.get(i));
        }
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setWeightNames(ArrayList<String> weightNames) {
        this.weightNames = weightNames;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public HashMap<String, Double> getWeightMap() {
        return weightMap;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public ArrayList<String> getWeightNames() {
        return weightNames;
    }

    public void setGrades(ArrayList<Assignment> grades) {
        this.grades = grades;
    }

    public ArrayList<Assignment> getGrades() {
        return grades;
    }



    public void updateFile() throws FileNotFoundException {
        File currFile = new File(Main.strMainDirectory + this.name + ".txt");
        PrintWriter pw = new PrintWriter(currFile);
        pw.println(name);
        String wNs = "";
        for(int i = 0; i< weightNames.size(); i++){
            wNs += weightNames.get(i);
            if(i != weightNames.size() - 1){
                wNs += ";;;";
            }
        }
        pw.println(wNs);
        String wS = "";
        for(int i = 0; i< weights.size(); i++){
            wS += weights.get(i);
            if(i != weights.size() - 1){
                wS += ";;;";
            }
        }
        pw.println(wS);

        for (int i = 0; i< grades.size(); i++){
            pw.println(grades.get(i).toString());
        }
        pw.close();
    }

    @Override
    public String toString() {
        String out = "NAME: " + this.name + '\n' + "WEIGHTS: " + '\n';
        for (int i = 0; i < this.weightNames.size(); i++) {
            out = out + '\t' + weightNames.get(i) + ": " + weights.get(i) + '\n';
        }
        out = out + "GRADES: " + '\n';
        for(int i = 0; i < this.grades.size(); i++){
            out = out + "------------------" + '\n';
            out =  out + '\t' + "" + this.grades.get(i).toString() + '\n';
            out = out + "------------------" + '\n';
        }
        return out;
    }



    public double getBlackBoardGrade(){
        double numerator = 0;
        double denominator = 0;

        for (int i = 0 ; i < grades.size(); i++){
            if (grades.get(i).getPointsR() != -1 ){
                numerator += grades.get(i).getPointsR();
                denominator += grades.get(i).getPointsT();
            }
        }



        return (numerator/denominator) * 100;
    }

    public double getPureGrade(){
        double[] nums = new double[weights.size()];
        double[] denoms = new double[weights.size()];

        for(int i = 0 ; i < grades.size(); i++){
            int indexInArray = weightNames.indexOf(grades.get(i).getWeightName());
            if(grades.get(i).getPointsR() != -1){
                nums[indexInArray] = nums[indexInArray] + grades.get(i).getPointsR();
                denoms[indexInArray] = denoms[indexInArray] + grades.get(i).getPointsT();
            }
        }

        double receivedPointsWithWeight = 0.0;
        double totalPointsWithWeight = 0.0;
        for(int i = 0 ; i < nums.length; i++){
            if( denoms[i] == 0){
                continue;
            }
            receivedPointsWithWeight += nums[i]/denoms[i] * weights.get(i);
            totalPointsWithWeight += denoms[i]/denoms[i] * weights.get(i);
        }

        return (receivedPointsWithWeight/totalPointsWithWeight) * 100;
    }
}
