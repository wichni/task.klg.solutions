package pl.jakubwichniarek.task.klg.solutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  List<Reservation> findAllByTenantId(Long tenantId);

  @Query("SELECT re " +
          "FROM Reservation re " +
          " JOIN re.lessor le " +
          " JOIN le.objectForRent ob " +
          "WHERE ob.id = ?1")
  List<Reservation> findAllByObjectForRentId(Long objectForRent);

  @Query("SELECT re " +
          "FROM Reservation re " +
          "WHERE re.dateFrom = ?1 " +
          " AND re.dateTo = ?2")
  Reservation findByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo);
}
