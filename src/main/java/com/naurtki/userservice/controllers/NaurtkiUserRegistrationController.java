package com.naurtki.userservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naurtki.userservice.exception.NaurtkiUserNotFoundException;
import com.naurtki.userservice.models.NaurtkiUser;
import com.naurtki.userservice.repo.NaurtkiUserRepository;

@RestController
@RequestMapping("/naurtkiuserapi")
public class NaurtkiUserRegistrationController {
	
	@Autowired
	NaurtkiUserRepository userRepository;
	
	// Get All Users
	@GetMapping("/users")
	public List<NaurtkiUser> getAllNotes() {
	    return userRepository.findAll();
	}
	
	@PostMapping("/adduser")
	public NaurtkiUser createNote(@Valid @RequestBody NaurtkiUser note) {
	    return userRepository.save(note);
	}
	
	@GetMapping("/user/{id}")
	public NaurtkiUser getNoteById(@PathVariable(value = "id") Long userId) {
	    return userRepository.findById(userId)
	            .orElseThrow(() -> new NaurtkiUserNotFoundException("NaurtkiUser", "id", userId));
	}
	
	@PutMapping("/update/{id}")
	public NaurtkiUser updateNote(@PathVariable(value = "id") Long noteId,
	                                        @Valid @RequestBody NaurtkiUser noteDetails) {

		NaurtkiUser note = userRepository.findById(noteId)
	            .orElseThrow(() -> new NaurtkiUserNotFoundException("NaurtkiUser", "id", noteId));

	    note.setFirstName(noteDetails.getFirstName());
	    note.setLastName(noteDetails.getLastName());

	    NaurtkiUser updatedNote = userRepository.save(note);
	    return updatedNote;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		NaurtkiUser note = userRepository.findById(noteId)
	            .orElseThrow(() -> new NaurtkiUserNotFoundException("NaurtkiUser", "id", noteId));

		userRepository.delete(note);

	    return ResponseEntity.ok().build();
	}
	
	
}
