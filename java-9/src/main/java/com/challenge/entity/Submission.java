package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "submission")
@EntityListeners(AuditingEntityListener.class)
public class Submission {

  @EmbeddedId
  private SubmissionId submissionId;

  @NotNull
  @Column(nullable = false)
  private Float score;

  @NotNull
  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private Timestamp createdAt;
}
