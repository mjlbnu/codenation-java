package com.challenge.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
public class Company {

  @OneToMany
  private List<Candidate> candidates;

  @Id
  private Integer id;

  @NotNull
  @Size(max = 100)
  @Column(nullable = false, length = 100)
  private String name;

  @NotNull
  @Column(length = 50)
  @Size(max = 50)
  private String slug;

  @NotNull
  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;
}
