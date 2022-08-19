package pl.jakubwichniarek.task.klg.solutions.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "object_for_rent")
public class ObjectForRent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "unit_price")
  private BigDecimal unitPrice;
  private Double surface;
  private String description;
}
