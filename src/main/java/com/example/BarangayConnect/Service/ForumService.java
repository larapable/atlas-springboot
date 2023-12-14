package com.example.BarangayConnect.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.BarangayConnect.Entity.ForumEntity;
import com.example.BarangayConnect.Entity.ReplyEntity;
import com.example.BarangayConnect.Entity.UserEntity;
import com.example.BarangayConnect.Repository.ForumRepository;
import com.example.BarangayConnect.Repository.ReplyRepository;
import com.example.BarangayConnect.Repository.UserRepository;

@Service
public class ForumService {
    @Autowired
    ForumRepository frepo;
    @Autowired
    UserRepository urepo;
    @Autowired
    ReplyRepository rrepo;
    
    //Create
    
       public ForumEntity insertForum(ForumEntity forum) {
        UserEntity user = urepo.findById(forum.getUser().getId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        forum.setUser(user);
        return frepo.save(forum);
    }

    //Read
    public List<ForumEntity> getAllPosts(){
        return frepo.findAll();
    }

    //Update Post
    public ForumEntity updatePost(int forumid, ForumEntity newPostDetails) {
    ForumEntity existingPost = frepo.findById(forumid)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found with id: " + forumid));
    existingPost.setPost(newPostDetails.getPost());

    return frepo.save(existingPost);
    }

    //Soft Delete

    public ForumEntity getForumById(int forumid) {
        return frepo.findById(forumid).orElse(null);
    }
    
      public ForumEntity softDeleteRequest(int forumid) {
        // Retrieve the request by docid
        ForumEntity existingPost = getForumById(forumid);
        
        if (existingPost != null) {
            // Set isDeleted to true
            existingPost.setIsDeleted(true);
            // Save the updated request to the database
        } 
          return frepo.save(existingPost);
    }

    
    //Reply
     public ReplyEntity addReply(int userId, int postId, String replyContent) {
        UserEntity user = urepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        ForumEntity post = frepo.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));

        ReplyEntity reply = new ReplyEntity();
        reply.setUser(user);
        reply.setForum(post);
        reply.setReply(replyContent);

        return rrepo.save(reply);
    }
    
    public List<ReplyEntity> getRepliesByPostId(int postId) {
        ForumEntity post = frepo.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));

        // Assuming you have a method in the ReplyRepository to find replies by post
        return rrepo.findByForum(post);
    }

  


}
           
