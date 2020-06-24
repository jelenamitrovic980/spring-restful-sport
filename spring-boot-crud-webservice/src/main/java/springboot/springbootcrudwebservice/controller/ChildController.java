package springboot.springbootcrudwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.springbootcrudwebservice.exception.ResourceNotFoundException;
import springboot.springbootcrudwebservice.model.Child;
import springboot.springbootcrudwebservice.repository.ChildRepository;

@RestController
@RequestMapping("/api/v1")
public class ChildController {
	@Autowired
	private ChildRepository childRepository;
	
	// create get all children api
	public List<Child> getAllChildren() {
		return childRepository.findAll();
		
	}
	
	// create child
	
	@PostMapping("/children")
	public Child createChild(@Validated @RequestBody Child child) {
		return childRepository.save(child);
		
	}
	// get child by id
	
	@GetMapping("children/{id}")
	public ResponseEntity<Child> getChildById(@PathVariable(value = "id") long childId) throws ResourceNotFoundException {
		Child child = childRepository.findById(childId).orElseThrow(() -> new ResourceNotFoundException("Child not found for this id : " + childId));
		return ResponseEntity.ok().body(child);
		
	}
	// update child
	
	@PutMapping("/children/{id}")
	public ResponseEntity<Child> updateChild(@PathVariable(value = "id") long childId
			, @RequestBody Child childDetails) throws ResourceNotFoundException {
		Child child = childRepository.findById(childId)
				.orElseThrow(() -> new ResourceNotFoundException("Child not found for this id : " + childId));
		child.setFirst_name(childDetails.getFirst_name());
		child.setLast_name(childDetails.getLast_name());
		child.setEmail(childDetails.getEmail());
		childRepository.save(child);
		return ResponseEntity.ok().body(child);
		
	}
	// delete child by id
	
	@DeleteMapping("/children/{id}")
	public ResponseEntity<?> deleteChild(@PathVariable(value = "id") long childId) throws ResourceNotFoundException {
		childRepository.findById(childId)
				.orElseThrow(() -> new ResourceNotFoundException("Child not found for this id : " + childId));
		childRepository.deleteById(childId);
		return ResponseEntity.ok().build();
		
		
		
	}

}


