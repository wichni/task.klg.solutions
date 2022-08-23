package pl.jakubwichniarek.task.klg.solutions.converter.reservation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jakubwichniarek.task.klg.solutions.converter.Converter;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.Reservation;
import pl.jakubwichniarek.task.klg.solutions.repository.LessorRepository;
import pl.jakubwichniarek.task.klg.solutions.repository.TenantRepository;
import pl.jakubwichniarek.task.klg.solutions.util.CalculateCostByDaysUtil;

@Component
@AllArgsConstructor
public class ReservationConverter extends Converter<Reservation, ReservationDTO> {

  private final LessorRepository lessorRepository;
  private final TenantRepository tenantRepository;
  private final CalculateCostByDaysUtil calculateCostByDaysUtil;

  @Override
  public Reservation convertToEntity(ReservationDTO dto) {
    Reservation reservation = new Reservation();
    reservation.setDateFrom(dto.getDateFrom());
    reservation.setDateTo(dto.getDateTo());
    reservation.setLessor(lessorRepository.findLessorByObjectForRentId(dto.getObjectForRentId()));
    reservation.setTenant(tenantRepository.findById(dto.getTenantId()).get());
    reservation.setCost(calculateCostByDaysUtil.calculateCostMultiplyDays(dto));
    return reservation;
  }

  public ReservationDTO convertToDTO(Reservation reservation, Long objectForRentId) {
    ReservationDTO dto = new ReservationDTO();
    dto.setDateFrom(reservation.getDateFrom());
    dto.setDateTo(reservation.getDateTo());
    dto.setObjectForRentId(objectForRentId);
    dto.setTenantId(reservation.getTenant().getId());
    dto.setCost(reservation.getCost());
    return dto;
  }

  public Reservation convertToEntity(Long tenantId, ReservationDTO dto) {
    Reservation reservation = new Reservation();
    reservation.setDateFrom(dto.getDateFrom());
    reservation.setDateTo(dto.getDateTo());
    reservation.setLessor(lessorRepository.findLessorByObjectForRentId(dto.getObjectForRentId()));
    reservation.setTenant(tenantRepository.findById(tenantId).get());
    reservation.setCost(calculateCostByDaysUtil.calculateCostMultiplyDays(dto));
    return reservation;
  }
}
