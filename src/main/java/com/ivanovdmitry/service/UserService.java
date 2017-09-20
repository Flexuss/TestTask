package com.ivanovdmitry.service;

import com.ivanovdmitry.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Dmitry on 20.09.2017.
 */

@Service
public interface UserService {
    User findByUsername(String username);

    void save(User user);
}
