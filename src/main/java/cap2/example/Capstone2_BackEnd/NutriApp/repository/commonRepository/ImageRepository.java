package cap2.example.Capstone2_BackEnd.NutriApp.repository.commonRepository;


import cap2.example.Capstone2_BackEnd.NutriApp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findImageByUrl(String url);
}
