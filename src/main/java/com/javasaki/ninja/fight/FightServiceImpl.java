package com.javasaki.ninja.fight;

import com.javasaki.ninja.armor.Armor;
import com.javasaki.ninja.dto.FightResponseDTO;
import com.javasaki.ninja.dto.NinjaFightDTO;
import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.ninja.NinjaHeroRepository;
import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.user.UserNinjaService;
import com.javasaki.ninja.weapon.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FightServiceImpl implements FightService {

  UserNinjaService userNinjaService;
  NinjaHeroRepository ninjaHeroRepository;

  @Autowired
  public FightServiceImpl(UserNinjaService userNinjaService, NinjaHeroRepository ninjaHeroRepository) {
    this.userNinjaService = userNinjaService;
    this.ninjaHeroRepository = ninjaHeroRepository;
  }

  @Override
  public List<NinjaFightDTO> fighters(String challenger, String challenged) throws Exception {
    if (!userNinjaService.isUserExists(challenger) || !userNinjaService.isUserExists(challenged)) {
      throw new Exception("user not exists");
    }
    UserNinja challengerNinja = userNinjaService.findUserNinjaByUsername(challenger);
    UserNinja challengedNinja = userNinjaService.findUserNinjaByUsername(challenged);
    if (!isOnTheList(challengerNinja, challenged) || !isOnTheList(challengedNinja, challenger)) {
      throw new Exception("they didn't challenged each other");
    }
    return somethingFight(challengedNinja.getNinjaHero(), challengerNinja.getNinjaHero());
  }

  private List<NinjaFightDTO> somethingFight(NinjaHero fighter1, NinjaHero fighter2) {
    Weapon fighter1Weapon = getTheWeapon(fighter1);
    Armor fighter1Armor = getTheArmor(fighter1);
    FightResponseDTO responseDTO = new FightResponseDTO();
    List<NinjaFightDTO> resultOfFight = new ArrayList<>();
    Weapon fighter2Weapon = getTheWeapon(fighter2);
    Armor fighter2Armor = getTheArmor(fighter2);

    resultOfFight.add(new NinjaFightDTO(fighter1));
    resultOfFight.add(new NinjaFightDTO(fighter2));

    do {
      if (fighter1.getSpeed() + randomToSix() > fighter2.getSpeed() + randomToSix()) {
        if (fighter1.getOffence() + fighter1Weapon.getOffense() + randomToSix() > fighter2.getDefence() + fighter2Weapon.getDefense()) {
          fighter2.setHp(fighter2.getHp() - (fighter1Weapon.getDamage() + randomToSix()) - fighter2Armor.getArmor());
          resultOfFight.add(new NinjaFightDTO(fighter2));
          if (fighter1.getDefence() + fighter1Weapon.getDefense() < fighter2.getOffence() + fighter2Weapon.getOffense() + randomToSix()) {
            fighter1.setHp(fighter1.getHp() - (fighter2Weapon.getDamage() + randomToSix()) - fighter1Armor.getArmor());
            resultOfFight.add(new NinjaFightDTO(fighter1));
            if (fighter1.getHp() <= 0) {
              return resultOfFight;
            }
          }
        }
      } else {
        if (fighter2.getOffence() + fighter2Weapon.getOffense() + randomToSix() > fighter1.getDefence() + fighter1Weapon.getDefense()) {
          fighter1.setHp(fighter1.getHp() - (fighter2Weapon.getDamage() + randomToSix()) - fighter1Armor.getArmor());
          resultOfFight.add(new NinjaFightDTO(fighter1));
          if (fighter1.getOffence() + fighter1Weapon.getOffense() + randomToSix() > fighter2.getDefence() + fighter2Weapon.getDefense()) {
            fighter2.setHp(fighter2.getHp() - (fighter1Weapon.getDamage() + randomToSix()) - fighter2Armor.getArmor());
            resultOfFight.add(new NinjaFightDTO(fighter2));
          }
        }
      }
    } while (fighter1.getHp() > 0 || fighter2.getHp() > 0);
    ninjaHeroRepository.save(fighter1);
    ninjaHeroRepository.save(fighter2);
    return resultOfFight;
  }

  private boolean isOnTheList(UserNinja user, String challengerName) {
    for (int i = 0; i < user.getChallengers().size(); i++) {
      if (user.getChallengers().get(i).getChallengedName().equals(challengerName)) {
        return true;
      }
    }
    return false;
  }

  private Weapon getTheWeapon(NinjaHero ninja) {
    for (int i = 0; i < ninja.getWeapons().size(); i++) {
      if (ninja.getWeapons().get(i).isUsed()) {
        return ninja.getWeapons().get(i);
      }
    }
    return ninja.getWeapons().get(0);
  }

  private Armor getTheArmor(NinjaHero ninja) {
    for (int i = 0; i < ninja.getArmors().size(); i++) {
      if (ninja.getArmors().get(i).isUsed()) {
        return ninja.getArmors().get(i);
      }
    }
    return null;
  }

  private int randomToSix() {
    return (int) (Math.random() * 6) + 1;
  }
}
