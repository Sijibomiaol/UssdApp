package com.sijibomiaol.skaetAss.context.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@MappedSuperclass
@Setter
@Getter
public class BaseCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_created")
    private LocalDateTime created;
    @Column(name="date_updated")
    private LocalDateTime updated;
    @PrePersist
    protected void onCreate(){
        this.created = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
}
