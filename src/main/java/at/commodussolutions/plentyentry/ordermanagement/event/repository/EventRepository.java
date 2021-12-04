package at.commodussolutions.plentyentry.ordermanagement.event.repository;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
