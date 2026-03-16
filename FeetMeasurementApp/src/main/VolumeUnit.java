public enum VolumeUnit implements IMeasurable {

    LITER(1000),
    MILLILITER(1);

    private final double factor;

    VolumeUnit(double factor){
        this.factor = factor;
    }

    public double getConversionFactor(){
        return factor;
    }

    public String getUnitName(){
        return name();
    }
}