package lk.ijse.ticketservice.service;

import lk.ijse.ticketservice.dto.TicketRequestDTO;
import lk.ijse.ticketservice.dto.TicketResponseDTO;
import lk.ijse.ticketservice.dto.TicketUpdateDTO;
import lk.ijse.ticketservice.entity.Ticket;

import java.util.List;

public interface TicketService {
    TicketResponseDTO create(TicketRequestDTO request);
    List<TicketResponseDTO> getAll();
    TicketResponseDTO getTicketById(Long id);
    List<TicketResponseDTO> getByUserId(Long userId);
    TicketResponseDTO updateStatus(Long id, Ticket.Status status);
    TicketResponseDTO updateTicket(Long id, TicketUpdateDTO request);
    void deleteTicket(Long id);
}


