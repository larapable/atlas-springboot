package com.example.BarangayConnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarangayConnect.Entity.AnnouncementEntity;
import com.example.BarangayConnect.Repository.AnnouncementRepository;

import java.util.*;

@Service
public class AnnouncementService {

    @Autowired
    AnnouncementRepository arepo;

    // Create
    public AnnouncementEntity insertAnnouncement(AnnouncementEntity announcement) {
        return arepo.save(announcement);
    }
    //Read
	public List<AnnouncementEntity> getAllAnnouncement(){
		return arepo.findAll();
	}

     // Read by ID
     public Optional<AnnouncementEntity> getAnnouncementById(int annId) {
        return arepo.findById(annId);
    }

    // Update
    @SuppressWarnings("finally")
    public AnnouncementEntity updateInfo(int annId, AnnouncementEntity newAnnouncementDetails) {
        AnnouncementEntity annInfo = new AnnouncementEntity();

        try {
            // search the id number of announcement that will be updated
            annInfo = arepo.findById(annId).orElseThrow(() -> new NoSuchElementException("Announcement " + annId + " does not exist!"));

            annInfo.setTitle(newAnnouncementDetails.getTitle());
            annInfo.setContent(newAnnouncementDetails.getContent());
            annInfo.setDate(newAnnouncementDetails.getDate());
            // return arepo.save(annInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Announcement " + annId + " does not exist!");
        } finally {
            return arepo.save(annInfo);
        }
    }


    //Delete
    public String deleteAnnouncement(int annId) {
        String msg = "";
        if(arepo.findById(annId) != null) {
            arepo.deleteById(annId);
            msg = "Announcement " + annId + " is successfully deleted!";
        } else 
            msg = "Announcement " + annId + " does not exist.";
        return msg;
    }
}