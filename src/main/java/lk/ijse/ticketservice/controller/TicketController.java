package lk.ijse.ticketservice.controller;

import jakarta.validation.Valid;
import lk.ijse.ticketservice.dto.TicketRequestDTO;
import lk.ijse.ticketservice.dto.TicketResponseDTO;
import lk.ijse.ticketservice.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(
            @Valid @RequestBody TicketRequestDTO request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticketService.createTicket(request));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {

        return ResponseEntity.ok(
                ticketService.getAllTickets()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicket(
            @PathVariable Long id) {

        return ticketService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }


    @GetMapping("/search")
    public ResponseEntity<List<TicketResponseDTO>> searchTickets(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                ticketService.searchTickets(keyword)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketRequestDTO request) {

        return ResponseEntity.ok(
                ticketService.updateTicket(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long id) {

        ticketService.deleteTicket(id);

        return ResponseEntity.noContent().build();
    }
}