package ci.ada1.learnspring.repositories;

import ci.ada1.learnspring.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {}
