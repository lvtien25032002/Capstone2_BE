package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Diet_Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Diet_Goal_ID;
    private Long User_ID;
    private double calorieTarget;
    private double proteinTarget;
    private double fatTarget;
    private double carbTarget;
    private int mealFrequency;
    private Timestamp createdDate;

   
}
