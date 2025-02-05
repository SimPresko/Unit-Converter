//Abstract class Unit so it can be a parent to unit types that don't have linear conversion rate

public abstract class Unit {

    private String name;
    private String type;

    //Set constructor
    Unit(String name,String type){
        this.name = name;
        this.type = type;
    }

    //Getters and Setters for name and type
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    //Abstract classes that can be overridden in the corresponding child class
    public abstract double toBaseUnit(double value);

    public abstract double fromBaseUnit(double value);
}
