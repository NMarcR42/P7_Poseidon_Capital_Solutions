package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing User entities.
 * Provides standard CRUD operations and a custom finder.
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	/**
     * Find a user by its username.
     *
     * @param username the username to search
     * @return the matching User, or null if not found
     */
	User findByUsername(String username);

}
