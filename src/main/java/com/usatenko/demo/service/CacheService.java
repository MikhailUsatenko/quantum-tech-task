package com.usatenko.demo.service;

import com.usatenko.demo.db.path.PathToTxt;
import com.usatenko.demo.pojo.Ticket;
import com.usatenko.demo.utils.JacksonUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CacheService {

  public void synchronizeTicketCache(Map<String, Ticket> memCache) {
    Path path = Path.of(PathToTxt.PATH_TO_TICKET_TXT);
    String jsonString = null;
    try {
      jsonString = Files.readString(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<Ticket> listOfTickets = JacksonUtils.getListFromJson(Ticket[].class, jsonString);
    memCache.putAll(
        listOfTickets.stream().collect(Collectors.toMap(Ticket::getId, ticket -> ticket)));
  }
}
