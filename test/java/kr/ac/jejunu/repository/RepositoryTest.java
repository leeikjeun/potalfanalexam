package kr.ac.jejunu.repository;

/**
 * Created by adaeng on 2017. 6. 9..
 */



import kr.ac.jejunu.Application;
import kr.ac.jejunu.Hello;
import kr.ac.jejunu.HelloImpl;
import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Catalog;
import kr.ac.jejunu.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void createUser() {
        User user = new User();
        user.setUserId("test");
        user.setPassword("testpass");
        user.setName("testname");
        User check = userRepository.save(user);
        int id = check.getId();
        User returnUser = userRepository.findOne(id);
        assertThat(id, is(returnUser.getId()));

        assertThat("test",is(returnUser.getUserId()));
        assertThat("testpass",is(returnUser.getPassword()));
        assertThat("testname",is(returnUser.getName()));
    }

    @Test
    public void createCatalog(){
        Catalog catalog = new Catalog();
        catalog.setName("test");
        Catalog check = catalogRepository.save(catalog);
        int id = check.getId();
        Catalog returnCatalog = catalogRepository.findOne(id);
        assertThat("test",is(returnCatalog.getName()));
        assertThat(id,is(returnCatalog.getId()));
    }

    @Test
    public void createBlog(){
        Blog blog = new Blog();
        blog.

    }

    @Test
    public void createComment(){

    }



}
