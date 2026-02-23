import java.util.Objects;

public final class QuantityLength {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

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

    public QuantityLength add(QuantityLength other) {
        Objects.requireNonNull(other, "Second operand cannot be null");

        double baseSum =
                this.unit.toBase(this.value)
              + other.unit.toBase(other.value);

        double resultValue = this.unit.fromBase(baseSum);

        return new QuantityLength(resultValue, this.unit);
    }

    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit) {

        if (a == null || b == null || targetUnit == null) {
            throw new IllegalArgumentException("Null input");
        }

        double baseSum =
                a.unit.toBase(a.value)
              + b.unit.toBase(b.value);

        double result = targetUnit.fromBase(baseSum);
        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityLength)) return false;
        QuantityLength that = (QuantityLength) o;

        double diff =
                Math.abs(
                        this.unit.toBase(this.value)
                      - that.unit.toBase(that.value)
                );

        return diff < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}