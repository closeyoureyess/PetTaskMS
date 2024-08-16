package com.pettaskmgmntsystem.PetTaskMS.tms.repository;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    Optional<Tasks> findByHeader(String header);
}
