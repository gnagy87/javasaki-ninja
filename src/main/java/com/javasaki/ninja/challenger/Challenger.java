package com.javasaki.ninja.challenger;

import com.javasaki.ninja.user.UserNinja;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Challenger {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String challengedName;
  private int bet;

  @ManyToOne
  private UserNinja userNinja;

  public Challenger(String challengedName, int bet) {
    this.challengedName = challengedName;
    this.bet = bet;
  }
}
