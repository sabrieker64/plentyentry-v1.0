package at.commodussolutions.plentyentry.user.userdata.repository;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Eker
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
