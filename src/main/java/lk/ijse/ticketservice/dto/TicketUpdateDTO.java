package lk.ijse.ticketservice.dto;

import jakarta.validation.constraints.Size;
import lk.ijse.ticketservice.entity.Ticket;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUpdateDTO {

    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    private Ticket.Status status;

    private String priority;

    private Long assignedTo;

    private String category;
}