package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User {

  @OneToMany
  private List<Candidate> candidates;

  @OneToMany
  private List<Submission> submissions;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Size(max = 100)
  @Column(name = "full_name", nullable = false, length = 100)
  private String fullName;

  @NotNull
  @Size(max = 100)
  @Column(nullable = false, length = 100)
  @Email
  private String email;

  @NotNull
  @Size(max = 50)
  @Column(name = "nickname", nullable = false, length = 50)
  private String nickName;

  @NotNull
  @Size(max = 255)
  @Column(nullable = false)
  private String password;

  @NotNull
  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;
}
