package com.mock.dubbo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mock.dubbo.domain.User;
import com.mock.dubbo.service.IUserService;
import com.mock.dubbo.service.TestArgsService;

/**
 * 
 * @author duanzongxiang1
 *
 */
@SpringBootApplication
@ImportResource({ "classpath:dubbo.xml" })
@RestController
public class Application {
    private static final Logger logger = Logger.getLogger(Application.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private TestArgsService testArgsService;

    @RequestMapping("/getUserById/{id}")
    @ResponseBody
    public User getUserById(@PathVariable(value = "id", required = true) Integer id) {
	User user = userService.getUserById(id);
	return user;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(@RequestBody User user) {
	user = userService.getUserInfo(user);
	// user = userService.getUserById(user.getId());
	return user;
    }

    @RequestMapping("/testArgs")
    @ResponseBody
    public String testArgs(@RequestBody String args) {
	String str = testArgsService.testArgs(args);
	return str;
    }

    public static void main(String[] args) throws InterruptedException {
	SpringApplication.run(Application.class, args);
	logger.info("启动...... ");
    }
}