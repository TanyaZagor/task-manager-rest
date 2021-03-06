package ru.zagorodnikova.tm.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.taskmanager.entity.enumeration.Status;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "app_project")
public class ProjectDto {
    @Id
    @NotNull
    private String id;

    @Nullable
    private String userId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateFinish;

    @Nullable
    private Date dateStart;

    @Nullable
    private Date dateCreate;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Status status;
}
