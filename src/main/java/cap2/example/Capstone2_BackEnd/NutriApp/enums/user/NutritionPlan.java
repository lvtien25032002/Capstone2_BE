package cap2.example.Capstone2_BackEnd.NutriApp.enums.user;

public enum NutritionPlan {
    // Weight Loss
    DURABLE(55, 15, 30, "Durable"),
    HIGH_PROTEIN(50, 25, 25, "High Protein"),
    LOW_CARB(35, 25, 40, "Low Carb"),

    // Weight Maintain
    BALANCED(60, 10, 30, "Balanced"),
    AFTER_WEIGHT_LOSS(50, 20, 30, "After Weight Loss"),
    MAINTAIN_MUSCLES(50, 25, 25, "Maintain Muscles"),
    // Weight Gain
    HIGH_ENERGY(55, 10, 35, "High Energy"),
    BUILD_MUSCLE(50, 25, 25, "Build Muscle"),
    // All
    ATHLETE(55, 20, 25, "Athlete"),
    CARDIO_TRAINING(60, 20, 20, "Cardio Training");


    private final int protein;
    private final int fat;
    private final int carbs;
    private final String description;


    NutritionPlan(int protein, int fat, int carbs, String description) {
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.description = description;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public String getDescription() {
        return description;
    }

}
