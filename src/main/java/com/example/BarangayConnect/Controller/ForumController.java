package com.example.BarangayConnect.Controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.ForumEntity;
import com.example.BarangayConnect.Entity.ReplyEntity;
import com.example.BarangayConnect.Service.ForumService;

@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "http://localhost:3000")
public class ForumController {
    @Autowired
    ForumService fserv;

    @PostMapping("/addPosts")
    public ForumEntity insertForum(@RequestBody ForumEntity forum) {
        System.out.println(forum.getUser().getId());
        return fserv.insertForum(forum);
    }

    @GetMapping("/getPosts")
    public List<ForumEntity> getAllPosts() {
        return fserv.getAllPosts();
    }

    @PutMapping("/updatePost/{forumid}")
    public ResponseEntity<ForumEntity> updatePost(@PathVariable int forumid, @RequestBody ForumEntity newPostDetails) {
    try {
        ForumEntity updatedPost = fserv.updatePost(forumid, newPostDetails);
        return ResponseEntity.ok(updatedPost);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(null);  // You might want to include an error message here
    }
}

    
   @DeleteMapping("/deletePost/{forumid}")
    public ResponseEntity<String> deletePost(@PathVariable int forumid) {
    try {
        // Instead of physically deleting, set isDeleted to true
        fserv.softDeleteRequest(forumid);
        return ResponseEntity.ok("Post marked as deleted successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Failed to mark post as deleted: " + e.getMessage());
    }
}



      @PostMapping("/addReply")
    public ResponseEntity<ReplyEntity> addReply(@RequestBody Map<String, Object> replyRequest) {
        try {
            int userId = (int) replyRequest.get("userId");
            int postId = (int) replyRequest.get("postId");
            String replyContent = (String) replyRequest.get("replyContent");

            ReplyEntity reply = fserv.addReply(userId, postId, replyContent);
            return ResponseEntity.status(HttpStatus.CREATED).body(reply);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getReplies")
    public ResponseEntity<List<ReplyEntity>> getReplies(@RequestParam("postId") int postId) {
    try {
        List<ReplyEntity> replies = fserv.getRepliesByPostId(postId);
        if (replies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(replies);
    } catch (NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

@PutMapping("/updateReply/{replyId}")
public ResponseEntity<ReplyEntity> updateReply(@PathVariable int replyId, @RequestBody String newReplyContent) {
    try {
        ReplyEntity updatedReply = fserv.updateReply(replyId, newReplyContent);
        return ResponseEntity.ok(updatedReply);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(null);  // You might want to include an error message here
    }
}


}
