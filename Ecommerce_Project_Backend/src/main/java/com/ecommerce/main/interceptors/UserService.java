package com.ecommerce.main.interceptors;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User loginUser (User user);
    public boolean addUser(User user) ;
    public boolean removeUser(User user);
}

