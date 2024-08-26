package com.example.Atlas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.Atlas.Entity.UserImageEntity;
import com.example.Atlas.Service.UserImageService;

@RestController
@RequestMapping("/userImage")
@CrossOrigin
public class UserImageController {
    @Autowired
    private UserImageService userImageService;

    @PostMapping("/insertUserImage")
    public ResponseEntity<?> uploadUserImage(@RequestParam("user_id") int userId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("image_format") String imageFormat) {
        try {
            byte[] imageData = image.getBytes();
            boolean imageExists = userImageService.imageExists(userId);

            boolean result;
            if (imageExists) {
                result = userImageService.updateUserImage(userId, imageData, imageFormat);
            } else {
                result = userImageService.insertUserImage(userId, imageData, imageFormat);
            }

            if (result) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "User image saved successfully.");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Failed to save user image.");
                return ResponseEntity.status(500).body(errorResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("An error occurred while saving user image.");
        }
    }

    @GetMapping("getImage/{userId}")
    public ResponseEntity<ImageResponse> getUserImage(@PathVariable int userId) {
        Optional<UserImageEntity> userImage = userImageService.getUserImage(userId);

        return userImage.map(image -> {
            String base64Image = Base64.getEncoder().encodeToString(image.getImage());
            return ResponseEntity.ok(new ImageResponse(base64Image, image.getImageFormat()));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ImageResponse(null, null)));
    }

    private static class ImageResponse {
        private String image;
        private String imageFormat;

        public ImageResponse(String image, String imageFormat) {
            this.image = image;
            this.imageFormat = imageFormat;
        }

        public String getImage() {
            return image;
        }

        public String getImageFormat() {
            return imageFormat;
        }
    }
}
