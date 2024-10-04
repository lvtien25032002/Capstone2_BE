package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Admin_ID;
    private String usernane;
    private String password;
    private String email;
    private String fullname;
    private Timestamp createdAt;

    public Admin() {
    }

    public Admin(UUID admin_ID, String usernane, String password, String email, String fullname, Timestamp createdAt) {
        Admin_ID = admin_ID;
        this.usernane = usernane;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.createdAt = createdAt;
    }

    public UUID getAdmin_ID() {
        return Admin_ID;
    }

    public void setAdmin_ID(UUID admin_ID) {
        Admin_ID = admin_ID;
    }

    public String getUsernane() {
        return usernane;
    }

    public void setUsernane(String usernane) {
        this.usernane = usernane;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
