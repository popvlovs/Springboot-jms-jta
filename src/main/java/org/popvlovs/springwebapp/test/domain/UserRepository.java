/**
 * Created by Think on 2017/8/1.
 */
package org.popvlovs.springwebapp.test.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    List<User> findByEml(String email);

    List<User> findByNameAndEmail(String name, String email);

    List<User> findByName(String name);
}