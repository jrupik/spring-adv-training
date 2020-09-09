package pl.training.shop.nosql.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.PagingIterable;

import java.time.LocalDate;
import java.util.UUID;

public class SimpleCassandraClient {

    public static void main(String[] args) {
        CqlSession cqlSession = CqlSession.builder()
                //.addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .build();

        /*cqlSession.execute(SimpleStatement.builder(
                "insert into hotels.reservations_by_confirmation " +
                        "(confirmation_number, hotel_id, room_number, start_date, end_date, guest_id)" +
                        "values (?, ?, ?, ?, ?, ?)")
                .addPositionalValue("XYZ123")
                .addPositionalValue("h898")
                .addPositionalValue((short) 3)
                .addPositionalValue(LocalDate.now())
                .addPositionalValue(LocalDate.now())
                .addPositionalValue(UUID.randomUUID())
                .build());

        cqlSession.execute(SimpleStatement.builder(
                "insert into hotels.reservations_by_confirmation " +
                        "(confirmation_number, hotel_id, room_number, start_date, end_date, guest_id)" +
                        "values (:cm, :hi, :rm, :st, :ed, :qi)")
                .addNamedValue("cm", "XYZ124")
                .addNamedValue("hi", "h898")
                .addNamedValue("rm", (short) 3)
                .addNamedValue("st", LocalDate.now())
                .addNamedValue("ed", LocalDate.now())
                .addNamedValue("qi", UUID.randomUUID())
                .build());

        PreparedStatement selectReservations = cqlSession.prepare(
                "select * from hotels.reservations_by_confirmation where confirmation_number = :cm");
        BoundStatement select = selectReservations.bind()
                .setString("cm", "XYZ124");*/


        /*Insert insertReservation = insertInto("hotels", "reservations_by_confirmation")
                .value("confirmation_number", literal("XYZ124"))
                .value("hotel_id", literal("h898"))
                .value("room_number", literal((short) 3))
                .value("start_date", literal(LocalDate.now()))
                .value("end_date", literal(LocalDate.now()))
                .value("guest_id", literal(UUID.randomUUID()));

        Select selectReservations = selectFrom("hotels", "reservations_by_confirmation")
                .all()
                .whereColumn("confirmation_number").isEqualTo(literal("XYZ124"));

        ResultSet rows = cqlSession.execute(selectReservations.build());
        for (Row row : rows) {
            System.out.println(row.getString("confirmation_number"));
        }*/

        ReservationMapper reservationMapper = new ReservationMapperBuilder(cqlSession)
                .withDefaultKeyspace("hotels")
                .build();
        ReservationDao reservationDao = reservationMapper.reservationDao();

        ReservationsByConfirmation reservationsByConfirmation = new ReservationsByConfirmation();
        reservationsByConfirmation.setConfirmationNumber("XYZ124");
        reservationsByConfirmation.setHotelId("h898");
        reservationsByConfirmation.setRoomNumber((short) 3);
        reservationsByConfirmation.setStartDate(LocalDate.now());
        reservationsByConfirmation.setEndDate(LocalDate.now());
        reservationsByConfirmation.setGuestId(UUID.randomUUID());

        reservationDao.save(reservationsByConfirmation);
        PagingIterable<ReservationsByConfirmation>  results = reservationDao.findByConfirmationNumber("XYZ124");
        System.out.println(results.all());

        cqlSession.close();
    }

}
