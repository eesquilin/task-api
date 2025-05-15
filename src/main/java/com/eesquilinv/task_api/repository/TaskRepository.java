package com.eesquilinv.task_api.repository;

import com.eesquilinv.task_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


}
