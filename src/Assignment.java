public class Assignment {
    private String name;
    private double pointsR;
    private double pointsT;
    String weightName;

    public Assignment(String name, double pointsReceived, double pointsTotal, String weightName) {
        this.name = name;
        this. pointsR = pointsReceived;
        this. pointsT = pointsTotal;
        this.weightName = weightName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPointsR(double pointsR) {
        this.pointsR = pointsR;
    }

    public void setPointsT(double pointsT) {
        this.pointsT = pointsT;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public double getPointsR() {
        return pointsR;
    }

    public double getPointsT() {
        return pointsT;
    }

    public String getName() {
        return name;
    }

    public String getWeightName() {
        return weightName;
    }


    @Override
    public String toString() {
        return this.name + ";;;" + this.pointsR + ";;;" + this.pointsT + ";;;" + this.weightName;
    }
}
