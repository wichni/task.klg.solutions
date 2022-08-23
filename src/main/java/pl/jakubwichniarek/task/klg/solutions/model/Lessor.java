package pl.jakubwichniarek.task.klg.solutions.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessor")
public class Lessor extends BaseEntity {
  private String name;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "lessor")
  @JsonBackReference
  private List<ObjectForRent> objectForRentList;
}
