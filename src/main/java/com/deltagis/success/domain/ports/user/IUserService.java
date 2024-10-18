package com.deltagis.success.domain.ports.user;


import com.deltagis.success.domain.entities.user.User;

public interface IUserService {
    User getUserByUsername(String username );
}