package at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Profile({"development", "test"})
public class InitializeDatabaseService implements ApplicationRunner {

    @Autowired(required = false)
    private List<InitializeDatabase> initializeDatabases;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Initializion is Running...");
        if (initializeDatabases == null || initializeDatabases.isEmpty()) {
            log.error("No initializers found!");
            return;
        }
        initializeDatabases.forEach(initializeDatabase -> {
            if (initializeDatabase.shouldDataBeInitialized()) {
                log.info("Initialize Started for {}: {}", initializeDatabase.initializerName(),
                        initializeDatabase.initializeName());
                final long startTime = System.currentTimeMillis();
                try {
                    initializeDatabase.initData();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
                final long tookTime = System.currentTimeMillis() - startTime;
                log.info("Initialize is finished for {}: {}", initializeDatabase.initializerName(),
                        initializeDatabase.initializeName());
            } else {
                log.info("Skipped Initialize for {}: {}", initializeDatabase.initializerName(),
                        initializeDatabase.initializeName());
            }
        });
    }
}
