package com.yms.jwt.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //데이터베이스의 테이블과 1:1 매핑되는 객체
@Table(name = "users") // 테이블명을 user로 지정
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @JsonIgnore
  @Id
  @Column(name="user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name="username", length = 50, unique = true)
  private String username;

  @JsonIgnore
  @Column(name="password", length = 100)
  private String password;

  @Column(name="nickname",length = 50)
  private String nickname;

  @JsonIgnore
  @Column(name="activated")
  private boolean activated;

  @ManyToMany
  @JoinTable
  //User객체와 권한 객체의 다대다 관계를 일대다, 다대일 관계의 조인 테이블로 정의했다는 뜻
  (
    name = "user_authority",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
    inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") }
  )
  private Set<Authority> authorities;
}
