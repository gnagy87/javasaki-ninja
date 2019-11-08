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
  private long id;
  protected String weaponType;
  protected int damage;
  protected int offense;
  protected int defense;
  protected int price;
  protected boolean isUsed;
  protected boolean isOnMarket;

  @ManyToOne
  private NinjaHero ninjaHero;

  public Weapon(String weaponType) {
    this.weaponType = weaponType;
    this.isUsed = false;
    this.isOnMarket = false;
  }
}
