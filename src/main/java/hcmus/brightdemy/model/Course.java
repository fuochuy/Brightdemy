package hcmus.brightdemy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name ="id")
    private long id;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;




}
