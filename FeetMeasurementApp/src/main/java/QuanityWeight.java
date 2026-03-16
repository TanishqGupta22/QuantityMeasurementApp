import java.util.Objects;

public final class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.0001;

    public QuantityWeight(double value, WeightUnit unit) {

        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // Convert to another weight unit
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(round(converted), targetUnit);
    }

    // UC6 style addition
    public static QuantityWeight add(QuantityWeight a, QuantityWeight b) {
        return add(a, b, a.unit);
    }

    // UC7 style addition with target unit
    public static QuantityWeight add(
            QuantityWeight a,
            QuantityWeight b,
            WeightUnit targetUnit) {

        validate(a, b, targetUnit);

        double baseSum =
                a.unit.convertToBaseUnit(a.value)
                        + b.unit.convertToBaseUnit(b.value);

        double converted = targetUnit.convertFromBaseUnit(baseSum);

        return new QuantityWeight(round(converted), targetUnit);
    }

    private static void validate(
            QuantityWeight a,
            QuantityWeight b,
            WeightUnit targetUnit) {

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
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

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