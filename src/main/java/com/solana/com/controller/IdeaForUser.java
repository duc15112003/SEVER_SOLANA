package com.solana.com.controller;

import com.solana.com.dao.IdeasRepository;
import com.solana.com.dto.IdeasDTO;
import com.solana.com.mapper.IdeasMapperImpl;
import com.solana.com.model.Ideas;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.IdeasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.solana.com.mapper.IdeasMapper;
@RestController
@RequestMapping("api/user/idea")
@CrossOrigin("*")
public class IdeaForUser {
    @Autowired
    private IdeasService ideasService;
    @Autowired
    private IdeasRepository ideasRepository;
    @Autowired
    private IdeasMapperImpl ideasMapperImpl;
    @Autowired
    IdeasMapper ideasMapper;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<IdeasDTO>>> getAllForUser(@RequestParam("username") String username) {
        ApiResponse<List<IdeasDTO>> response = new ApiResponse<>();

        // Fetch the ideas based on username and map them to DTOs
        List<Ideas> ideasList = ideasRepository.findIdeasByUsername(username);
        List<IdeasDTO> ideasDTOList = ideasList.stream()
                .map(ideasMapper::toIdeasDTO)
                .collect(Collectors.toList());

        response.setCode(HttpStatus.OK.value());
        response.setMessage("Get all successfully");
        response.setResult(ideasDTOList);

        return ResponseEntity.ok(response);
    }

}
