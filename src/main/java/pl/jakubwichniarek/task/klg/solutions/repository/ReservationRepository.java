package pl.jakubwichniarek.task.klg.solutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  @Query("SELECT re " +
          "FROM Reservation re " +
          " JOIN re.tenant te " +
          "WHERE te.name = ?1")
  List<Reservation> findAllByTenantName(String tenantName);

  @Query("SELECT re " +
          "FROM Reservation re " +
          " JOIN re.lessor le " +
          " JOIN le.objectForRentList ob " +
          "WHERE ob.id = ?1")
  List<Reservation> findAllByObjectForRentId(Long objectForRentId);

  @Query("SELECT CASE WHEN (COUNT(re) <> 0) THEN FALSE ELSE TRUE END " +
          "FROM Reservation re " +
          " JOIN re.lessor le " +
          " JOIN le.objectForRentList ob " +
          "WHERE ob.id = ?3 " +
          "   AND (((?1 BETWEEN re.dateFrom AND re.dateTo) OR (?2 BETWEEN re.dateFrom AND re.dateTo)) " +
          "   OR ((re.dateFrom BETWEEN ?1 AND ?2) OR (re.dateTo BETWEEN ?1 AND ?2)))")
  boolean checkReservationsByDateFromAndDateToAndByObjectForRentId(LocalDate dateFrom, LocalDate dateTo, Long objectForRentId);
}
