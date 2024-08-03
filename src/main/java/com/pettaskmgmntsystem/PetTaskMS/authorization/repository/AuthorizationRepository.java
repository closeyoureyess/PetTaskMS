package com.pettaskmgmntsystem.PetTaskMS.authorization.repository;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Users, Integer> {
}
