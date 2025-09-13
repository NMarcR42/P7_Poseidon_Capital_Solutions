package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Trade entities.
 * Extends JpaRepository to provide CRUD operations.
 *
 * This interface does not require implementation,
 * Spring Data JPA will automatically provide it at runtime.
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {
	// No custom queries here, standard CRUD is enough
}
