package pl.jakubwichniarek.task.klg.solutions.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakubwichniarek.task.klg.solutions.converter.reservation.ReservationConverter;
import pl.jakubwichniarek.task.klg.solutions.exception.BadRequestException;
import pl.jakubwichniarek.task.klg.solutions.exception.ResourceNotFoundException;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.repository.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final ReservationConverter reservationConverter;

  @Transactional
  public ReservationDTO addNewReservation(ReservationDTO dto) {
    List<Reservation> byDateFromAndDateTo = reservationRepository.findByDateFromAndDateToAndByObjectForRentId(dto.getDateFrom(), dto.getDateTo(), dto.getObjectForRentId());
    if (byDateFromAndDateTo.isEmpty()) {
      Reservation reservation = reservationConverter.convertToEntity(dto);
      Reservation save = reservationRepository.save(reservation);
      return reservationConverter.convertToDTO(save, dto.getObjectForRentId());
    } else
      throw new BadRequestException(String.format("There is already a reservation between the given dates: %s - %s", dto.getDateFrom(), dto.getDateTo()));
  }

  @Transactional
  public ReservationDTO updateReservation(Long id, ReservationDTO dto) {
    Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Reservation with id: %d, not exists", id)));

    Long tenantId = reservation.getTenant().getId();

    reservationRepository.delete(reservation);

    List<Reservation> byDateFromAndDateTo = reservationRepository.findByDateFromAndDateToAndByObjectForRentId(dto.getDateFrom(), dto.getDateTo(), dto.getObjectForRentId());

    if (byDateFromAndDateTo.isEmpty()) {
      Reservation convertReservation = reservationConverter.convertToEntity(tenantId, dto);

      Reservation save = reservationRepository.save(convertReservation);
      return reservationConverter.convertToDTO(save, dto.getObjectForRentId());
    } else
      throw new BadRequestException(String.format("There is already a reservation between the given dates: %s - %s", dto.getDateFrom(), dto.getDateTo()));
  }

  public List<Reservation> findAllByTenantId(Long tenantId) {
    return reservationRepository.findAllByTenantId(tenantId);
  }

  public List<Reservation> findAllByObjectForRentId(Long objectForRentId) {
    return reservationRepository.findAllByObjectForRentId(objectForRentId);
  }
}
