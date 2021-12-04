package at.commodussolutions.plentyentry.ordermanagement.event.mapper;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {


    @Mapping(target = "ticket", source = "event.ticket")
    EventDTO mapToDTO(Event event);
}
