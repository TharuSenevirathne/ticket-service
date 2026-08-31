package lk.ijse.ticketservice.dto;

import lk.ijse.ticketservice.entity.Ticket;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Ticket.Status status;
}