    VolumeUnit(double conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double convertToBase(double value){
        return value * conversionFactor;
    }

    @Override
    public double convertFromBase(double value){
        return value / conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName(){
        return this.name();
    }

}