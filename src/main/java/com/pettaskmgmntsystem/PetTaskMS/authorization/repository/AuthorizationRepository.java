package com.pettaskmgmntsystem.PetTaskMS.authorization.repository;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasees.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<User, Integer> {
}
