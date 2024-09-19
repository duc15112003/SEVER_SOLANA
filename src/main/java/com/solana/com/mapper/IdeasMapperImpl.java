//package com.solana.com.mapper;
//
//import com.solana.com.dto.IdeasDTO;
//import com.solana.com.model.Account;
//import com.solana.com.model.Ideas;
//
//public class IdeasMapperImpl implements IdeasMapper {
//    @Override
//    public Ideas Ideas(IdeasDTO ideasDTO) {
//        if (ideasDTO == null) {
//            return null;
//        }
//
//        Ideas ideas = new Ideas();
//        // Mapping other properties...
//        ideas.setId(ideasDTO.getId());
//        ideas.setTitle(ideasDTO.getTitle());
//        ideas.setDescription(ideasDTO.getDescription());
//        ideas.setStatus(ideasDTO.getStatus());
//        ideas.setImage(ideasDTO.getImage());
//        ideas.setCreatedAt(ideasDTO.getCreateAt().toLocalDate());
//        ideas.setUpdateAt(ideasDTO.getUpdateAt().toLocalDate());
//        ideas.setEndAt(ideasDTO.getEndAt().toLocalDate());
//        ideas.setCountFeedback(ideasDTO.getCountFeedback());
//        ideas.setAwardForOneFeedback(ideasDTO.getAwardForOneFeedback());
//
//        if (ideasDTO.getAccountUsername() != null) {
//            // Create an Account instance or retrieve it from the database if needed.
//            Account account = new Account();
//            account.setUsername(ideasDTO.getAccountUsername());
//            ideas.setAccount(account);
//        }
//
//        return ideas;
//    }
//
//    @Override
//
//}
