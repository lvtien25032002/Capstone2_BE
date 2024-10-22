package cap2.example.Capstone2_BackEnd.NutriApp.service.commonService;

import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.CloudinaryReponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ImageResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.model.Image;
import cap2.example.Capstone2_BackEnd.NutriApp.repository.commonRepository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class ImageService {
    CloudinaryService cloudinaryService;
    ImageRepository imageRepository;


    public ImageResponse uploadImage(MultipartFile file, String fileName) {
        CloudinaryReponse cloudinaryResponse = cloudinaryService.uploadImage(file, fileName);
        Image image = Image.builder()
                .publicID(cloudinaryResponse.getPublicID())
                .url(cloudinaryResponse.getUrl())
                .build();
        imageRepository.save(image);
        return ImageResponse.builder()
                .url(image.getUrl())
                .build();
    }

    public Void deleteImage(Image imageURL) {
        if (imageURL == null) {
            return null;
        }
        cloudinaryService.deleteImage(imageURL.getPublicID());
        imageRepository.delete(imageURL);
        return null;
    }
}
