package pl.jakubwichniarek.task.klg.solutions.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.repository.LessorRepository;
import pl.jakubwichniarek.task.klg.solutions.repository.TenantRepository;

@Component
@AllArgsConstructor
public class ReservationConverter extends Converter<Reservation, ReservationDTO> {

  private final LessorRepository lessorRepository;
  private final TenantRepository tenantRepository;

  @Override
  public Reservation convertToEntity(ReservationDTO dto) {
    Reservation reservation = new Reservation();
    reservation.setDateFrom(dto.getDateFrom());
    reservation.setDateTo(dto.getDateTo());
    reservation.setLessor(lessorRepository.findLessorByObjectForRentId(dto.getObjectForRentId()));
    reservation.setTenant(tenantRepository.findById(dto.getTenantId()).get());
    return reservation;
  }

  @Override
  public ReservationDTO convertToDTO(Reservation reservation) {
    ReservationDTO dto = new ReservationDTO();
    dto.setDateFrom(reservation.getDateFrom());
    dto.setDateTo(reservation.getDateTo());
    dto.setObjectForRentId(reservation.getLessor().getObjectForRent().getId());
    dto.setTenantId(reservation.getTenant().getId());
    return dto;
  }
}
