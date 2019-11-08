package com.javasaki.ninja.fight;

import com.javasaki.ninja.dto.FightResponseDTO;

public interface FightService {

  FightResponseDTO fighters(String challenger, String challenged) throws Exception;
}
