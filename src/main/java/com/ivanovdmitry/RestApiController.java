package com.ivanovdmitry;

import com.ivanovdmitry.model.User;
import com.ivanovdmitry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dmitry on 20.09.2017.
 */

@RestController
public class RestApiController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public Response authorization(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password){
        User user=userService.findByUsername(username);
        if(user==null){
            return new Response("User not found");
        }
        if(user.getPassword().equals(password)){
            return new Response("Success");
        }
        return new Response("Wrong password");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Response registration(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password){
        User user=userService.findByUsername(username);
        if(user==null){
            userService.save(new User(username, password));
            return new Response("Success");
        }else return new Response("User already exist");
    }
}
