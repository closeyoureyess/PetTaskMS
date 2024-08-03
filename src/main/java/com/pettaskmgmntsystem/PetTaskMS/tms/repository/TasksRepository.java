package com.pettaskmgmntsystem.PetTaskMS.tms.repository;

import com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
}
