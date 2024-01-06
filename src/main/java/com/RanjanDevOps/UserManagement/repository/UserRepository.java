package com.RanjanDevOps.UserManagement.repository;

import com.RanjanDevOps.UserManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
