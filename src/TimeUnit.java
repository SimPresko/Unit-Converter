public class TimeUnit extends Unit{

    //Additional conversion factor value for a linear conversion type like Time
    double convFactor;

    //Parent constructor + conversion factor
    TimeUnit(String name, String type, double convFactor) {
        super(name, type);
        this.convFactor = convFactor;
    }

    //Overriding abstract methods to return the value * or / the conversion factor
    @Override
    public double toBaseUnit(double value) {
        return value/convFactor;
    }

    @Override
    public double fromBaseUnit(double value) {
        return value*convFactor;
    }
}
