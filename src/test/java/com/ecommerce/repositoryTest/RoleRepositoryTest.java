package com.ecommerce.repositoryTest;


import com.ecommerce.role.Role;
import com.ecommerce.role.RoleRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RoleRepositoryTest {
    @Autowired
    RoleRepo repo;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = repo.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRoles() {
        Role roleSalesPerson = new Role("SalesPerson", "manage product price, "
                + "customers,shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories,brands, "
                + "products,articles and menus");
        Role roleShipper = new Role("Shipper", "view products,view orders "
                + "and update order status");
        Role roleAssistant = new Role("Assistant", "manage  questions and reviews ");
        repo.saveAll(List.of(roleSalesPerson, roleEditor, roleShipper, roleAssistant));

    }
}