package com.unololtd.nazmul.bus.repositories;

import com.unololtd.nazmul.bus.models.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findByShipNumber(String shipNumber);

    List<Ship> findByDeletedFalse();

    Page<Ship> findByDeletedFalse(Pageable pageable);

    Page<Ship> findDistinctByNameContainingIgnoreCaseOrStartingPointContainingIgnoreCaseOrDroppingPointContainingIgnoreCaseAndDeletedFalse(String name, String startingPoint, String droppingPoint, Pageable pageable);
}
