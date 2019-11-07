package com.javasaki.ninja.ninja;

import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.weapon.Weapon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public abstract class NinjaHero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  protected String name;
  protected int hp;
  private int armor;
  protected int offence;
  protected int defence;
  protected int speed;
  private int money;
  private boolean isInJail;
  private boolean isTraining;
  private long finishedAt;

  @OneToOne
  private UserNinja userNinja;

  @OneToMany
  private List<Weapon> weapons;

  public NinjaHero() {
  }

  public NinjaHero(String name) {
    this.name = name + " son";
    this.isInJail = false;
    this.isTraining = false;
    this.finishedAt = java.time.Instant.now().getEpochSecond();
  }
}
