package org.springmeetup.reactivespringdemo.rest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springmeetup.reactivespringdemo.domain.entity.SubjectComment;
import org.springmeetup.reactivespringdemo.domain.repository.SubjectCommentRepository;
import org.springmeetup.reactivespringdemo.util.TimestampUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subject-comment")
public class SubjectCommentRestController {

	private final SubjectCommentRepository subjectCommentRepository;
	
    public SubjectCommentRestController(SubjectCommentRepository subjectCommentRepository) {
		super();
		this.subjectCommentRepository = subjectCommentRepository;
	}

    @GetMapping(value = "/all/{code}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<SubjectComment> findAllCommentsBySubjectCode(@PathVariable("code") String code) {
    	Date date = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
    	
		return subjectCommentRepository.findBySubjectIdAndTimestampGreaterThan(code, TimestampUtils.computeISO8601Timestamp(date));
	}

    @GetMapping(value = "/add-new/{code}/{author}/{comment}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<SubjectComment> addNewComment(
			@PathVariable("code") String code,
			@PathVariable("author") String author,
			@PathVariable("comment") String comment) {
    	SubjectComment subjectComment = new SubjectComment(code, author, comment);
    	
    	return subjectCommentRepository.save(subjectComment);
	}
    
}
