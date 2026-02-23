package com.quantity;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double valueInFeet = value * unit.getToFeetFactor();
        double convertedValue = valueInFeet / targetUnit.getToFeetFactor();

        return new QuantityLength(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        QuantityLength quantity = new QuantityLength(value, source);
        return quantity.convertTo(target).getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisInFeet = value * unit.getToFeetFactor();
        double otherInFeet = other.value * other.unit.getToFeetFactor();

        return Math.abs(thisInFeet - otherInFeet) < 0.000001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                value * unit.getToFeetFactor()
        );
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}