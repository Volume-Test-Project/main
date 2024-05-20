package com.volumeTest.volume.common.audit;


import jakarta.persistence.Column;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

public abstract class Auditable {
  @CreatedDate
  @Setter(lombok.AccessLevel.NONE)
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime modifiedAt;

  public Auditable() {
  }
}
