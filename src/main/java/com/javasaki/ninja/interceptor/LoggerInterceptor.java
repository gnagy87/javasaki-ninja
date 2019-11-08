package com.javasaki.ninja.interceptor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;

@Component
@Async
@Transactional
public class LoggerInterceptor extends HandlerInterceptorAdapter {

  private static Logger log = Logger.getLogger(LoggerInterceptor.class.getName());
  private FileHandler fileHandler;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    return true;
  }

//  @Override
//  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                         ModelAndView modelAndView) throws Exception {
//    try {
//      fileHandler = new FileHandler("C:/temp/MyLogs.log");
//      log.addHandler(fileHandler);
//      SimpleFormatter formatter = new SimpleFormatter();
//      fileHandler.setFormatter(formatter);
//      if (response.getStatus() < 300) {
//        log.info(request.getRemoteUser() + " " + response.getStatus()
//                + " " + request.getRequestURI() + " " + request.getMethod());
//      } else {
//        log.severe(request.getRemoteUser() + " " + response.getStatus()
//                + " " + request.getRequestURI() + " " + request.getMethod());
//      }
//    } catch (SecurityException | IOException ex) {
//      ex.printStackTrace();
//    }
  //  }
}
