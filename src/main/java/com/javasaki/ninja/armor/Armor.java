package com.javasaki.ninja.armor;

import com.javasaki.ninja.ninja.NinjaHero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "armor")
@Getter
@Setter
@NoArgsConstructor
public abstract class Armor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  protected String type;
  protected int price;
  protected int armor;
  protected boolean isUsed;
  protected boolean isOnMarket;

  @ManyToOne
  private NinjaHero ninjaHero;

  public Armor(String type) {
    this.type = type;
    this.isUsed = false;
    this.isOnMarket = false;
  }
}
