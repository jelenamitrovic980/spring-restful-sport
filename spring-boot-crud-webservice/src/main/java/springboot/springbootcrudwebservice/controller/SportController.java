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
import springboot.springbootcrudwebservice.model.Sport;
import springboot.springbootcrudwebservice.repository.SportRepository;

@RestController
@RequestMapping("/api/v1")
public class SportController {
	
	@Autowired
	private SportRepository sportRepository;
	
	// create get all sports api
	
	@GetMapping("/sports")
	public List<Sport> getAllSports() {
		return sportRepository.findAll();
		
	}
	
	// create sport
	
	@PostMapping(".sports")
	public Sport createSpotr(@Validated @RequestBody Sport sport) {
		return sportRepository.save(sport);
		
		
	}
	// get sport by id
	
	@GetMapping("sports/{id}")
	public ResponseEntity<Sport> getSportById(@PathVariable(value = "id") long sportId) throws ResourceNotFoundException {
		Sport sport = sportRepository.findById(sportId)
				.orElseThrow(() -> new ResourceNotFoundException("Sport not found for this id: " + sportId));
		return ResponseEntity.ok().body(sport);
		
	}
	// update sport
	
	@PutMapping("/sports/{id}")
	public ResponseEntity<Sport> updateSport(@PathVariable(value = "id") long sportId 
			, @RequestBody Sport sportDetails)  throws ResourceNotFoundException {
		Sport sport = sportRepository.findById(sportId)
				.orElseThrow(() -> new ResourceNotFoundException("Sport not found for this id: " + sportId));
		sport.setName(sportDetails.getName());
		sportRepository.save(sport);
		return ResponseEntity.ok().body(sport);
		
	}
	// delete sport by id
	
	@DeleteMapping("/sports/{id}")
	public ResponseEntity<?> deleteSport(@PathVariable(value = "id") long sportId) throws ResourceNotFoundException {
		sportRepository.findById(sportId)
				.orElseThrow(() -> new ResourceNotFoundException("Sport not found for this id: " + sportId));
		sportRepository.deleteById(sportId);
		return ResponseEntity.ok().build();
		
		
		
	}

}
