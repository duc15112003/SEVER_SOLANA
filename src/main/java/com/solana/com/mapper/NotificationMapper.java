package com.solana.com.mapper;

import com.solana.com.dto.NotificationDTO;

import com.solana.com.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    Notification toNotification(NotificationDTO notificationDTO);
    NotificationDTO toNotificationDTO(Notification notification);
}
