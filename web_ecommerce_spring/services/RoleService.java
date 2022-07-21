package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findRoleById(int id);
}
