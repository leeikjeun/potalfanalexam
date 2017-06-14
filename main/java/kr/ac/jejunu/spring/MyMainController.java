package kr.ac.jejunu.spring;

import kr.ac.jejunu.model.Blog;
import kr.ac.jejunu.model.Catalog;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.BlogRepository;
import kr.ac.jejunu.repository.CatalogRepository;
import kr.ac.jejunu.repository.CommentRepository;
import kr.ac.jejunu.repository.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String index(Model model) {

        List<Catalog> catalogs = IteratorUtils.toList(catalogRepository.findAll().iterator());
        Catalog catalog = catalogs.get(0);
        List<Blog> blogs = blogRepository.findByCatalog(catalog);
        User user = null;
        model.addAttribute("user", user);
        model.addAttribute("catalogs",catalogs);
        model.addAttribute("blogs", blogs);

        return "index";
    }

    @RequestMapping(value = "/index/login", method = RequestMethod.POST)
    public String logIn(@RequestParam String id, @RequestParam String password, HttpServletResponse res, HttpSession session, Model model) throws IOException {

        System.out.print(id + " " + password);
        User user =userRepository.findByUserId(id);

        try {
            if(user.getPassword().equals(password)){
                session.setAttribute("id", id);
                session.setAttribute("key",user.getId());
                return "redirect:/index";
            }else{
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>alert('비밀번호가 틀렸습니다.'); history.go(-1);</script>");
                out.flush();
                return "redirect:/index";
            }
        }catch (NullPointerException e){
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('아이디가 존재하지 않습니다.'); history.go(-1);</script>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/index";
    }

    @RequestMapping("/index/logout")
    public String logOut(HttpSession session){

        session.invalidate();

        return "redirect:/index";
    }

    @RequestMapping(value = "/blog/details", method = RequestMethod.GET)
    public String blogDetail(@RequestParam String id, Model model){


        Blog blog = blogRepository.findOne(Integer.parseInt(id));
        List<Comment> comments = commentRepository.findByBlog(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("replry",comments);

        return "/content/blog_detail";
    }

    @RequestMapping(value = "/index/catagolyupload", method=RequestMethod.POST)
    public String catagolyUpload(Model model) {

        List<Catalog> catalogs = IteratorUtils.toList(catalogRepository.findAll().iterator());
        Catalog catalog = catalogs.get(0);
        List<Blog> blogs = blogRepository.findByCatalog(catalog);
        User user = null;
        model.addAttribute("user", user);
        model.addAttribute("catalogs",catalogs);
        model.addAttribute("blogs", blogs);

        return "index";
    }

    @RequestMapping(value="/index/blogajax", method=RequestMethod.POST)
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
        Date date = new Date();

        Integer userId = Integer.parseInt((String) params.get("user_id"));
        Integer blogId = Integer.parseInt((String) params.get("blog_id"));
        User user = userRepository.findOne(userId);
        Blog blog = blogRepository.findOne(blogId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBlog(blog);
        comment.setContent((String) params.get("content"));
        comment.setDateComment(date);
        commentRepository.save(comment);

        resultMap.put("date", date);

        return resultMap;
    }




}
