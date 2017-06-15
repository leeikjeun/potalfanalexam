package kr.ac.jejunu.repository;

/**
 * Created by adaeng on 2017. 6. 9..
 */



import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Catalog;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import org.apache.commons.collections4.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
        Date date = new Date();
        Catalog catalog = catalogRepository.findOne(1);
        blog.setCatalog(catalog);
        blog.setContent("밥먹쟈 test");
        blog.setTitle("밥먹자 test");
        User user = userRepository.findOne(1);
        blog.setUser(user);
        blog.setDate(date);

        Blog check = blogRepository.save(blog);
        int id = check.getId();
        Blog returnBlog = blogRepository.findOne(id);
        assertThat(returnBlog.getId(),is(id));
        assertThat(returnBlog.getCatalog().getName(),is(catalog.getName()));
        assertThat(returnBlog.getContent(),is("밥먹쟈 test"));
        assertThat(returnBlog.getTitle(),is("밥먹자 test"));
        assertThat(returnBlog.getUser().getName(),is(user.getName()));

    }

    @Test
    public void createComment(){
        User user = userRepository.findOne(1);
        Comment comment = new Comment();

        comment.setContent("test content");
        comment.setUser(user);
        comment.setBlog(blogRepository.findOne(1));
        comment.setDateComment(new Date());

        int id = commentRepository.save(comment).getId();
        Comment check = commentRepository.findOne(id);

        assertThat(check.getId(),is(id));

        assertThat(check.getContent(),is(comment.getContent()));
        assertThat(check.getBlog().getId(),is(comment.getBlog().getId()));
//        assertThat(check.getDateComment(),is(comment.getDateComment())); 시간이 잘들어가나 출력되면 값이 봐꾸네;; 어케 해야하지?
    }

    @Test
    public void checkFindBlogOnCatalogId(){
        Catalog catalog = new Catalog();
        catalog.setId(1);
        catalog.setName("test");
//        Iterator<Catalog> catalogs = catalogRepository.findAll().iterator();



        List<Catalog> catalogs = IteratorUtils.toList(catalogRepository.findAll().iterator());
        List<Blog> blogs = blogRepository.findByCatalog(catalog);
//        List<Comment> comments = commentRepository.findByCommentAll(blogs.get(1));

        for (Catalog test : catalogs) {
            System.out.print(test);

        }

        for(Blog blog : blogs){
            System.out.println(blog.getContent());
        }

//        for(Comment comment : comments){
//            System.out.println(comment.getContent());
//        }

    }

    @Test
    public void checkNullPoint(){
//        User check = userRepository.findOne(1);
        User user = userRepository.findByUserId("aaa");
//        System.out.print(user.getName());
//        assertThat(check.getName(),is(user.getName()));
    }

    @Test
    public void checkUpdateBlog(){
        Blog fisrt = blogRepository.findOne(1);

        fisrt.setTitle("change");

        Blog compare = blogRepository.save(fisrt);
        assertThat(compare.getId(),is(fisrt.getId()));

    }


}
