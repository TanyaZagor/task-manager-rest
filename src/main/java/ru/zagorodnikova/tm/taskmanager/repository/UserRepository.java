package ru.zagorodnikova.tm.taskmanager.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.taskmanager.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    @Nullable
    @Query(value = "SELECT user FROM User user WHERE user.login = :login")
    User findByLogin(@NotNull @Param("login") final String login);
}
