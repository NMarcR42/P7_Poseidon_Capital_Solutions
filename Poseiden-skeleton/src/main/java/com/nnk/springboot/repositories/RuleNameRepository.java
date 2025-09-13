package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Trade entities.
 * Extends JpaRepository to provide CRUD operations.
 *
 * This interface does not require implementation,
 * Spring Data JPA will automatically provide it at runtime.
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
	// No custom queries here, standard CRUD is enough
}
