package com.m3kong.infrastructure.persistence.model;

import com.m3kong.blog.domain.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "auth", name = "token")
public class TokenEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String accessToken;

  private String refreshToken;

  private String saltKey;

  private Date expiredTime;

  @Convert(converter = StatusConverter.class)
  private StatusType status;

  private Date createTime;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(columnDefinition = "accountId", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private AccountEntity account;
}
