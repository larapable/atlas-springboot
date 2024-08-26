package com.example.Atlas.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.UserImageEntity;
import com.example.Atlas.Repository.UserImageRepository;
import com.example.Atlas.Repository.UserRepository;

@Service
public class UserImageService {
    @Autowired
    UserImageRepository userImageRepo;
    @Autowired
    UserRepository userRepo;

    public boolean imageExists(int userId) {
        return userImageRepo.findByUserId(userId).isPresent();
    }

    public boolean insertUserImage(int userId, byte[] image, String imageFormat) {
        try {
            UserImageEntity userImage = new UserImageEntity();
            userImage.setUser(userRepo.findById(userId).orElseThrow());
            userImage.setImage(image);
            userImage.setImageFormat(imageFormat);
            userImageRepo.save(userImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserImage(int userId, byte[] imageData, String imageFormat) {
        try {
            UserImageEntity userImage = userImageRepo.findByUserId(userId)
                    .orElseThrow();
            userImage.setImage(imageData);
            userImage.setImageFormat(imageFormat);
            userImageRepo.save(userImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<UserImageEntity> getUserImage(int userId) {
        return userImageRepo.findByUserId(userId);
    }
    
}
