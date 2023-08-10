import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class InputParser {
//If bouncer returns true that means the entry is correct, if it found something it returns false
    public InputParser(){}

    public boolean checkWeightNamesForDuplicates(String[] weightNames){
        HashSet<String> check = new HashSet<String>();
        for(int i = 0 ; i < weightNames.length; i++){
            check.add(weightNames[i]);
        }

        if(check.size() == weightNames.length){
            return true;
        }
        return false;
    }

    public boolean checkWeightNamesForDuplicates(ArrayList<String> weightNames){
        HashSet<String> check = new HashSet<String>();
        for(int i = 0 ; i < weightNames.size(); i++){
            check.add(weightNames.get(i));
        }

        if(check.size() == weightNames.size()){
            return true;
        }
        return false;
    }

    public boolean checkWeightAndWeightNamesForSameNumberOfWeights(String[] weightNames, double[] weights) {
        if (weightNames.length == weights.length) {
            return true;
        }
        return false;
    }

    public boolean checkWeightAndWeightNamesForSameNumberOfWeights(ArrayList<String> weightNames, ArrayList<Double> weights) {
        if (weightNames.size() == weights.size()) {
            return true;
        }
        return false;
    }

    public boolean checkIfSemicolonsArePresent(String person){
        int numOfSemicolons = 0;
        for(int i = 0 ; i < person.length(); i++){
            if(person.charAt(i) == ';'){
                numOfSemicolons++;
            }
        }
        if(numOfSemicolons != 0)
            return true;
        return false;
    }

    public boolean checkIfWeightsAreAllDoubles(String weights){
       String[] actualWeights = weights.split(";");

        for(int i =0 ; i < actualWeights.length; i++){
            try{
                Double.parseDouble(actualWeights[i].strip());
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

    public boolean checkForFileDuplicate(String nameOfClass){
        File[] existingFiles = Main.mainDirectory.listFiles();
        for(int i = 0 ; i < existingFiles.length; i++){
            if((nameOfClass + ".txt").equals(existingFiles[i].getName())){
                return false;
            }
        }
        return true;
    }

    public boolean checkForSumOf100(double[] weights){
        double sum = 0;
        double epsilon = 0.0009;
        for(int i = 0 ; i < weights.length; i++){
            sum += weights[i];
        }
        if(!((100 - sum) < epsilon) || ((1.00 - sum) < epsilon)){
            return false;
        }
        return true;
    }
    public boolean checkForSumOf100(ArrayList<Double> weights){
        double sum = 0;
        double epsilon = 0.0009;
        for(int i = 0 ; i < weights.size(); i++){
            sum += weights.get(i);
        }
        System.out.println("Sum: " + sum);
        System.out.println("100 Sum: "+ ((100 - sum) < epsilon));
        System.out.println("1.00 Sum: " + ((1.00 - sum) < epsilon));

        if(!(((100 - sum) < epsilon) || ((1.00 - sum) < epsilon))){
            return false;
        }
        return true;
    }

    public boolean checkForPositiveWeights(double[] weights){
        for(int i = 0 ; i < weights.length; i++){
            if(weights[i] <= 0){
                return false;
            }
        }
        return true;
    }
    public boolean checkForPositiveWeights(ArrayList<Double> weights){
        for(int i = 0 ; i < weights.size(); i++){
            if(weights.get(i) <= 0){
                return false;
            }
        }
        return true;
    }


    public boolean checkForFileNameOfSettings(String nameOfClass){
        if(!nameOfClass.strip().equals("Settings")){
            return true;
        }
        return false;
    }

    public boolean checkIfPointIsDouble(String point){
        try{
            Double.parseDouble(point.strip());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean checkIfSlashIsPresent(String point){
        for(int i = 0 ; i < point.length(); i++){
            if (point.charAt(i) == '/'){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfEditingPointsAreOnlyTwoFields(String points){
        String[] arrPoints = points.split("/");
        if(arrPoints.length != 2) {
            return false;
        }
        return true;
    }
    public boolean checkIfEditingPointsFieldIsDoubles(String points){
        String[] arrPoints = points.split("/");
        try{
            for(int i = 0 ; i < arrPoints.length; i++){
                Double.parseDouble(arrPoints[i]);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }





}
