package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Catalog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adaeng on 2017. 6. 9..
 */
public interface BlogRepository extends CrudRepository<Blog,Integer> {
    public List<Blog> findByCatalog(Catalog catalog);
}
