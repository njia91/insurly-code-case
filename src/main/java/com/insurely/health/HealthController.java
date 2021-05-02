package com.insurely.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/health"})
public class HealthController {

  @GetMapping("")
  public @ResponseBody HealthOKResponse health() {
    return new HealthOKResponse("healthy");
  }
}
