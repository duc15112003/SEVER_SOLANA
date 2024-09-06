package com.solana.com.service;

import com.solana.com.dao.UsersRepository;
import com.solana.com.dto.UsersDTO;
import com.solana.com.mapper.UsersMapper;
import com.solana.com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    UsersMapper usersMapper;

    public Page<UsersDTO> getAll(PageRequest pageRequest) {
        Page<Users> userPage = usersRepository.findAll(pageRequest);
        List<UsersDTO> userDTOlist = new ArrayList<UsersDTO>();
        for (Users user : userPage.getContent()) {
            UsersDTO userDTO = usersMapper.toUsersDTO(user);
            userDTOlist.add(userDTO);
        }
        return new PageImpl<>(userDTOlist, userPage.getPageable(), userPage.getTotalElements());
    }

    public UsersDTO getUsersById(Long id) {
        Optional<Users> users = usersRepository.findById(id);
        return users.map(value -> usersMapper.toUsersDTO(users.get())).orElse(null);
    }

    public UsersDTO save(UsersDTO usersDTO) {
        usersDTO.setId(null);
        Users user = usersMapper.toUsers(usersDTO);
        user.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        return usersMapper.toUsersDTO(usersRepository.save(user));
    }

    public UsersDTO update(UsersDTO usersDTO) {
        Users user = usersMapper.toUsers(usersDTO);
        Optional<Users> usersOptional = usersRepository.findById(user.getId());
        if (usersOptional.isPresent()) {
            user.setCreateAt(usersOptional.get().getCreateAt());
            return usersMapper.toUsersDTO(usersRepository.save(user));
        }else{
            return null;
        }

    }

    public boolean delete(Long id) {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
