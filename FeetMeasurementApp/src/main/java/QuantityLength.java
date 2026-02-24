import java.util.Objects;

public final class QuantityLength {

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

    /* ---------- UC6: implicit target (first operand) ---------- */
    public static QuantityLength add(QuantityLength a, QuantityLength b) {
        return add(a, b, a.unit);
    }

    /* ---------- UC7: explicit target unit ---------- */
    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit) {

        validate(a, b, targetUnit);

        double baseSum = toBase(a) + toBase(b);
        double converted = targetUnit.fromBase(baseSum);

        return new QuantityLength(round(converted), targetUnit);
    }

    /* ---------- private helpers ---------- */
    private static double toBase(QuantityLength q) {
        return q.unit.toBase(q.value);
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
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}