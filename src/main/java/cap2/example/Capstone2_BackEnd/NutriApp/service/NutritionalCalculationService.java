package cap2.example.Capstone2_BackEnd.NutriApp.service;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.nutritionalCalculation.NutritionalCalculationResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.User;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class NutritionalCalculationService {
    UserRepository userRepository;


    public NutritionalCalculationResponse calculateNutrition(User user) {
        // BMR (Basal Metabolic Rate) là lượng calo cơ bản mà cơ thể cần để duy trì các chức năng sống ở trạng thái nghỉ.
        double bmr = 0;
        if (user.isGender()) {
            bmr = 88.362 + (13.397 * user.getWeight()) + (4.799 * user.getHeight()) - (5.677 * user.getAge());
        } else {
            bmr = 447.593 + (9.247 * user.getWeight()) + (3.098 * user.getHeight()) - (4.330 * user.getAge());
        }


        // TDEE (Total Daily Energy Expenditure) là lượng calo cần thiết để duy trì cân nặng hiện tại của cơ thể.
        double tdee = 0;
        tdee = bmr * user.getActivityFactor().getFactor();

        double caloriesNeeded = 0;
        switch (user.getDietType()) {
            case WEIGHT_LOSS:
                caloriesNeeded = tdee - 500;
                break;
            case WEIGHT_MAINTAIN:
                caloriesNeeded = tdee;
                break;
            case WEIGHT_GAIN:
                caloriesNeeded = tdee + 500;
                break;
        }

        int proteinNeeded = (int) (caloriesNeeded * user.getNutritionPlan().getProtein() / 100 / 4);
        int fatNeeded = (int) (caloriesNeeded * user.getNutritionPlan().getProtein() / 100 / 9);
        int carbsNeeded = (int) (caloriesNeeded * user.getNutritionPlan().getCarbs() / 100 / 4);

        return NutritionalCalculationResponse.builder()
                .caloriesNeeded((int) caloriesNeeded)
                .proteinNeeded(proteinNeeded)
                .fatNeeded(fatNeeded)
                .carbsNeeded(carbsNeeded)
                .build();
    }


}
