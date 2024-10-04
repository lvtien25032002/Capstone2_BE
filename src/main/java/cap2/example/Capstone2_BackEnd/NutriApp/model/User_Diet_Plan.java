package cap2.example.Capstone2_BackEnd.NutriApp.model;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Date;
import java.util.UUID;

@Entity
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

    public User_Diet_Plan() {
    }

    public User_Diet_Plan(UUID user_Diet_Plan_ID, User user_ID, Diet_Plan diet_Plan_ID, Date startDate, Date endDate) {
        User_Diet_Plan_ID = user_Diet_Plan_ID;
        User_ID = user_ID;
        Diet_Plan_ID = diet_Plan_ID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getUser_Diet_Plan_ID() {
        return User_Diet_Plan_ID;
    }

    public void setUser_Diet_Plan_ID(UUID user_Diet_Plan_ID) {
        User_Diet_Plan_ID = user_Diet_Plan_ID;
    }

    public User getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(User user_ID) {
        User_ID = user_ID;
    }

    public Diet_Plan getDiet_Plan_ID() {
        return Diet_Plan_ID;
    }

    public void setDiet_Plan_ID(Diet_Plan diet_Plan_ID) {
        Diet_Plan_ID = diet_Plan_ID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
