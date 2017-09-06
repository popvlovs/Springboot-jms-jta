
/**
 * Created by Think on 2017/8/1.
 */
package org.popvlovs.springwebapp.test.web;

import org.popvlovs.springwebapp.test.domain.UserRepository;
import org.popvlovs.springwebapp.test.domain.User;
import org.popvlovs.springwebapp.test.service.DataInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class HelloController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private DataInitService initService;

    @GetMapping(path = "/") // Map ONLY GET Requests
    public @ResponseBody
    String index() {

        return "hello world！";
    }

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/find")
    public @ResponseBody
    List<User> getUsers(@RequestParam(required = false) String name,
                        @RequestParam(required = false) String email) {

        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(email))
            return userRepository.findByNameAndEmail(name, email);

        if (!StringUtils.isEmpty(name))
            return userRepository.findByName(name);

        if (!StringUtils.isEmpty(email))
            return userRepository.findByEml(email);

        return null;
    }

    @GetMapping(path = "/init")
    public @ResponseBody
    List<User> initUsers() {

        initService.InitUsers();

        return null;
    }
}