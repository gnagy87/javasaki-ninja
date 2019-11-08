package com.javasaki.ninja.dto;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.weapon.Weapon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MarketDTO {

  private List<WeaponDTO> weapons;
  private List<ArmorDTO> armors;

  public MarketDTO(List<Weapon> weapons, List<Armor> armors) {
    this.weapons = weaponListOnMarket(weapons);
    this.armors = armorListOnMarket(armors);
  }

  private List<ArmorDTO> armorListOnMarket(List<Armor> armors) {
    return armors.stream()
            .filter(armor -> armor.isOnMarket())
            .map(armor -> new ArmorDTO(armor))
            .collect(Collectors.toList());
  }

  private List<WeaponDTO> weaponListOnMarket(List<Weapon> weapons) {
    return weapons.stream()
            .filter(weapon -> weapon.isOnMarket())
            .map(weapon -> new WeaponDTO(weapon))
            .collect(Collectors.toList());
  }
}
