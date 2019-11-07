package com.javasaki.ninja.timeservice;

import java.sql.Timestamp;

public interface TimeService {

  boolean expiredOtNot(Long finishedAt);

  Long minutesBetweenVerificationTokenTimestamps(Timestamp expiryDate);
}
