package pl.jakubwichniarek.task.klg.solutions.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessor")
public class Lessor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToOne
  @JoinColumn(name = "object_for_rent_id")
  private ObjectForRent objectForRent;
}
