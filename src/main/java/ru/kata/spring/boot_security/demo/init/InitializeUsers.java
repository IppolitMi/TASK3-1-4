package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitializeUsers {
    private final UserService userService;
    private final RoleService roleService;
    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");
    private final Set<Role> roles1 = new HashSet<>(Set.of(adminRole, userRole));
    private final Set<Role> roles2 = new HashSet<>(Set.of(userRole));
    private final User admin = new User("admin@gmail.com", "admin", 24, "admin", roles1);
    private final User user = new User("user@gmail.com", "user", 24, "user", roles2);

    @Autowired
    public InitializeUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    public void init() {
        roleService.save(adminRole);
        roleService.save(userRole);
        userService.addUser(admin);
        userService.addUser(user);
    }
}