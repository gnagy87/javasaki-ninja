package com.javasaki.ninja.weapon;

import com.javasaki.ninja.ninja.NinjaHero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Weapon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String weaponType;
  int damage;
  int offense;
  int defense;
  int price;

  @ManyToOne
  private NinjaHero ninjaHero;

  public Weapon(String weaponType) {
    this.weaponType = weaponType;
  }
}
