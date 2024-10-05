package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User_Diet_Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID User_Diet_Plan_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User User_ID;

    @ManyToOne
    @JoinColumn(name = "Diet_Plan_ID")
    private Diet_Plan Diet_Plan_ID;

    private Date startDate;
    private Date endDate;

}
