package com.example.BarangayConnect.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BarangayConnect.Entity.BusinessEntity;
import com.example.BarangayConnect.Repository.BusinessRepository;

@Service
@Transactional
public class BusinessService {
    
    @Autowired
    private BusinessRepository busRepository;

    //read
    public List<BusinessEntity> getAllBusiness() {
        return busRepository.findAll();
    }

    //add
    public BusinessEntity insertAdminBusiness(BusinessEntity busEntity) {
        return busRepository.save(busEntity);
    }

    //update
    public BusinessEntity updateBusiness(int busId, BusinessEntity newBusinessDetails) {

        BusinessEntity businessList = busRepository.findById(busId)
                .orElseThrow(() -> new NoSuchElementException("Business " + busId + " not found"));

        businessList.setBusTitle(newBusinessDetails.getBusTitle());
        businessList.setDate(newBusinessDetails.getDate());
        businessList.setBusContent(newBusinessDetails.getBusContent());
        //insert photo here?

        return busRepository.save(businessList);
    }

    //delete
    public String deleteBusiness(int busId) {
        Optional<BusinessEntity> directory = busRepository.findById(busId);

        if (directory.isPresent()) {
            BusinessEntity entity = directory.get();

            entity.setIsdelete(1);
            busRepository.save(entity);

            return "Business " + busId + " is marked as deleted!";
        } else {
            return "Business " + busId + " does not exist!";
        }
    }

    public BusinessEntity findById(int busId) {
        return busRepository.findById(busId).orElse(null);
    }





    public void uploadImage(MultipartFile file) throws IOException {
        Path imagesPath = Paths.get("business_images"); // Specify your directory
        if (file.getOriginalFilename() == null) {
            throw new IOException("Original name is null");
        }
        String originalFilename = file.getOriginalFilename();
        Path filePath = imagesPath.resolve(originalFilename);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }


}
