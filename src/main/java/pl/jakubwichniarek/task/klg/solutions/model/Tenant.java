package pl.jakubwichniarek.task.klg.solutions.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenant")
public class Tenant extends BaseEntity {
  private String name;
}
