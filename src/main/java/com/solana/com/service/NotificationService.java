package com.solana.com.service;

import com.solana.com.dao.NotificationRepository;
import com.solana.com.dto.NotificationDTO;
import com.solana.com.mapper.NotificationMapper;
import com.solana.com.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationDTO> notificationDTOs = new ArrayList<NotificationDTO>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = NotificationMapper.INSTANCE.toNotificationDTO(notification);
            notificationDTOs.add(notificationDTO);
        }
        return notificationDTOs;
    }

    public NotificationDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).get();
        NotificationDTO notificationDTO = NotificationMapper.INSTANCE.toNotificationDTO(notification);
        return notificationDTO;
    }

    public NotificationDTO save(NotificationDTO notificationDTO) {
        Notification notification = NotificationMapper.INSTANCE.toNotification(notificationDTO);
        notificationRepository.save(notification);
        return notificationDTO;
    }

    public NotificationDTO update(NotificationDTO notificationDTO) {
        Notification notification = NotificationMapper.INSTANCE.toNotification(notificationDTO);
        notification.setCreatedAt(LocalDateTime.now());
        return NotificationMapper.INSTANCE.toNotificationDTO(notificationRepository.save(notification));
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }


}
