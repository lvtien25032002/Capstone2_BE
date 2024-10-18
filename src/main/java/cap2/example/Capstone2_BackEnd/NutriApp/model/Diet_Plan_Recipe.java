package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Diet_Plan_Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Diet_Plan_Recipe_ID;


    @ManyToOne
    @JoinColumn(name = "Diet_Plan_ID")
    Diet_Plan Diet_Plan_ID;

    @ManyToOne
    @JoinColumn(name = "Recipe_ID")
    Recipe Recipe_ID;

}
