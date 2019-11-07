package com.javasaki.ninja.timeservice;

import java.sql.Timestamp;

public interface TimeService {

  long expiredOtNot(Long finishedAt);

  Long minutesBetweenVerificationTokenTimestamps(Timestamp expiryDate);
}
