package com.unololtd.nazmul.bus.controller.api.ship;

import com.unololtd.nazmul.bus.models.Ship;
import com.unololtd.nazmul.bus.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/ships")
public class ShipController {
    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Ship>> getAllShips(@RequestParam(value = "query", required = false, defaultValue = "") String query,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return ResponseEntity.ok(this.shipService.searchShip(query, page));
    }

    /*Return the ship with the specified ID
    * @param id     The id of the ship to retrieve
    * @return       the ship object with the specified ID
    * */
    @GetMapping("/{id}")
    public ResponseEntity<?> getShipWithId(@PathVariable Long id){
//        return ResponseEntity.ok(this.shipService.getById(id));
        return this.shipService.getById(id)
                .map(ship -> {
                    try{
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(ship.getVersion()))
                                .location(new URI("/api/v1/ships/"+ship.getId()))
                                .body(ship);
                    } catch (URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /*Create a new Ship
    * @param ship   the ship to create
    * @return       the crated ship
    */
    @PostMapping("")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship){
        //Create the new ship
        Ship newShip = this.shipService.save(ship);

        try{
            //Build a created response
            return ResponseEntity
                    .created(new URI("/api/v1/ships/" + newShip.getId()))
                    .eTag(Integer.toString(newShip.getVersion()))
                    .body(newShip);
        } catch (URISyntaxException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}