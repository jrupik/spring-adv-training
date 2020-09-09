package pl.training.shop.nosql.cassandra;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    @DaoFactory()
    ReservationDao reservationDao();

}
