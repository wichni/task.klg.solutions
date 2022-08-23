package pl.jakubwichniarek.task.klg.solutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakubwichniarek.task.klg.solutions.model.ObjectForRent;

@Repository
public interface ObjectForRentRepository extends JpaRepository<ObjectForRent, Long> {

  @Query("SELECT ob.lessor " +
          "FROM ObjectForRent ob " +
          "WHERE ob.id = ?1")
  ObjectForRent findLessorByObjectForRentId(Long objectForRentId);
}
