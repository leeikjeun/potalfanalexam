package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adaeng on 2017. 6. 9..
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    public User findByUserId(String userId);


}
