package com.solana.com.service;

import com.solana.com.dao.UsersRepository;
import com.solana.com.dto.UsersDTO;
import com.solana.com.mapper.UsersMapper;
import com.solana.com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users ;
    }

    public UsersDTO getUserById(Long id) {
        Users users = usersRepository.findById(id).get();
        UsersDTO usersDTO = UsersMapper.INSTANCE.toUsersDto(users);
        return usersDTO;
    }

    public UsersDTO save(UsersDTO usersDTO) {
        Users user = UsersMapper.INSTANCE.toUsers(usersDTO);
        usersRepository.save(user);
        return usersDTO;
    }

    public UsersDTO findUser(Long id) {
        Users user = usersRepository.findById(id).get();
        return UsersMapper.INSTANCE.toUsersDto(user);
    }

    public UsersDTO update(UsersDTO usersDTO) {
        Users user = UsersMapper.INSTANCE.toUsers(usersDTO);
        user.setCreateAt(LocalDate.now());
        return UsersMapper.INSTANCE.toUsersDto(usersRepository.save(user));
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

}
