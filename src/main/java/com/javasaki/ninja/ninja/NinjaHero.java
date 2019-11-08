package com.javasaki.ninja.ninja;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.weapon.Weapon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ninja")
@Getter
@Setter
public abstract class NinjaHero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  protected String name;
  protected int hp;
  protected int offence;
  protected int defence;
  protected int speed;
  private int money;
  private boolean isInJail;
  private boolean isTraining;
  private long finishedAt;
  private long dailyBonusTime;

  @OneToOne
  private UserNinja userNinja;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "ninjaHero")
  private List<Weapon> weapons;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "ninjaHero")
  private List<Armor> armors;

  public NinjaHero() {
  }

  public NinjaHero(String name) {
    this.name = name + " son";
    this.isInJail = false;
    this.isTraining = false;
    this.finishedAt = System.currentTimeMillis() / 1000;
    this.dailyBonusTime = System.currentTimeMillis() / 1000;
    this.weapons = new ArrayList<>();
    this.armors = new ArrayList<>();
  }

  public void addWeapon(Weapon weapon) {
    this.weapons.add(weapon);
  }

  public void addArmor(Armor armor) {
    this.armors.add(armor);
  }
}
