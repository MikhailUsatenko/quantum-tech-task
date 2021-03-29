package com.usatenko.demo.controller;

import com.usatenko.demo.controller.api.ControllerAPI;
import com.usatenko.demo.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerAPI.FLIGHT_CONTROLLER)
@RequiredArgsConstructor
public class FlightController {

  private final TicketService ticketService;

  @GetMapping(
      value = ControllerAPI.FLIGHT_CONTROLLER_CHECK_TICKET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> checkTicket(@PathVariable String id) {
    var response = ticketService.isTicketFree(id);
    return ResponseEntity.ok(response);
  }
}
