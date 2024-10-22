package cap2.example.Capstone2_BackEnd.NutriApp.service.commonService;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.CloudinaryReponse;
import cap2.example.Capstone2_BackEnd.NutriApp.enums.ErrorCode;
import cap2.example.Capstone2_BackEnd.NutriApp.exception.AppException;
import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Service
public class CloudinaryService {
    Cloudinary cloudinary;

    @Transactional
    public CloudinaryReponse uploadImage(MultipartFile file, String fileName) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), Map.of("public_id", "tiendev/cap2/" + fileName));
            String url = (String) result.get("secure_url");
            String publicID = (String) result.get("public_id");
            return CloudinaryReponse.builder()
                    .publicID(publicID)
                    .url(url)
                    .build();
        } catch (Exception e) {
            log.error("Error uploading image to Cloudinary: {}", e.getMessage());
            throw new AppException(ErrorCode.UPLOAD_IMAGE_FAILED);
        }
    }

    @Transactional
    public void deleteImage(String publicID) {
        try {
            cloudinary.uploader().destroy(publicID, Map.of());
        } catch (Exception e) {
            throw new AppException(ErrorCode.DELETE_IMAGE_FAILED);
        }
    }
}
