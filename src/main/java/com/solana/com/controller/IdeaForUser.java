package com.solana.com.controller;

import com.solana.com.dao.IdeasRepository;
import com.solana.com.model.Ideas;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.IdeasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/idea")
@CrossOrigin("*")
public class IdeaForUser {
    @Autowired
    private IdeasService ideasService;
    @Autowired
    private IdeasRepository ideasRepository;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Ideas>>> getAllForUser(@RequestParam("username") String username) {
        ApiResponse<List<Ideas>> response = new ApiResponse<>();
        List<Ideas> list = ideasRepository.findIdeasByUsername(username);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("get All successfuly");
        response.setResult(list);
        return ResponseEntity.ok(response);
    }
}
