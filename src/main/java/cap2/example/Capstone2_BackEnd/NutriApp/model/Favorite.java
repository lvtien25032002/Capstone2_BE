package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Favorite_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User User_ID;

    @OneToMany(mappedBy = "Recipe_ID")
    private Set<Recipe> Recipe_ID;

    private Date dateAdded;

   
}
