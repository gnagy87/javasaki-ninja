package com.javasaki.ninja.dto;

import com.javasaki.ninja.armor.Armor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArmorDTO {

  private long in;
  private String type;
  private int price;
  private int armor;
  private boolean isUsed;
  private boolean isOnMarket;

  public ArmorDTO(Armor armor) {
    this.in = armor.getId();
    this.type = armor.getType();
    this.price = armor.getPrice();
    this.armor = armor.getArmor();
    this.isUsed = armor.isUsed();
    this.isOnMarket = armor.isOnMarket();
  }
}
