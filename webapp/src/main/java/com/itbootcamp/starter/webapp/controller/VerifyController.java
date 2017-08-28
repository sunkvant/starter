package com.itbootcamp.starter.webapp.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyController {

    @RequestMapping(value = "api/registration/verify",method = RequestMethod.GET)
    public void verify() {

        Sender sender=new Sender("sun.kvant@gmail.com","26a01b95c");

       // sender.send("Subject","Secret key: ecvvrrbrb","sun.kvant@gmail.com","sun.kvant@gmail.com");

        String str=BCrypt.hashpw("eve",BCrypt.gensalt());

        System.out.println(str);

    }





}
