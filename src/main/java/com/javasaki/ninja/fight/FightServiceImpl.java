package com.javasaki.ninja.fight;

import com.javasaki.ninja.ninja.NinjaHero;
import com.javasaki.ninja.user.UserNinja;
import com.javasaki.ninja.user.UserNinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FightServiceImpl implements FightService {

  UserNinjaService userNinjaService;

  @Autowired
  public FightServiceImpl(UserNinjaService userNinjaService) {
    this.userNinjaService = userNinjaService;
  }

  @Override
  public void fighters(String challenger, String challenged) throws Exception {
    if (!userNinjaService.isUserExists(challenger) || !userNinjaService.isUserExists(challenged)) {
      throw new Exception("user not exists");
    }
    UserNinja challengerNinja = userNinjaService.findUserNinjaByUsername(challenger);
    UserNinja challengedNinja = userNinjaService.findUserNinjaByUsername(challenged);
    if (!challengerNinja.getChallengers().contains(challenged) || !challengedNinja.getChallengers().contains(challenger)) {
      throw new Exception("they didn't challenged each other");
    }
    somethingFight(challengedNinja.getNinjaHero(), challengerNinja.getNinjaHero());
  }

  private void somethingFight(NinjaHero fighter1, NinjaHero fighter2) {

  }
}
