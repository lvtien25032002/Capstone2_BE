package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID Favorite_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    User User_ID;

    @OneToMany(mappedBy = "Recipe_ID")
    Set<Recipe> Recipe_ID;

    Date dateAdded;


}
