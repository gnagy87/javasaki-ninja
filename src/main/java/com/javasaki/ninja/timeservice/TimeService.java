package com.javasaki.ninja.timeservice;

import java.sql.Timestamp;

public interface TimeService {

  boolean expiredOrNot(Long finishedAt);

  Long minutesBetweenVerificationTokenTimestamps(Timestamp expiryDate);
}
