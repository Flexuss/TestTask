package com.ivanovdmitry.repository;

import com.ivanovdmitry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitry on 20.09.2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
