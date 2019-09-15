package com.unololtd.nazmul.bus.services;

import com.unololtd.nazmul.bus.models.Ship;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ShipService {
    /*Returns Ship with the specified id
     * @param id     ID for the product to retrieve.
     * @return       The request Ship if found
     * */
    Optional<Ship> getById(Long id);

    /*Return all Ships in the database
     * @return   All ships in the database.
     * */
    List<Ship> getAll();

    /*Return all ships page by page from database
     * @param pageNumber     pageNumber for specified page
     * @return ShipPage      Ship page with specified page number
     * */
    Page<Ship> getAll(int page);

    /*Return Ship page with searching parameter
     * @param query      the content that is searching into database
     * @param page       the shipPage number for pagination
     * @return           ship page that found with searching parameter from our database with
     *                   pagination
     *                   */
    Page<Ship> searchShip(String query, int page);

    /*Save the specified Ship in the database
     * @param ship       the ship entity to save into the database
     * @return           the saved ship
     * */
    Ship save(Ship ship);

    /*Return true if ship with specified id exist into the database
     * @param id     the ship id that was looking for
     * @return       true if specified id exist into the database
     * */
    boolean exists(long id);

    /*Delete the ship with the specified id
     * @param id     the id of the ship to delete
     * @return       true if the operation was successful
     * */
    boolean delete(Long id);

    // ************************ SERVICE ADMIN MODULES ******************
    /*Set of Ship that belong to service admin
     * @return       Set of ship that belongs to Service admin*/
    Set<Ship> getMyShips();
}
