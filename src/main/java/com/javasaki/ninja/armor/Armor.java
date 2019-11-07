package com.javasaki.ninja.armor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Armor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int price;
}
