package com.m3kong.infrastructure.persistence.model;

import com.m3kong.domain.model.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "auth", name = "key_token")
public class KeyTokenEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private UUID keyId;

  private String publicKey;

  private String refreshToken;

  private Date expiredTime;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  private Date createTime;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(columnDefinition = "accountId", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private AccountEntity account;
}
