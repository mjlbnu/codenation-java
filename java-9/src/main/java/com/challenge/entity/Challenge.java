package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(EntityListeners.class)
public class Challenge {

  @OneToMany(mappedBy = "challenge")
  private List<Acceleration> accelerations;

  @OneToMany
  private List<Submission> submissions;

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

  @NotNull
  @CreatedDate
  @Column(name= "created_at", nullable = false)
  private Timestamp createdAt;
}
