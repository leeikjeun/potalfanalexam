package kr.ac.jejunu.servlet;

/**
 * Created by adaeng on 2017. 6. 8..
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//@Controller("/hello")
public class HelloServlet extends GenericServlet {

    private final static Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    public void destroy() {
        logger.info("******** destory **********");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        logger.info("******** init **********");
        super.init();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("********* service ***********");
        res.getWriter().println("<h1>Hello!!!</h1>");
    }
}
