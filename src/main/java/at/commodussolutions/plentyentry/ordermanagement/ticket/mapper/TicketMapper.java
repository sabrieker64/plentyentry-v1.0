package at.commodussolutions.plentyentry.ordermanagement.ticket.mapper;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.mapstruct.MappingTarget;

public interface TicketMapper {

    TicketDTO mapToDTO(Ticket entity);

    Ticket mapToEntity(TicketDTO dto, @MappingTarget Ticket entity);
}
