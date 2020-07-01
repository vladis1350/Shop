package com.vladis1350.auth.repositories;

import com.vladis1350.auth.bean.Role;
import com.vladis1350.auth.bean.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(UserRoles role);
}
