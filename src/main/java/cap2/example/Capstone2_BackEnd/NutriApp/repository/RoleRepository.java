package cap2.example.Capstone2_BackEnd.NutriApp.repository;


import cap2.example.Capstone2_BackEnd.NutriApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}
