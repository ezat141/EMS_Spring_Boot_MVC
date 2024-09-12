package com.ebi.app1.repo;

import com.ebi.app1.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
