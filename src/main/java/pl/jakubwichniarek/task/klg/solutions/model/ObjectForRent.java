package pl.jakubwichniarek.task.klg.solutions.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ObjectForRent extends BaseEntity {
  private String name;
  @Column(name = "unit_price")
  private BigDecimal unitPrice;
  private Double surface;
  private String description;
  @ManyToOne
  @JoinColumn(name = "lessor_id")
  @JsonManagedReference
  private Lessor lessor;
}
