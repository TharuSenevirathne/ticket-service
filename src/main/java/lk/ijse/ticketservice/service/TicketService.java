package lk.ijse.ticketservice.service;

import lk.ijse.ticketservice.dto.TicketRequestDTO;
import lk.ijse.ticketservice.dto.TicketResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    TicketResponseDTO createTicket(TicketRequestDTO request);

    Optional<TicketResponseDTO> findById(Long id);

    List<TicketResponseDTO> getAllTickets();

    List<TicketResponseDTO> searchTickets(String keyword);

    TicketResponseDTO updateTicket(Long id, TicketRequestDTO request);

    void deleteTicket(Long id);
}