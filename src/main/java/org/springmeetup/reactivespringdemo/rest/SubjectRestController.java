package org.springmeetup.reactivespringdemo.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springmeetup.reactivespringdemo.domain.entity.Subject;
import org.springmeetup.reactivespringdemo.domain.repository.SubjectRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subject")
public class SubjectRestController {

	private final SubjectRepository subjectRepository;
	
    public SubjectRestController(SubjectRepository subjectRepository) {
		super();
		this.subjectRepository = subjectRepository;
	}

	@GetMapping(value = "/{code}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<Subject> findTeamById(@PathVariable("code") String code) {
		return subjectRepository.findById(code);
	}
    
    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Subject> findAll() {
		return subjectRepository.findAll();
	}
    
    @GetMapping(value = "/add-new/{code}/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<Subject> addNewSubject(@PathVariable("code") String code, @PathVariable("name") String name) {
		return subjectRepository.save(new Subject(code, name));
	}
}
