package com.challenge.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class SubmissionId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "challenge_id", nullable = false)
  private Challenge challenge;
}
