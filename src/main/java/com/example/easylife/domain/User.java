package com.example.easylife.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class User extends AbstractEntity implements UserDetails {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @OneToMany
  @JoinColumn(name = "role_id")
  private Set<Role> authorities;

  @Column(name = "account_expired", nullable = false)
  @ColumnDefault("0")
  protected boolean accountExpired;

  @Column(name = "account_locked", nullable = false)
  @ColumnDefault("0")
  protected boolean accountLocked;

  @Column(name = "credentials_expired", nullable = false)
  @ColumnDefault("0")
  protected boolean credentialsExpired;

  @Column(name = "enabled", nullable = false)
  @ColumnDefault("1")
  protected boolean enabled;

  @Override
  public boolean isAccountNonExpired() {
    return !this.accountExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !this.accountLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !this.credentialsExpired;
  }

  private User(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.authorities.add(role);
  }

  public static User newInstance(String username, String password, Role role) {
    return new User(username, password, role);
  }

}

