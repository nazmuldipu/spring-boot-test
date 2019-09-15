package com.unololtd.nazmul.bus.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unololtd.nazmul.bus.controller.api.ship.ShipController;
import com.unololtd.nazmul.bus.models.Ship;
import com.unololtd.nazmul.bus.models.ShipFacilities;
import com.unololtd.nazmul.bus.services.ShipService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipController.class)
public class ShipControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShipService service;

    @Test
    public void givenShip_whenGetShips_thenReturnJsonArray()
            throws Exception {

        Ship ship = getShip("101", "Teknaf", "Saintmartin", "9:30am", false);
        List<Ship> shipList = Arrays.asList(ship);
        final Page<Ship> page = new PageImpl<>(shipList);
        given(service.searchShip("", 0)).willReturn(page);

        mvc.perform(get("/api/v1/ships")
                //validate the response code and content type
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //validate the returned fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name", is(ship.getName())));
    }

    @Test
    @DisplayName("Get /api/v1/ships/1 - Found ")
    public void testGetShipByIdFound() throws Exception {
        //setup our mock service
        Ship ship = getShip("101", "Teknaf", "Saintmartin", "9:30am", false);
        ship.setId(1L);
        ship.setVersion(1);
        doReturn(Optional.of(ship)).when(service).getById(1L);

        // Execute the get request
        mvc.perform(get("/api/v1/ships/1")
                //validate the response code and content type
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                //validate the headers
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/ships/1"))

                //validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(ship.getName())))
                .andExpect(jsonPath("$.version", is(ship.getVersion())));

    }

    @Test
    @DisplayName("Get /api/v1/ships/1 - Not Found ")
    public void testGetShipByIdNotFound() throws Exception {
        //setup our mock service
        doReturn(Optional.empty()).when(service).getById(1L);

        // Execute the get request
        mvc.perform(get("/api/v1/ships/1"))
                //validate the response code and content type
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Post /api/v1/ships - Success")
    public void testCreateShip() throws Exception {
        //setup our mock service
        Ship postShip = getShip("101", "Teknaf", "Saintmartin", "9:30am", false);
        Ship mockShip = getShip("101", "Teknaf", "Saintmartin", "9:30am", false);
        mockShip.setId(1L);
        mockShip.setVersion(1);
        doReturn(mockShip).when(service).save( any());

        // Execute the get request
        mvc.perform(post("/api/v1/ships")
                //validate the response code and content type
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postShip)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                //validate the header
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(postShip.getName())))
                .andExpect(jsonPath("$.version", is(1)))
                .andExpect(jsonPath("$.shipNumber", is("101")));
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

    static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
