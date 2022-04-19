package at.commodussolutions.plentyentry.user.userdata.repository;

import at.commodussolutions.plentyentry.user.userdata.beans.QUser;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Eker
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<User> {

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    default public void customize(
            QuerydslBindings bindings, QUser root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding((Path<?>) root.email);
    }

}
