package com.unololtd.nazmul.bus.repositoryTest;

import com.unololtd.nazmul.bus.models.Ship;
import com.unololtd.nazmul.bus.models.ShipFacilities;
import com.unololtd.nazmul.bus.repositories.ShipRepository;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShipRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShipRepository shipRepository;

    @Test
    public void aSaveShipAndFindById() {
        // given
        Ship ship = getShip("101", "Teknaf", "Saint martin", "10:00am", false);
        entityManager.persist(ship);
        entityManager.flush();

        Ship ship2 = getShip("102",  "Saint martin", "Teknaf","18:00am", false);
        entityManager.persist(ship2);
        entityManager.flush();

        // when
        Optional<Ship> found = shipRepository.findById(ship.getId());
        Optional<Ship> found2 = shipRepository.findById(ship2.getId());

        // then
        Assertions.assertThat(found.get().getName()).isEqualTo(ship.getName());
        Assertions.assertThat(found2.get().getName()).isEqualTo(ship2.getName());
    }

    @Test
    public void bFindByShipNumber() {
        List<Ship> shipList = shipRepository.findByShipNumber("101");
        System.out.println(shipList.get(0));
        //then
        Assertions.assertThat(shipList.size()).isGreaterThan(0);
    }

    @Test
    public void cFindByDeletedFalse(){
        Ship ship = getShip("101", "Teknaf", "Saint martin", "10:00am", true);
        entityManager.persist(ship);
        entityManager.flush();

        Ship ship2 = getShip("102",  "Saint martin", "Teknaf","18:00am", false);
        entityManager.persist(ship2);
        entityManager.flush();

        List<Ship> shipList = shipRepository.findByDeletedFalse();
        System.out.println("Size :" + shipList.size());
        Assertions.assertThat(shipList.size()).isGreaterThan(0);
        Assertions.assertThat(shipList.contains(shipRepository.findByShipNumber("102").get(0)));
    }

    @Test
    public void zDeleteShips() {
        List<Ship> ship1 = shipRepository.findByShipNumber("101");
        List<Ship> ship2 = shipRepository.findByShipNumber("102");
        System.out.println("Ship 1 Size : " + ship1.size());
        System.out.println("Ship 2 Size : " + ship2.size());
        System.out.println("Initial Count : " + shipRepository.count());
        for (Ship ship : ship1) {
            shipRepository.delete(ship);
        }

        for (Ship ship : ship2) {
            shipRepository.delete(ship);
        }
        System.out.println("Final Count : " + shipRepository.count());

    }
    Ship getShip(String shipNumber, String startingPoint, String droppingPoint, String startTime, boolean deleted){
        Ship ship = new Ship(shipNumber, "Bay - cruise", "019 54 546 665", "2", startingPoint, droppingPoint, startTime, deleted);
        ship.setShipFacilities(getShipFacilities());
        return ship;
    }
    ShipFacilities getShipFacilities() {
        ShipFacilities shipFacilities = new ShipFacilities();
        shipFacilities.setCasino(false);
        shipFacilities.setShops(false);
        shipFacilities.setSpa(false);
        shipFacilities.setFitnessCenter(false);
        shipFacilities.setLibrary(false);
        shipFacilities.setTheatre(false);
        shipFacilities.setCinema(false);
        shipFacilities.setSwimmingPool(false);
        shipFacilities.setHotTub(false);
        shipFacilities.setRestaurant(false);
        shipFacilities.setLounges(false);
        shipFacilities.setGym(false);
        shipFacilities.setBar(false);
        shipFacilities.setWifi(false);
        shipFacilities.setKidsPlayRoom(false);

        return shipFacilities;
    }
}
