package pl.jakubwichniarek.task.klg.solutions.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity {
  @Column(name = "date_from")
  private LocalDate dateFrom;
  @Column(name = "date_to")
  private LocalDate dateTo;
  @OneToOne
  @JoinColumn(name = "lessor_id")
  private Lessor lessor;
  @OneToOne
  @JoinColumn(name = "tenant_id")
  private Tenant tenant;
  private BigDecimal cost;
}
