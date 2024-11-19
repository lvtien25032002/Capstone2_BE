package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Favorite_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    User User_ID;

    @ManyToOne
    @JoinColumn(name = "Recipe_ID")
    Recipe Recipe_ID;
    LocalDate dateAdded;
}
