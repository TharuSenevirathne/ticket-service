package lk.ijse.ticketservice.service;

import lk.ijse.ticketservice.entity.Ticket;
import lk.ijse.ticketservice.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public Ticket updateStatus(Long id, String status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }
}