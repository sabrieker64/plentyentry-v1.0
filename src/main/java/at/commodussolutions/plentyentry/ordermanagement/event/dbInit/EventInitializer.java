package at.commodussolutions.plentyentry.ordermanagement.event.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventInitializer implements InitializeDatabase {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public String initializeName() {
        return null;
    }

    @Override
    public String initializerName() {
        return null;
    }

    @Override
    public boolean shouldDataBeInitialized() {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {
        Event event = new Event();
        //Build Data
        eventRepository.save(event);

    }
}
