package kr.ac.jejunu.spring;

import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Catalog;
import kr.ac.jejunu.repository.BlogRepository;
import kr.ac.jejunu.repository.CatalogRepository;
import kr.ac.jejunu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by adaeng on 2017. 6. 9..
 */
@Controller
public class MyMainController {
    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/index")
    public String hello(Model model) {
        Catalog catalog = new Catalog();
        catalog.setId(1);
        catalog.setName("test");
        List<Blog> blogs = blogRepository.findByCatalog(catalog);

        model.addAttribute("hi", new String[] {"하이!!!"});

        return "index";
    }
}
