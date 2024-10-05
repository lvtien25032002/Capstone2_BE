package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Diet_Plan_Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Diet_Plan_Recipe_ID;


    @ManyToOne
    @JoinColumn(name = "Diet_Plan_ID")
    private Diet_Plan Diet_Plan_ID;

    @ManyToOne
    @JoinColumn(name = "Recipe_ID")
    private Recipe Recipe_ID;

}
