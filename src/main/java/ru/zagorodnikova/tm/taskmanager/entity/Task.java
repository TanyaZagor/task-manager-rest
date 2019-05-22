package ru.zagorodnikova.tm.taskmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.taskmanager.entity.enumeration.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_task")
public class Task {

    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Nullable
    private String userId;

    @Nullable
    private String projectId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateStart;

    @Nullable
    private Date dateFinish;

    @NotNull
    private Date dateCreate = new Date();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name,
                @NotNull String  description, @NotNull Date dateStart, @NotNull Date dateFinish) {
        this.userId = userId;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }
}
