package ru.zagorodnikova.tm.taskmanager.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sort {
    private String property;
    private org.springframework.data.domain.Sort.Direction direction;
}
