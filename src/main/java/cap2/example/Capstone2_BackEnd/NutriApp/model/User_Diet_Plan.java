package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class User_Diet_Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String User_Diet_Plan_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    User User_ID;

    @ManyToOne
    @JoinColumn(name = "Diet_Plan_ID")
    Diet_Plan Diet_Plan_ID;

    Date startDate;
    Date endDate;

}
