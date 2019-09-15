package com.unololtd.nazmul.bus.IntegrationTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.unololtd.nazmul.bus.models.Ship;
import com.unololtd.nazmul.bus.models.ShipFacilities;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShipControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Post /api/v1/ships - Success")
    public void testCreateShip() throws Exception {
        //setup our mock service
        Ship postShip = getShip("101", "Teknaf", "Saintmartin", "9:30am", false);

        // Execute the get request
        mockMvc.perform(post("/api/v1/ships")
                //validate the response code and content type
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postShip)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                //validate the header
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(postShip.getName())))
                .andExpect(jsonPath("$.version", is(1)))
                .andExpect(jsonPath("$.shipNumber", is("101")));
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/v1/ships")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
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
