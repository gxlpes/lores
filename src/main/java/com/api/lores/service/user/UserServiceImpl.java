package com.api.lores.service.user;

import com.api.lores.dto.UserDto;
import com.api.lores.enums.RoleName;
import com.api.lores.mapper.UserMapper;
import com.api.lores.model.RoleModel;
import com.api.lores.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String create(UserDto dto) {
        var isSamePassword = dto.getPassword().equals(dto.getRetypePassword());

        if (!isSamePassword) {
            return "Senhas não são iguais.";
        }

        var exists = userRepository.findByUsername(dto.getUsername()).isPresent();
        if (exists) {
            return "Usuário já cadastrado.";
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        var userToSave = userMapper.toModel(dto);

        RoleName role;
        if (dto.getRoleName().equals("1")) {
            role = RoleName.ROLE_ADMIN;
        } else {
            role = RoleName.ROLE_USER;
        }

        var roles = setUserRoles((Long.parseLong(dto.getRoleName())), role);
        userToSave.setRoles(roles);

        userRepository.save(userToSave);
        return "Usuário cadastrado com sucesso.";
    }

    private List<RoleModel> setUserRoles(long id, RoleName roleName) {
        var role = new RoleModel();
        role.setRoleId(id);
        role.setRoleName(roleName);
        var roles = new ArrayList<RoleModel>();
        roles.add(role);

        return roles;
    }
}