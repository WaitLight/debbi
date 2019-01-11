package org.dl.debbi.common.id.repositroy;

import org.dl.debbi.common.id.domain.IdOrigin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdOriginRepository extends JpaRepository<IdOrigin, Integer> {
    Optional<IdOrigin> findByProcess(int process);
}
