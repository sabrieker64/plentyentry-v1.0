package at.commodussolutions.plentyentry.ordermanagement.ticket.mapper;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "quantity", ignore = true)
    TicketDTO mapToDTO(Ticket entity);

    List<TicketDTO> mapToListDTO(List<Ticket> entity);

    void mapToListEntity(List<TicketDTO> dtoList, @MappingTarget List<Ticket> entity);

    void  mapToEntity(TicketDTO dto, @MappingTarget Ticket entity);
}
