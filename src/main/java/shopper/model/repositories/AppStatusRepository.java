package shopper.model.repositories;

import org.springframework.data.repository.CrudRepository;
import shopper.model.StatusEnum;
import shopper.model.entities.AppStatus;

import java.util.Date;
import java.util.List;

/**
 * Crud repository for operations on application
 * Created by ssingh9 on 8/2/15.
 */

public interface AppStatusRepository extends CrudRepository<AppStatus, Long> {

    public List<AppStatus> findByDateBetween(Date d1, Date d2);

    public AppStatus findByShopperId(Long shopperId);

    public List<AppStatus> findByDateBetweenAndStatus(Date d1, Date d2, StatusEnum status);

}
