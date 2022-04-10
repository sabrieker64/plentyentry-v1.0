package at.commodussolutions.plentyentry.ordermanagement.ticket.mapper;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDTO mapToDTO(Ticket entity);

    List<TicketDTO> mapToListDTO(List<Ticket> entity);

    Set<Ticket> mapToListEntity(Set<TicketDTO> dtoList, @MappingTarget Set<Ticket> entity);

    Ticket mapToEntity(TicketDTO dto, @MappingTarget Ticket entity);
}
