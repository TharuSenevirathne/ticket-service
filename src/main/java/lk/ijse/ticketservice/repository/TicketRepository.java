package lk.ijse.ticketservice.repository;

import lk.ijse.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String description
    );

    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByStatus(Ticket.Status status);
}