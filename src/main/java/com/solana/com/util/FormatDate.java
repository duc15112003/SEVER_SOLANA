package com.solana.com.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    static String partten = "yyyy-MM-dd HH:mm:ss";

    public static String FormatTimestampToString(Timestamp timestamp) {
        // Định dạng mong muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Chuyển đổi Timestamp thành LocalDateTime
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        // Định dạng LocalDateTime thành chuỗi theo định dạng đã cho
        return localDateTime.format(formatter);
    }
}
