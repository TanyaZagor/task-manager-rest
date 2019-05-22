package ru.zagorodnikova.tm.taskmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.taskmanager.entity.enumeration.RoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class User implements Serializable {

    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Column(unique = true)
    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType roleType = RoleType.USER;

    public User(@Nullable String login, @Nullable String password, @Nullable String firstName,
                @Nullable String lastName, @Nullable String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
