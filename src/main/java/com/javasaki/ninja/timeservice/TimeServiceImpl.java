package com.javasaki.ninja.timeservice;

import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {

  @Override
  public long expiredOtNot(Long finishedAt) {
    return java.time.Instant.now().getEpochSecond() - finishedAt;
  }
}
