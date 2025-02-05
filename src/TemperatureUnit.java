public class TemperatureUnit extends Unit{

    //Parent constructor
    TemperatureUnit(String name, String type) {
        super(name, type);
    }

    //Overriding the abstract methods to calculate non-linear conversion
    @Override
    public double toBaseUnit(double value){
        return switch (this.getName()) {
            case "Fahrenheit" -> (value - 32.0) / 1.8;
            case "Kelvin" -> value - 273.15;
            case "Celsius" -> value;
            default -> -1.0;
        };
    }

    @Override
    public double fromBaseUnit(double value){
        return switch (this.getName()) {
            case "Fahrenheit" -> (value * 1.8) + 32;
            case "Kelvin" -> value + 273.15;
            case "Celsius" -> value;
            default -> -1.0;
         };
    }
}
