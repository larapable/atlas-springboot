package com.example.BarangayConnect.Service;

import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BarangayConnect.Repository.AnnouncementsRepository;
import com.example.BarangayConnect.Entity.AnnouncementsEntity;

@Service
@Transactional
public class AnnouncementsService {

    @Autowired
    private AnnouncementsRepository announcementsRepository;

    //read
    public List<AnnouncementsEntity> getAllAnnouncement() {
        return announcementsRepository.findAll();
    }

    //add
    public AnnouncementsEntity insertAdminAnnouncement(AnnouncementsEntity announcementEntity) {
        return announcementsRepository.save(announcementEntity);
    }
   
   //update
    public AnnouncementsEntity updateAnnouncement(int announcementId, AnnouncementsEntity newAnnouncementDetails) {

        AnnouncementsEntity announcementList = announcementsRepository.findById(announcementId)
                .orElseThrow(() -> new NoSuchElementException("Announcement " + announcementId + " not found"));

        announcementList.setAnnouncementTitle(newAnnouncementDetails.getAnnouncementTitle());
        announcementList.setDate(newAnnouncementDetails.getDate());
        announcementList.setAnnouncementContent(newAnnouncementDetails.getAnnouncementContent());

        return announcementsRepository.save(announcementList);
    }
    //delete
    public String deleteAnnouncement(int announcementId) {
        Optional<AnnouncementsEntity> directory = announcementsRepository.findById(announcementId);

        if (directory.isPresent()) {
            AnnouncementsEntity entity = directory.get();

            // Soft delete by updating isdelete field to 1
            entity.setIsdelete(1);
            announcementsRepository.save(entity);

            return "Announcement " + announcementId + " is marked as deleted!";
        } else {
            return "Announcement " + announcementId + " does not exist!";
        }
    }

    public AnnouncementsEntity findById(int announcementId) {
        return announcementsRepository.findById(announcementId).orElse(null);
    }
}
