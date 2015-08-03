package shopper.model.repositories;

import org.springframework.data.repository.CrudRepository;
import shopper.model.entities.Shopper;

import java.util.List;

/**
 * Crud repository for shoppers
 * Created by ssingh9 on 8/2/15.
 */
public interface ShopperRepository extends CrudRepository<Shopper, Long> {
    List<Shopper> findByFirstName(String lastName);

    Shopper save(Shopper shopper);
}
