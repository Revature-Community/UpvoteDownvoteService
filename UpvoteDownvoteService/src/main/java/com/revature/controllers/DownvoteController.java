package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Downvote;
import com.revature.service.DownvoteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/downvote")
public class DownvoteController {

    @Autowired
    private DownvoteService dvservice; 
    
    @GetMapping("/{postId}")
    public ResponseEntity<Integer> getalldownvotesbypostid(@PathVariable("postId") int postId) { 
        List<Downvote> dv = dvservice.getAllDownvotesByPostId(postId);
        return new ResponseEntity<Integer>(dv.size(), HttpStatus.OK); 
    }
    
    @PostMapping("/add-downvote")
    public ResponseEntity<Downvote> save(@RequestBody Downvote downvote) throws IOException {

        Downvote r = new Downvote(); 

        r.setPostId(downvote.getPostId());
        r.setUserId(downvote.getUserId());
        Downvote responseObject = dvservice.submitDownvote(downvote);
        return new ResponseEntity<Downvote>(responseObject, HttpStatus.OK);
    }
	
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Downvote> delete(@PathVariable("id") int id) {
        dvservice.deleteDownvote(id);
        return new ResponseEntity<Downvote>(HttpStatus.OK);
    }
    
    
}
