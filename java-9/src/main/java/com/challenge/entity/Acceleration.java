package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(EntityListeners.class)
@Table(name = "acceleration")
public class Acceleration {

  @OneToMany
  private List<Candidate> candidates;

  @ManyToOne
  @JoinColumn(name = "challenge_id", nullable = false)
  private Challenge challenge;

  @Id
  private Integer id;

  @NotNull
  @Size(max = 100)
  @Column(nullable = false, length = 100)
  private String name;

  @NotNull
  @Size(max = 50)
  @Column(length = 50)
  private String slug;

  @CreatedDate
  @NotNull
  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;
}
