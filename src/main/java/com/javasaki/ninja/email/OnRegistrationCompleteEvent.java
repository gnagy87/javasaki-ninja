package com.javasaki.ninja.email;

import com.javasaki.ninja.user.UserNinja;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

  private static final long serialVersionUID = 1L;
  private String appUrl;
  private UserNinja user;

  public OnRegistrationCompleteEvent(UserNinja user, String appUrl) {
    super(user);
    this.appUrl = appUrl;
    this.user = user;
  }
}

