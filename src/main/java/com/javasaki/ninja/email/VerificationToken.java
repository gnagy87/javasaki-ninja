package com.javasaki.ninja.email;

import com.javasaki.ninja.user.UserNinja;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "email")
public class VerificationToken {

  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;
  @Column(name = "created_date")
  private Timestamp createdDate;
  @Column(name = "expiry_date")
  private Timestamp expiryDate;

  @OneToOne(targetEntity = UserNinja.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private UserNinja user;

  public VerificationToken(final String token, final UserNinja user) {
    super();
    Calendar calendar = Calendar.getInstance();

    this.token = token;
    this.user = user;
    this.createdDate = new Timestamp(calendar.getTime().getTime());
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  public Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Timestamp(cal.getTime().getTime());
  }

}
