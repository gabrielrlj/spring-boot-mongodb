package com.jardim.mongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardim.mongo.domain.User;

@RestController
@RequestMapping("/users")
public class UserResource {

	
		@GetMapping
		public ResponseEntity<List<User>> findAll(){
			User maria = new User("1", "Maria", "maria@gmail.com");
			User paulo = new User("2", "Paulo", "paulo@gmail.com");
			
			List<User> list = new ArrayList<>();
			list.addAll(Arrays.asList(maria, paulo));
			return ResponseEntity.ok().body(list);
		}
}
