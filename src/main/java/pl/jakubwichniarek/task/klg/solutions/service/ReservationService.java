package pl.jakubwichniarek.task.klg.solutions.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubwichniarek.task.klg.solutions.converter.ReservationConverter;
import pl.jakubwichniarek.task.klg.solutions.exception.BadRequestException;
import pl.jakubwichniarek.task.klg.solutions.exception.ResourceNotFoundException;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.repository.LessorRepository;
import pl.jakubwichniarek.task.klg.solutions.repository.ReservationRepository;
import pl.jakubwichniarek.task.klg.solutions.repository.TenantRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final ReservationConverter reservationConverter;
  private final LessorRepository lessorRepository;
  private final TenantRepository tenantRepository;

  public ReservationDTO addNewReservation(ReservationDTO dto) {
    Reservation byDateFromAndDateTo = reservationRepository.findByDateFromAndDateToAndByObjectForRentId(dto.getDateFrom(), dto.getDateTo(), dto.getObjectForRentId());
    if (byDateFromAndDateTo == null) {
      Reservation reservation = reservationConverter.convertToEntity(dto);
      Reservation save = reservationRepository.save(reservation);
      return reservationConverter.convertToDTO(save);
    }
    throw new BadRequestException(String.format("There is already a reservation between the given dates: %s - %s", dto.getDateFrom(), dto.getDateTo()));
  }

  public ReservationDTO updateReservation(Long id, ReservationDTO dto) {
    Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Reservation with id: %d, not exists", id)));

    reservation.setDateFrom(dto.getDateFrom());
    reservation.setDateTo(dto.getDateTo());
    reservation.setLessor(lessorRepository.findLessorByObjectForRentId(dto.getObjectForRentId()));
    reservation.setTenant(tenantRepository.findById(dto.getTenantId()).get());

    Reservation save = reservationRepository.save(reservation);
    return reservationConverter.convertToDTO(save);
  }

  public List<Reservation> findAllByTenantId(Long tenantId) {
    return reservationRepository.findAllByTenantId(tenantId);
  }

  public List<Reservation> findAllByObjectForRentId(Long objectForRentId) {
    return reservationRepository.findAllByObjectForRentId(objectForRentId);
  }
}
