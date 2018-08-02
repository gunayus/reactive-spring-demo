package org.springmeetup.reactivespringdemo.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springmeetup.reactivespringdemo.domain.entity.SubjectComment;

import reactor.core.publisher.Flux;

public interface SubjectCommentRepository extends ReactiveMongoRepository<SubjectComment, String> {

	@Tailable
	public Flux<SubjectComment> findBySubjectIdAndTimestampGreaterThan(final String subjectId, final String timestamp);
	
}
