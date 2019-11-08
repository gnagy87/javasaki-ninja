package com.javasaki.ninja.fight;

import com.javasaki.ninja.dto.NinjaFightDTO;

import java.util.List;

public interface FightService {

  List<NinjaFightDTO> fighters(String challenger, String challenged) throws Exception;
}
