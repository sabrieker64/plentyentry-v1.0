package at.commodussolutions.plentyentry.ordermanagement.event.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class EventInitializer implements InitializeDatabase {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventBuilder eventBuilder;

    @Override
    public String initializeName() {
        return "Event Group";
    }

    @Override
    public String initializerName() {
        return "Event Initializer";
    }

    @Override
    public boolean shouldDataBeInitialized() {
        return eventRepository.count() == 0;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {
        eventBuilder.buildEvent();
    }
}
