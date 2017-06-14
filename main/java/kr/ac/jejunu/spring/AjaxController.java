package kr.ac.jejunu.spring;

import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Catalog;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.BlogRepository;
import kr.ac.jejunu.repository.CatalogRepository;
import kr.ac.jejunu.repository.CommentRepository;
import kr.ac.jejunu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adaeng on 2017. 6. 14..
 */
@Controller
public class AjaxController {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="/index/blogajax", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, List<Blog>> blogChange(@RequestBody Map<String, Object> params){
        Map<String, List<Blog>> resultMap = new HashMap<String,List<Blog>>();
        List<Blog> blogs = null;
        Integer id = Integer.parseInt((String) params.get("catalogId"));
        Catalog catalog = catalogRepository.findOne(id);
        blogs = blogRepository.findByCatalog(catalog);

        resultMap.put("blogs", blogs);
        return resultMap;
    }

    @RequestMapping(value="/detail/commentajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentAjax(@RequestBody Map<String, Object> params){
        Map<String, Object> resultMap = new HashMap<String,Object>();

        Integer userId = Integer.parseInt((String) params.get("user_id"));
        Integer blogId = Integer.parseInt((String) params.get("blog_id"));
        User user = userRepository.findOne(userId);
        Blog blog = blogRepository.findOne(blogId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBlog(blog);
        comment.setContent((String) params.get("content"));
        comment.setDateComment(new Date());
        commentRepository.save(comment);


        resultMap.put("date", new Date());

        return resultMap;
    }

    @RequestMapping(value="/index/signup", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signUp(@RequestBody Map<String, Object> params){
        Map<String, Object> resultMap = new HashMap<String,Object>();
        User user = new User();

        user.setUserId((String) params.get("signUp"));
        user.setPassword((String) params.get("signPass"));
        user.setName((String) params.get("name"));
        userRepository.save(user);
        resultMap.put("messge","회원가입이 완료되었습니다");

        return resultMap;
    }

    @RequestMapping(value="/index/checksignupajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkSignUpAjax(@RequestBody Map<String, Object> params){
        Map<String, Object> resultMap = new HashMap<String,Object>();
        String userId = (String) params.get("signUpId");
        User user = userRepository.findByUserId(userId);
        String message;
        String flag;
        try {
            user.getName();
            message = "중복된 아이디 입니다.";
            flag = "false";
            resultMap.put("message", message);
            resultMap.put("flag",flag);
        }catch (NullPointerException e){
            message = "사용 가능한 아이디 입니다.";
            flag = "true";
            resultMap.put("message", message);
            resultMap.put("flag",flag);
        }
        return resultMap;
    }

    @RequestMapping(value="/index/catalogajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> catalogAjax(@RequestBody Map<String, Object> params){
        Map<String, Object> resultMap = new HashMap<String,Object>();

        Catalog catalog = new Catalog();
        catalog.setName((String) params.get("catalogTitle"));
        Catalog newCatalog = catalogRepository.save(catalog);

        resultMap.put("catalogMsg","등록이 완료되었습니다.");
        resultMap.put("catalogId", newCatalog.getId());

        return resultMap;
    }
}
