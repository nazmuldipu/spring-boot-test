package com.unololtd.nazmul.bus.services.impl;

import com.unololtd.nazmul.bus.commons.PageAttr;
import com.unololtd.nazmul.bus.models.Ship;
import com.unololtd.nazmul.bus.repositories.ShipRepository;
import com.unololtd.nazmul.bus.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public Optional<Ship> getById(Long id) {
        if(this.exists(id)){
            return this.shipRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Ship> getAll() {
        return this.shipRepository.findAll();
    }

    @Override
    public Page<Ship> getAll(int page) {
        return this.shipRepository.findAll(PageAttr.getPageRequest(page));
    }

    @Override
    public Page<Ship> searchShip(String query, int page) {
        return this.shipRepository.findDistinctByNameContainingIgnoreCaseOrStartingPointContainingIgnoreCaseOrDroppingPointContainingIgnoreCaseAndDeletedFalse(query, query, query, PageAttr.getPageRequest(page));
    }

    @Override
    public Ship save(Ship ship) {
        if(ship.getId() == null){
            ship.setVersion(1);
        } else{
            ship.setVersion(ship.getVersion() + 1);
        }
        return this.shipRepository.save(ship);
    }

    @Override
    public boolean exists(long id) {
        return this.shipRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(this.exists(id)) {
            this.shipRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Set<Ship> getMyShips() {
        return null;
    }
}
