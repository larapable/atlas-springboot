package com.example.Atlas.Controller;

import com.example.Atlas.Entity.DepartmentImageEntity;
import com.example.Atlas.Service.DepartmentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class DepartmentImageController {

    @Autowired
    private DepartmentImageService departmentImageService;

    @PostMapping("/insertImage")
    public ResponseEntity<?> uploadDepartmentImage(@RequestParam("department_id") int departmentId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("image_format") String imageFormat) {
        try {
            byte[] imageData = image.getBytes();
            boolean imageExists = departmentImageService.imageExists(departmentId);

            boolean result;
            if (imageExists) {
                result = departmentImageService.updateDepartmentImage(departmentId, imageData, imageFormat);
            } else {
                result = departmentImageService.insertDepartmentImage(departmentId, imageData, imageFormat);
            }

            if (result) {
                return ResponseEntity.ok("Department image saved successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to save department image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving department image.");
        }
    }

    @GetMapping("getImage/{departmentId}")
    public ResponseEntity<ImageResponse> getDepartmentImage(@PathVariable int departmentId) {
        Optional<DepartmentImageEntity> departmentImage = departmentImageService.getDepartmentImage(departmentId);

        return departmentImage.map(image -> {
            String base64Image = Base64.getEncoder().encodeToString(image.getImage());
            return ResponseEntity.ok(new ImageResponse(base64Image, image.getImageFormat()));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ImageResponse(null, null)));
    }

    private static class ImageResponse {
        private String imageData;
        private String imageFormat;

        public ImageResponse(String imageData, String imageFormat) {
            this.imageData = imageData;
            this.imageFormat = imageFormat;
        }

        public String getImageData() {
            return imageData;
        }

        public String getImageFormat() {
            return imageFormat;
        }
    }
}
