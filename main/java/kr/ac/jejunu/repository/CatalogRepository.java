package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.Catalog;
import kr.ac.jejunu.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adaeng on 2017. 6. 9..
 */
public interface CatalogRepository extends CrudRepository<Catalog, Integer> {

}
