package kr.ac.jejunu.repository;

/**
 * Created by adaeng on 2017. 6. 8..
 */
import kr.ac.jejunu.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {}
