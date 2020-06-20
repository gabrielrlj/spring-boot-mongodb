package com.jardim.mongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardim.mongo.domain.User;
import com.jardim.mongo.dto.UserDTO;
import com.jardim.mongo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService service;
	
		@GetMapping
		public ResponseEntity<List<UserDTO>> findAll(){
				
			List<User> list = service.findAll();
			List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
}
