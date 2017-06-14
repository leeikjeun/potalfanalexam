package kr.ac.jejunu.repository;

/**
 * Created by adaeng on 2017. 6. 8..
 */
import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Comment;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    public List<Comment> findByBlog(Blog blog);
}
