package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Diet_Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Diet_Goal_ID;
    Long User_ID;
    double calorieTarget;
    double proteinTarget;
    double fatTarget;
    double carbTarget;
    int mealFrequency;
    Timestamp createdDate;


}
