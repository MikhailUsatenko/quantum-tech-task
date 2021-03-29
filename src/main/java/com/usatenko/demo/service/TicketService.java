package com.usatenko.demo.service;

import com.usatenko.demo.pojo.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

  private final CacheService cacheService;

  private Map<String, Ticket> cacheMem = new HashMap<>();

  @PostConstruct
  private void initCache() {
    cacheService.synchronizeTicketCache(cacheMem);
  }

  public Boolean isTicketFree(String id) {
    log.info("Got request get ticket by id: {}", id);
    return cacheMem.containsKey(id);
  }
}
