package com.example.carservice.config;

import com.example.carservice.data.entity.Role;
import com.example.carservice.data.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class DbInit implements
        ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!roleRepository.getById((long)1).getAuthority().equals("ADMIN")) {
            Role admin = new Role();
            admin.setId(1);
            admin.setAuthority("ADMIN");
            roleRepository.save(admin);
        }
        if(!roleRepository.getById((long)1).getAuthority().equals("EMPLOYEE")) {
            Role employee = new Role();
            employee.setId(2);
            employee.setAuthority("EMPLOYEE");
            roleRepository.save(employee);
        }
        if(!roleRepository.getById((long)1).getAuthority().equals("EMPLOYEE")) {
            Role customer = new Role();
            customer.setId(3);
            customer.setAuthority("CUSTOMER");
            roleRepository.save(customer);
        }
    }
}
