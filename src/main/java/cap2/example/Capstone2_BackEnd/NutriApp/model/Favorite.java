package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
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

    public Favorite() {
    }

    public Favorite(UUID favorite_ID, User user_ID, Set<Recipe> recipe_ID, Date dateAdded) {
        Favorite_ID = favorite_ID;
        User_ID = user_ID;
        Recipe_ID = recipe_ID;
        this.dateAdded = dateAdded;
    }

    public UUID getFavorite_ID() {
        return Favorite_ID;
    }

    public void setFavorite_ID(UUID favorite_ID) {
        Favorite_ID = favorite_ID;
    }

    public User getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(User user_ID) {
        User_ID = user_ID;
    }

    public Set<Recipe> getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(Set<Recipe> recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
