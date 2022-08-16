package com.java.web_ecommerce_spring.serviceImpls;

import com.java.web_ecommerce_spring.domain.Role;
import com.java.web_ecommerce_spring.repositorys.RoleRepository;
import com.java.web_ecommerce_spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleById(int id) {
        return roleRepository.findRoleById(id);
    }
}
