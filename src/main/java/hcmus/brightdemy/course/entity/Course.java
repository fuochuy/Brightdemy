package hcmus.brightdemy.course.entity;

import hcmus.brightdemy.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends BaseEntity {
    private static final long serialVersionUID = 4766770617680553069L;

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "status")
    private int status;

    @Column(name = "open_time")
    private LocalDateTime openTime;

    @Column(name = "training_time")
    private LocalDateTime trainingTime;

    @Column(name="language")
    private String language;

    @Column(name="framework")
    private String framework;

    @Column(name="position")
    private String position;

}
