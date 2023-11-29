package com.example.BarangayConnect.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.AnnouncementEntity;
import com.example.BarangayConnect.Repository.AnnouncementRepository;
import com.example.BarangayConnect.Service.AnnouncementService;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService aserv;
    AnnouncementRepository arepo;


    //CREATE ANNOUNCEMENT RECORD
	@PostMapping ("/insertAnnouncement")
	public AnnouncementEntity insertAnnouncement(@RequestBody AnnouncementEntity announcement) {
		return aserv.insertAnnouncement(announcement);
	}
    //READ ALL ANNOUNCEMENT RECORD IN TBLANNOUNCEMENT
	@GetMapping ("/getAllAnnouncement")
	public List<AnnouncementEntity> getAllAnnouncement(){
		return aserv.getAllAnnouncement();
	}

    // Read by id
    @GetMapping("getAnnouncementById/{annId}")
    public Optional<AnnouncementEntity> getAnnouncementById(@PathVariable int annId) {
        return aserv.getAnnouncementById(annId);
    }

     // Update
    @PutMapping("updateInfo")
    public AnnouncementEntity updateInfo(@PathVariable int annId, @RequestBody AnnouncementEntity newAnnouncementDetails) {
        return aserv.updateInfo(annId, newAnnouncementDetails);
    }

    //DELETE ANNOUNCEMENT RECORD
	@DeleteMapping ("/deleteAnnouncement/{annId}")
	public String deleteAnnouncement(@PathVariable int annId) {
		return aserv.deleteAnnouncement(annId);
	}
}
