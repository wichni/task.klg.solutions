package pl.jakubwichniarek.task.klg.solutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakubwichniarek.task.klg.solutions.model.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Long> {

  @Query("SELECT le " +
          "FROM Lessor le " +
          " JOIN le.objectForRentList ob " +
          "WHERE ob.id = ?1")
  Lessor findLessorByObjectForRentId(Long objectForRentId);
}
