package hcmus.brightdemy.user.repository;

import hcmus.brightdemy.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByUsernameContainingOrFullNameContaining(String username, String fullName);

    @Query("FROM User user")
    List<User> findAllPaging(Pageable pageable);

    List<User> findByUsernameContainingOrFullNameContaining(String key, String fullName, Pageable pageable);
}
