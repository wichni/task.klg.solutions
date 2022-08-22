package pl.jakubwichniarek.task.klg.solutions.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jakubwichniarek.task.klg.solutions.model.Lessor;
import pl.jakubwichniarek.task.klg.solutions.model.ObjectForRent;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.model.Tenant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {

  @Autowired
  private ReservationController reservationController;

  @Test
  public void shouldFindAllReservationByObjectForRentIdEquals1AndReturnOk() {
    //given
    Long objectForRentId = 1L;
    HttpStatus expectedStatus = HttpStatus.OK;
    Reservation reservation = getReservation();

    //when
    ResponseEntity<List<Reservation>> allByObjectForRentId = reservationController.findAllByObjectForRentId(objectForRentId);

    //then
    assertEquals(expectedStatus, allByObjectForRentId.getStatusCode());
    for (int i = 0; i < Objects.requireNonNull(allByObjectForRentId.getBody()).size(); i++) {
      assertEquals(reservation.getId(), allByObjectForRentId.getBody().get(i).getId());
      assertEquals(reservation.getDateFrom(), allByObjectForRentId.getBody().get(i).getDateFrom());
      assertEquals(reservation.getDateTo(), allByObjectForRentId.getBody().get(i).getDateTo());
      assertEquals(reservation.getCost(), allByObjectForRentId.getBody().get(i).getCost());
    }
  }

  private Reservation getReservation() {
    Reservation reservation = new Reservation();
    reservation.setId(2L);
    reservation.setDateFrom(LocalDate.of(2022, 8, 30));
    reservation.setDateTo(LocalDate.of(2022, 9, 30));
    reservation.setLessor(getLessor());
    reservation.setTenant(getTenant());
    reservation.setCost(new BigDecimal("50.00"));
    return reservation;
  }

  private Lessor getLessor() {
    Lessor lessor = new Lessor();
    lessor.setId(5L);
    lessor.setName("Weronika Abc");
    lessor.setObjectForRent(getObjectForRent());
    return lessor;
  }

  private Tenant getTenant() {
    Tenant tenant = new Tenant();
    tenant.setId(2L);
    tenant.setName("Student");
    return tenant;
  }

  private ObjectForRent getObjectForRent() {
    ObjectForRent objectForRent = new ObjectForRent();
    objectForRent.setId(1L);
    objectForRent.setName("Pokój dla studenta");
    objectForRent.setUnitPrice(new BigDecimal("123.90"));
    objectForRent.setSurface(9.1);
    objectForRent.setDescription("Pokój jednoosobowy dla osoby studiującej. W mieszkaniu jest kuchnia, łazienka i balkon.");
    return objectForRent;
  }
}

