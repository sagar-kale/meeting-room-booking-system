package com.sagar.meetingroom;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.Test;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@RequestMapping(value = "/test",  method = RequestMethod.GET, produces = "application/json")
	public Test test(){
		Test t = new Test(1, "API IS UP", new Date());
		return t;
	}
	
	@RequestMapping(value = "/testdate",  method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Test> testDate(@RequestBody Test test){
		System.out.println(test);
		System.out.println(test.getD());
		return new ResponseEntity<Test>(test, HttpStatus.OK);
	}

}
