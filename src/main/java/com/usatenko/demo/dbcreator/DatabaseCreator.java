package com.usatenko.demo.dbcreator;

import com.usatenko.demo.db.path.PathToTxt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class DatabaseCreator {

  private String TicketDBPath = PathToTxt.PATH_TO_TICKET_TXT;

  private String defaultTickets =
      " [\n"
          + "    {\n"
          + "      \"id\": \"13\"\n"
          + "    },\n"
          + "    {\n"
          + "      \"id\": \"37\"\n"
          + "    }\n"
          + "  ]";

  public DatabaseCreator() {}

  @PostConstruct
  public void init() throws IOException {
    log.info("Start INIT DB Creator");
    createDatabases();
  }

  public void createDatabases() throws IOException {
    createTicketDBifNotExist();
  }

  private void createTicketDBifNotExist() throws IOException {
    Path path = Path.of(TicketDBPath);
    var jsonString = Files.readString(path);

    if (jsonString.isEmpty()) {
      byte[] defaultTicketsToBytes = defaultTickets.getBytes();
      Files.write(path, defaultTicketsToBytes);
    }
  }
}
