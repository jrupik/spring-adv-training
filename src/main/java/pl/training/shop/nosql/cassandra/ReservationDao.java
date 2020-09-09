package pl.training.shop.nosql.cassandra;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;

@Dao
public interface ReservationDao {

    @Insert
    void save(ReservationsByConfirmation reservationsByConfirmation);

    @Delete
    void delete(ReservationsByConfirmation reservationsByConfirmation);

    @Select//(customWhereClause = "confirmation_number = :confirmation_number")
    PagingIterable<ReservationsByConfirmation> findByConfirmationNumber(/*@CqlName("confirmation_number")*/ String confirmationNumber);

}
