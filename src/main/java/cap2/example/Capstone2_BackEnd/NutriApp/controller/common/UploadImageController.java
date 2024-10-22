package cap2.example.Capstone2_BackEnd.NutriApp.controller.common;


import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.request.ImageRequest;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ApiResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.dto.common.response.ImageResponse;
import cap2.example.Capstone2_BackEnd.NutriApp.service.commonService.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadImageController {
    ImageService imageService;

    @PostMapping("/upload")
    public ApiResponse<ImageResponse> uploadImage(@ModelAttribute ImageRequest request) {
        ApiResponse<ImageResponse> imageResponseApiResponse = new ApiResponse<>();
        imageResponseApiResponse.setMessage("Upload image successfully");
        imageResponseApiResponse.setData(imageService.uploadImage(request.getImage(), System.currentTimeMillis() + "_" + request.getImage().getOriginalFilename()));
        return imageResponseApiResponse;
    }

}
