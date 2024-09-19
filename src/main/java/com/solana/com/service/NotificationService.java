package com.solana.com.service;

import com.solana.com.dao.NotificationRepository;
import com.solana.com.dto.NotificationDTO;
import com.solana.com.mapper.NotificationMapper;
import com.solana.com.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;

    public Page<NotificationDTO> getAll(PageRequest pageRequest) {
        Page<Notification> notificationPage = notificationRepository.findAll(pageRequest);
        List<NotificationDTO> notificationDTOlist = new ArrayList<NotificationDTO>();
        for (Notification acc : notificationPage.getContent()) {
            NotificationDTO accDTO = notificationMapper.toNotificationDTO(acc);
            notificationDTOlist.add(accDTO);
        }
        return  new PageImpl<>(notificationDTOlist, notificationPage.getPageable(), notificationPage.getTotalElements());
    }

    public NotificationDTO getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        // hoặc ném một ngoại lệ nếu cần
        return notification.map(value -> notificationMapper.toNotificationDTO(value)).orElse(null);
    }


    public NotificationDTO save(NotificationDTO notificationDTO) {
        Notification notification = notificationMapper.toNotification(notificationDTO);
        return notificationMapper.toNotificationDTO(notificationRepository.save(notification));
    }

    public NotificationDTO update(NotificationDTO NotificationDTO) {
        Notification notification = notificationMapper.toNotification(NotificationDTO);
        return notificationMapper.toNotificationDTO(notificationRepository.save(notification));
    }


    public boolean delete(Long id) {
        if(notificationRepository.findById(id).isPresent()){
            notificationRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
