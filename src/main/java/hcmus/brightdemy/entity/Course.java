package hcmus.brightdemy.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name ="course_id")
    private long id;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name = "owner_id")
    private int owner_id;

    @Column(name="status")
    private int status;

    @Column(name="open_time")
    private Timestamp openTime;

    @Column(name="training_time")
    private Timestamp trainingTime;

    @Column(name ="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name ="updated_at")
    @UpdateTimestamp
    private Date updatedAt;



}
