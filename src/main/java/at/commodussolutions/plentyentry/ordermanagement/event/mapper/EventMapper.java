package at.commodussolutions.plentyentry.ordermanagement.event.mapper;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO mapToDTO(Event event);

    Event mapToEntity(EventDTO eventDTO, @MappingTarget Event event);

    List<EventDTO> mapToListDTO(List<Event> events);
}
