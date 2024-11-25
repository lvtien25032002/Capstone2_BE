package cap2.example.Capstone2_BackEnd.NutriApp.enums.user;

public enum ActivityFactor {
    SEDENTARY("Sedentary", 1.2),
    LIGHTLY_ACTIVE("Lightly Active", 1.375),
    MODERATELY_ACTIVE("Moderately Active", 1.55),
    VERY_ACTIVE("Very Active", 1.725),
    EXTRA_ACTIVE("Extra Active", 1.9);

    private final String displayValue;
    private final double factor;

    ActivityFactor(String displayValue, double factor) {
        this.displayValue = displayValue;
        this.factor = factor;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public double getFactor() {
        return factor;
    }
}