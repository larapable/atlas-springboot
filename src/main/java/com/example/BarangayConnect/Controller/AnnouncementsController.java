package com.example.BarangayConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.AnnouncementsEntity;
import com.example.BarangayConnect.Service.AnnouncementsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/announcements")
public class AnnouncementsController {

    @Autowired
    private AnnouncementsService announcementsService;

    @PostMapping("/insertAdminAnnouncement")
    public ResponseEntity<AnnouncementsEntity> insertAdminAnnouncement(  @RequestBody AnnouncementsEntity announcementEntity) {
        AnnouncementsEntity savedEntity = announcementsService.insertAdminAnnouncement(announcementEntity);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getAllAnnouncement")
    public List<AnnouncementsEntity> getAllAnnouncement() {
        return announcementsService.getAllAnnouncement();
    }

    @PutMapping("/updateAnnouncement/{announcementId}")
    public AnnouncementsEntity updateAnnouncement(@PathVariable int announcementId,
            @RequestBody AnnouncementsEntity announcementEntity) {
        return announcementsService.updateAnnouncement(announcementId, announcementEntity);
    }

    @DeleteMapping("/deleteAnnouncement/{announcementId}")
    public void deleteAnnouncement(@PathVariable int announcementId) {
        announcementsService.deleteAnnouncement(announcementId);
    }

    @GetMapping("getInfoById/{announcementId}")
    public AnnouncementsEntity getAnnouncementID(@PathVariable int announcementId) {
        return announcementsService.findById(announcementId);
    }
}


