package pl.jakubwichniarek.task.klg.solutions.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jakubwichniarek.task.klg.solutions.helper.dto.ReservationDTO;
import pl.jakubwichniarek.task.klg.solutions.model.ObjectForRent;
import pl.jakubwichniarek.task.klg.solutions.repository.ObjectForRentRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CalculateCostByDaysUtil {

  private final ObjectForRentRepository objectForRentRepository;

  public BigDecimal calculateCostMultiplyDays(ReservationDTO dto) {
    long duration = ChronoUnit.DAYS.between(dto.getDateFrom(), dto.getDateTo());
    Optional<ObjectForRent> objectForRent = objectForRentRepository.findById(dto.getObjectForRentId());
    return objectForRent.get().getUnitPrice().multiply(BigDecimal.valueOf(duration));
  }
}
