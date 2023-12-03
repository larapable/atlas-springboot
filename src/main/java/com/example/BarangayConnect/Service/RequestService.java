package com.example.BarangayConnect.Service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.BarangayConnect.Entity.RequestEntity;
import com.example.BarangayConnect.Entity.UserEntity;
import com.example.BarangayConnect.Repository.RequestRepository;
import com.example.BarangayConnect.Repository.UserRepository;


@Service
public class RequestService {
    @Autowired
    RequestRepository rrepo;
    @Autowired
    UserRepository urepo;
    
    //Create
    
       public RequestEntity insertRequest(RequestEntity request) {
        UserEntity user = urepo.findById(request.getUser().getId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        request.setUser(user);
        return rrepo.save(request);
    }

    //Read
    public List<RequestEntity> getAllRequest(){
        return rrepo.findAll();
    }


    //Update
    public RequestEntity updateRequest(int docid, RequestEntity newRequestDetails) {
    RequestEntity existingRequest = rrepo.findById(docid)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found with id: " + docid));
    existingRequest.setTrack(newRequestDetails.getTrack());

    return rrepo.save(existingRequest);
}
    

    public RequestEntity getRequestById(int docid) {
        return rrepo.findById(docid).orElse(null);
    }
    

    public RequestEntity softDeleteRequest(int docid) {
        // Retrieve the request by docid
        RequestEntity existingRequest = getRequestById(docid);
        
        if (existingRequest != null) {
            // Set isDeleted to true
            existingRequest.setIsDeleted(true);
            // Save the updated request to the database
        } 
          return rrepo.save(existingRequest);
    }
    



    //Delete 
    public String deleteRequest(int docid) {
        String msg="";
        if (rrepo.findById(docid) != null) {
            rrepo.deleteById(docid);
            msg = "Request "+docid+" is succesfully deleted!";
        } else 
            msg = "Request "+docid+" does not exist!";
            return msg;
    } 


 }
