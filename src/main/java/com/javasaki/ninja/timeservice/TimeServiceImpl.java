package com.javasaki.ninja.timeservice;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class TimeServiceImpl implements TimeService {

  @Override
  public boolean expiredOrNot(Long finishedAt) {
    return java.time.Instant.now().getEpochSecond() - finishedAt > 0;
  }

  @Override
  public Long minutesBetweenVerificationTokenTimestamps(Timestamp expiryDate) {
    Calendar calendar = Calendar.getInstance();
    Timestamp startDate = new Timestamp((calendar.getTime().getTime()));
    return expiryDate.getTime() - startDate.getTime();
  }
}
