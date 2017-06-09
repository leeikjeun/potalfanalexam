package kr.ac.jejunu;

import org.springframework.stereotype.Component;

/**
 * Created by adaeng on 2017. 6. 8..
 */
@Component
public class HelloImpl implements Hello{
    @Override
    public String sayHello() {
        return "Hello!!!";
    }
}
