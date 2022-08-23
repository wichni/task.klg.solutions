package pl.jakubwichniarek.task.klg.solutions.controller.reservation;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubwichniarek.task.klg.solutions.exception.ResourceNotFoundException;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.service.ReservationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class ReservationController {

  private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
  private final ReservationService reservationService;

  @PostMapping("/reservation")
  public ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationDTO reservationDTO) {
    logger.info("/task/reservation, model: {}", reservationDTO);
    ReservationDTO dto = reservationService.addNewReservation(reservationDTO);
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/reservation/id/{id}")
  public ResponseEntity<ReservationDTO> update(@PathVariable(name = "id") Long id,
                                               @Valid @RequestBody ReservationDTO reservationDTO) {
    logger.info("/task/reservation/id/{}, model: {}", id, reservationDTO);
    ReservationDTO dto = reservationService.updateReservation(id, reservationDTO);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/reservation/tenant_id/{tenant_id}")
  public ResponseEntity<List<Reservation>> findAllByTenantId(@PathVariable(name = "tenant_id") Long tenantId) {
    logger.info("/task/reservation/tenant_id/{}", tenantId);
    List<Reservation> allByTenantId = reservationService.findAllByTenantId(tenantId);
    if (!allByTenantId.isEmpty())
      return ResponseEntity.ok(allByTenantId);
    else
      throw new ResourceNotFoundException(String.format("No resources were found for the given id: %d", tenantId));
  }

  @GetMapping("/reservation/object_for_rent_id/{object_for_rent_id}")
  public ResponseEntity<List<Reservation>> findAllByObjectForRentId(@PathVariable(name = "object_for_rent_id") Long objectForRentId) {
    logger.info("/task/reservation/object_for_rent_id/{}", objectForRentId);
    List<Reservation> allByObjectForRentId = reservationService.findAllByObjectForRentId(objectForRentId);
    if (!allByObjectForRentId.isEmpty())
      return ResponseEntity.ok(allByObjectForRentId);
    else
      throw new ResourceNotFoundException(String.format("No resources were found for the given id: %d", objectForRentId));
  }
}
