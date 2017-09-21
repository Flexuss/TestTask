package com.ivanovdmitry;

import com.ivanovdmitry.model.User;
import com.ivanovdmitry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        username=username.toLowerCase();
        if(!checkParam(username)){
            return new Response("Wrong username");
        }
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
        username=username.toLowerCase();
        if(!checkParam(username)){
            return new Response("Wrong username");
        }
        User user=userService.findByUsername(username);
        if(user==null){
            if(!checkParam(password)){
                return new Response("Wrong password");
            }
            userService.save(new User(username, password));
            return new Response("Success");
        }else return new Response("User already exist");
    }

    private static boolean checkParam(String param){
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+$");
        Matcher m = p.matcher(param);
        return m.matches();
    }
}
