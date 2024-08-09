package com.pettaskmgmntsystem.PetTaskMS.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
}
