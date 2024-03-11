package com.hierarchy.project.util;

import java.time.LocalDateTime;
import java.util.Date;

public class CurrentDateTime {
    public Date getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date currentDate = java.sql.Timestamp.valueOf(currentDateTime);
        return currentDate;
    }
}
