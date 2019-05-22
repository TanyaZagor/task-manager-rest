package ru.zagorodnikova.tm.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.taskmanager.entity.Project;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project, String> {
}
