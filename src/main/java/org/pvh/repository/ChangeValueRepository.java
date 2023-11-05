package org.pvh.repository;

import org.pvh.model.entity.ChangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeValueRepository extends JpaRepository<ChangeValue, Long> {
}
