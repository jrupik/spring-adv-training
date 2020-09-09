package pl.training.shop.nosql.cassandra;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.NamingStrategy;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@NamingStrategy(convention = NamingConvention.SNAKE_CASE_INSENSITIVE)
@Entity
@Data
public class ReservationsByConfirmation {

    @PartitionKey
    private String confirmationNumber;
    private String hotelId;
    private short roomNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID guestId;

}
