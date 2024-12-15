package cap2.example.Capstone2_BackEnd.NutriApp.enums.user;

public enum NutritionPlan {
    // Weight Loss
    DURABLE(55, 15, 30, "This diet will help you lose weight with lasting results, allowing you to slim gradually while getting" +
            "all the necessary nutrients. This diet is based on balanced, healthy foods in combination with a lowered energy and fats intake.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259022/tiendev/cap2/1734259021799_durable.png.png"
    ),
    HIGH_PROTEIN(50, 25, 25, "This diet contains all your daily required nutrients and is based on a high protein intake." +
            "Protein help build a stronger, leaner body and make you less hungry, so you will find it easier to keep up the lower calorie intake of this weight loss plan.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259049/tiendev/cap2/1734259050985_highProtein.jpg.jpg"),

    LOW_CARB(35, 25, 40, "This diet aim at quick a easy weight loss, although you may not get all your daily recommended nutrients." +
            "We advice you to use this plan for a limited period of time, in example 6 to 8 weeks.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259068/tiendev/cap2/1734259069618_lowcarb.jpg.jpg"),

    // Weight Maintain
    BALANCED(60, 10, 30, "If you have managed to maintain your weight (for some time) and would like to keep it like this, this diet will help you to maintain a healthy" +
            "balance. It is baed on set guidelines of good nutrition and contains all daily nutrient needs.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259134/tiendev/cap2/1734259135507_balanced.jpg.jpg"),
    AFTER_WEIGHT_LOSS(50, 20, 30, "if you have recently lost substantial weight, this diet will help you keep it that way. It will" +
            "provide you with all the necessary nutrients you need and the extra proteins wil help prevent your weight to increase again.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259089/tiendev/cap2/1734259090263_afterweightloss.jpg.jpg"),
    MAINTAIN_MUSCLES(50, 25, 25, "This diet is aimed at (strength) athletes with substantial muscle mass. The increased amount of proteins wil help you with the upkeep" +
            "and building of muscle mass. A balanced amount of fats and carbohydrates will provide you with all the necessary nutrients and energy.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259158/tiendev/cap2/1734259158552_maintain_muscle.jpg.jpg"),
    // Weight Gain
    HIGH_ENERGY(55, 10, 35, "This diet is specifically designed for people who want to gain weight. " +
            "An increased amount of carbohydrates and fats help you to take in sufficient energy to gain weight. Due to the balanced plan, " +
            "it will help you get all the necessary nutrients you need.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259177/tiendev/cap2/1734259176781_highEnergy.jpg.jpg"),
    BUILD_MUSCLE(50, 25, 25, "This diet is aimed at(strength0 athletes who want to build up muscle mass. " +
            "The increased amount of proteins will help you with the development and upkeep of muscle mass, while a balanced amount of fats and " +
            "carbohydrates will give you all the necessary energy and nutrients you need.",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259195/tiendev/cap2/1734259194863_build_muscle.jpg.jpg"),

    // All
    ATHLETE(55, 20, 25, "This plan has been designed for athletes who would like to build up a bit of extra muscle mass to increase performance" +
            ". The increased amount of protein will help you with the upkeep and building of muscle mass, while a balanced amount of" +
            "fats and carbohydrates will give you all the necessary energy .",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259112/tiendev/cap2/1734259112639_athlete.jpg.jpg"),
    CARDIO_TRAINING(60, 20, 20, "This diet is aimed at (cardio) athletes, that have a need for extra energy for high cardio performance," +
            "like running. The high amount of carbohydrates will provide you with sufficient energy, while the proteins make sure lean muscle mass is well maintained",
            "https://res.cloudinary.com/dwq1whrdj/image/upload/v1734259215/tiendev/cap2/1734259216715_cardio.jpg.jpg");


    private final int protein;
    private final int fat;
    private final int carbs;
    private final String description;
    private final String imageURL;


    NutritionPlan(int protein, int fat, int carbs, String description, String imageURL) {
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.description = description;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

}
