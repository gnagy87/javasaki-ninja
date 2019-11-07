package com.javasaki.ninja.user;

import com.javasaki.ninja.character.NinjaHero;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class UserNinja {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private String email;
  private boolean isEnable;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "userNinja")
  private NinjaHero ninjaHero;

  public UserNinja() {
    this.isEnable = false;
  }

  public UserNinja(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.isEnable = false;
  }
}
