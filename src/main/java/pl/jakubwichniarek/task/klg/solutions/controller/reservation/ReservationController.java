package pl.jakubwichniarek.task.klg.solutions.controller.reservation;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.service.ReservationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

  private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
  private final ReservationService reservationService;

  @PostMapping("/")
  public ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationDTO reservationDTO) {
    logger.info("/reservation/, model: {}", reservationDTO);
    ReservationDTO dto = reservationService.addNewReservation(reservationDTO);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/id/{id}")
  public ResponseEntity<ReservationDTO> update(@PathVariable(name = "id") Long id,
                                               @Valid @RequestBody ReservationDTO reservationDTO) {
    logger.info("/reservation/id/{}, model: {}", id, reservationDTO);
    ReservationDTO dto = reservationService.updateReservation(id, reservationDTO);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/tenant_name/{tenant_name}")
  public ResponseEntity<List<Reservation>> findAllByTenantName(@PathVariable(name = "tenant_name") String tenantName) {
    logger.info("/reservation/tenant_name/{}", tenantName);
    List<Reservation> allByTenantName = reservationService.findAllByTenantName(tenantName);
    if (!allByTenantName.isEmpty())
      return ResponseEntity.ok(allByTenantName);
    else
      return ResponseEntity.notFound().build();
  }

  @GetMapping("/object_for_rent_id/{object_for_rent_id}")
  public ResponseEntity<List<Reservation>> findAllByObjectForRentId(@PathVariable(name = "object_for_rent_id") Long objectForRentId) {
    logger.info("/reservation/object_for_rent_id/{}", objectForRentId);
    List<Reservation> allByObjectForRentId = reservationService.findAllByObjectForRentId(objectForRentId);
    if (!allByObjectForRentId.isEmpty())
      return ResponseEntity.ok(allByObjectForRentId);
    else
      return ResponseEntity.notFound().build();
  }
}
