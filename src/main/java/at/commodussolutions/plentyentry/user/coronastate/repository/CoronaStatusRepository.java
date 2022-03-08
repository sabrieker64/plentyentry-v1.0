package at.commodussolutions.plentyentry.user.coronastate.repository;

import at.commodussolutions.plentyentry.user.coronastate.beans.CoronaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoronaStatusRepository extends JpaRepository<CoronaStatus, Long> {
}
