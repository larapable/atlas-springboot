package com.example.BarangayConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return rserv.insertRequest(request);
    }

    @GetMapping("/getAllRequest")
    public List<RequestEntity> getAllRequest() {
        return rserv.getAllRequest();
    }

    @PutMapping("/updateRequest") 
    public RequestEntity updateRequest(@RequestParam int docid, @RequestBody RequestEntity newRequestDetails) {
        return rserv.updateRequest(docid, newRequestDetails);
    }

    @DeleteMapping("/deleteRequest/{docid}")
    public String deleteRequest(@PathVariable int docid) {
        return rserv.deleteRequest(docid);
    }

}
