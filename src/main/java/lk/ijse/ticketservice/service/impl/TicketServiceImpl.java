package lk.ijse.ticketservice.service.impl;

import lk.ijse.ticketservice.dto.TicketRequestDTO;
import lk.ijse.ticketservice.dto.TicketResponseDTO;
import lk.ijse.ticketservice.entity.Ticket;
import lk.ijse.ticketservice.repository.TicketRepository;
import lk.ijse.ticketservice.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public TicketResponseDTO createTicket(TicketRequestDTO request) {

        Ticket ticket = new Ticket();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setUserId(request.getUserId());

        Ticket savedTicket = ticketRepository.save(ticket);

        return mapToResponse(savedTicket);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TicketResponseDTO> findById(Long id) {

        return ticketRepository.findById(id)
                .map(this::mapToResponse);
    }


    @Override
    @Transactional(readOnly = true)
    public List<TicketResponseDTO> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<TicketResponseDTO> searchTickets(String keyword) {

        return ticketRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword,
                        keyword
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public TicketResponseDTO updateTicket(
            Long id,
            TicketRequestDTO request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Ticket not found with id: " + id
                        )
                );

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setUserId(request.getUserId());

        Ticket updatedTicket = ticketRepository.save(ticket);

        return mapToResponse(updatedTicket);
    }


    @Override
    public void deleteTicket(Long id) {

        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException(
                    "Ticket not found with id: " + id
            );
        }

        ticketRepository.deleteById(id);
    }


    private TicketResponseDTO mapToResponse(Ticket ticket) {

        return TicketResponseDTO.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .build();
    }
}