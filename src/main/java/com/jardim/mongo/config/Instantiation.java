package com.jardim.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jardim.mongo.domain.Post;
import com.jardim.mongo.domain.User;
import com.jardim.mongo.dto.AuthorDTO;
import com.jardim.mongo.dto.CommentDTO;
import com.jardim.mongo.repositories.PostRepository;
import com.jardim.mongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2020"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2020"), "Bom dia", "Acordei putasso", new AuthorDTO(maria));
		
		CommentDTO c1 =  new CommentDTO("boa viagem man", sdf.parse("21/03/2020"), new AuthorDTO(alex));
		CommentDTO c2 =  new CommentDTO("tenha um otimo dia", sdf.parse("23/03/2020"), new AuthorDTO(alex));
		CommentDTO c3 =  new CommentDTO("aproveita", sdf.parse("22/03/2020"), new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(c1, c3));
		post2.getComments().addAll(Arrays.asList(c2));
		
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
		
	}

}
