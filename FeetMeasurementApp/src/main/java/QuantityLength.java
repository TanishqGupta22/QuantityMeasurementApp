import java.util.Objects;

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.01;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
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

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(round(converted), targetUnit);
    }

    // UC6 compatibility (implicit target)
    public static QuantityLength add(QuantityLength a, QuantityLength b) {
        return add(a, b, a.unit);
    }

    
    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit) {

        validate(a, b, targetUnit);

        double baseSum =
                a.unit.convertToBaseUnit(a.value)
                        + b.unit.convertToBaseUnit(b.value);

        double converted = targetUnit.convertFromBaseUnit(baseSum);

        return new QuantityLength(round(converted), targetUnit);
    }

    private static void validate(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit) {

        if (a == null || b == null || targetUnit == null) {
            throw new IllegalArgumentException("Null argument not allowed");
        }
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof QuantityLength other)) return false;

        double baseA = unit.convertToBaseUnit(value);
        double baseB = other.unit.convertToBaseUnit(other.value);

        return Math.abs(baseA - baseB) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}