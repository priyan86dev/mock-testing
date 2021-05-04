package lk.test.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lk.test.data.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

}
