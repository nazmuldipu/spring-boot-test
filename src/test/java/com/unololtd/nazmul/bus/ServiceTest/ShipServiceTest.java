package com.unololtd.nazmul.bus.ServiceTest;

import com.unololtd.nazmul.bus.models.Ship;
import com.unololtd.nazmul.bus.models.ShipFacilities;
import com.unololtd.nazmul.bus.repositories.ShipRepository;
import com.unololtd.nazmul.bus.services.impl.ShipServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
public class ShipServiceTest {

    /*
    * The Service that we want to test
    */
    @InjectMocks
    private ShipServiceImpl shipService;

    /*
    * A Mock version of ShipRepository for use in our test
    */
    @Mock
    ShipRepository shipRepository;

    @Test
    @DisplayName("Test find by Id success")
    public void testFindById(){
        //Setup our mock
        long id = 1;
        Ship mockShip = this.getShip("101", "Teknaf", "Saint martin", "9:30am",false);
        Mockito.when(shipRepository.count()).thenReturn(123L);
        Mockito.when(shipRepository.existsById(id)).thenReturn(true);
        Mockito.when(shipRepository.findById(id)).thenReturn(Optional.of(mockShip));

        //Execute all Service call
        long userCount = shipRepository.count();
        boolean exist = shipService.exists(id);
        Optional<Ship> returnShip = shipService.getById(id);

        //Assert to the response
        Assert.assertEquals(123L, userCount);
        Assert.assertTrue(exist);
        Assertions.assertTrue(returnShip.isPresent(), "Ship was not found");
        Assertions.assertSame(returnShip.get(), mockShip, "Ship should be the same");
    }

    @Test
    @DisplayName("Test find by id not found")
    public  void testFindByIdNotFound(){
        //Setup our mock
        long id = 1;
        Mockito.when(shipRepository.existsById(id)).thenReturn(true);
        Mockito.when(shipRepository.findById(id)).thenReturn(Optional.empty());

        //Execute all Service call
        Optional<Ship> returnShip = shipService.getById(id);

        //Assert to the response
        Assertions.assertFalse(returnShip.isPresent(), "Ship was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test find all")
    public void testFindAll(){
        //Setup our mock
        Ship mockShip = this.getShip("101", "Teknaf", "Saint martin", "9:30am",false);
        Ship mockShip2 = this.getShip("102", "Saint martin", "Teknaf",  "4:30pm",false);
        Mockito.when(shipRepository.findAll()).thenReturn(Arrays.asList(mockShip, mockShip2));

        //Execute all Service call
        List<Ship> shipList = shipService.getAll();

        //Assert to the response
        Assertions.assertEquals(2, shipList.size(), "Find all should return 2 ship");
    }

    @Test
    @DisplayName("Test save ship")
    public void testSaveShip(){
        //Setup our mock
        Ship mockShip = this.getShip("101", "Teknaf", "Saint martin", "9:30am",false);
        Mockito.when(shipRepository.save(any())).thenReturn(mockShip);

        //Execute all Service call
        Ship ship = shipService.save(mockShip);

        //Assert to the response
        Assertions.assertNotNull(ship, "The saved product should not be null");
        Assertions.assertEquals(1, ship.getVersion(), "The version for new product should be 1");
    }


    Ship getShip(String shipNumber, String startingPoint, String droppingPoint, String startTime, boolean deleted){
        Ship ship = new Ship(shipNumber, "Bay - cruise", "019 54 546 665", "2", startingPoint, droppingPoint, startTime, deleted);
        ship.setId(1l);
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
