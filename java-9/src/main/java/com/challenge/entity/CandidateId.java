package com.challenge.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@EntityListeners(EntityListeners.class)
public class CandidateId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "acceleration_id", nullable = false)
  private Acceleration acceleration;

  @ManyToOne
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;
}
