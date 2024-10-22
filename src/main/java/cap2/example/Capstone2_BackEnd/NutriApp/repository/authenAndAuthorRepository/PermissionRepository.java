package cap2.example.Capstone2_BackEnd.NutriApp.repository.authenAndAuthorRepository;

import cap2.example.Capstone2_BackEnd.NutriApp.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
