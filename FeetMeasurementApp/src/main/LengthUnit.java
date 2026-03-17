    private final double convertToFeet;

    LengthUnit(double convertToFeet){
        this.convertToFeet = convertToFeet;
    }

    @Override
    public double convertToBase(double value){
        return value * convertToFeet;
    }

    @Override
    public double convertFromBase(double value){
        return value/convertToFeet;
    }

    @Override
    public double getConversionFactor(){ return convertToFeet; }

    @Override
    public String getUnitName() {
        return this.name();
    }
}