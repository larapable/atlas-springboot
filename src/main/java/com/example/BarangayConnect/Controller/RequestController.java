package com.example.BarangayConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.BarangayConnect.Entity.RequestEntity;
import com.example.BarangayConnect.Service.RequestService;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = "http://localhost:3000")
public class RequestController {
    @Autowired
    RequestService rserv;

    @PostMapping("/insertRequest")
    public RequestEntity insertRequest(@RequestBody RequestEntity request) {
        System.out.println(request.getUser().getId());
        request.setTrack("processing");
        return rserv.insertRequest(request);
    }

    @GetMapping("/getAllRequest")
    public List<RequestEntity> getAllRequest() {
        return rserv.getAllRequest();
    }

    @PutMapping("/updateRequest/{docid}")
    public ResponseEntity<RequestEntity> updateRequest(@PathVariable int docid, @RequestBody RequestEntity newRequestDetails) {
    try {
        RequestEntity updatedRequest = rserv.updateRequest(docid, newRequestDetails);
        return ResponseEntity.ok(updatedRequest);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(null);  // You might want to include an error message here
    }
}


    @DeleteMapping("/deleteRequest/{docid}")
    public ResponseEntity<String> deleteRequest(@PathVariable int docid) {
    try {
        // Instead of physically deleting, set isDeleted to true
        rserv.softDeleteRequest(docid);
        return ResponseEntity.ok("Request marked as deleted successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Failed to mark request as deleted: " + e.getMessage());
    }
}


}
