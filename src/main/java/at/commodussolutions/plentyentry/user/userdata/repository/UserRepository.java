package at.commodussolutions.plentyentry.user.userdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import at.commodussolutions.plentyentry.user.userdata.beans.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>{
    
}
