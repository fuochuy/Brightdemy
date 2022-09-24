package hcmus.brightdemy.repository;

import hcmus.brightdemy.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User  findByEmail(String email);
}
