package com.rezende.commerce.repositories;

import com.rezende.commerce.entities.User;
import com.rezende.commerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);

    @Query(nativeQuery = true, value =
    "SELECT  tb_user.id, tb_user.email AS username, tb_role.authority,  tb_user.password " +
            "FROM tb_user " +
            "INNER JOIN tb_users_roles ON tb_user.id = tb_users_roles.user_id " +
            "INNER JOIN tb_role ON tb_role.id = tb_users_roles.role_id " +
            "WHERE tb_user.email = :email")
    List<UserDetailsProjection> searchUserAndRoleByEmail(String email);
}
