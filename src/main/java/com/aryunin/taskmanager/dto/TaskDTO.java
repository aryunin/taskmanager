package com.aryunin.taskmanager.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDate creationDate;
}
