package org.springmeetup.reactivespringdemo.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springmeetup.reactivespringdemo.domain.entity.Subject;

public interface SubjectRepository extends ReactiveMongoRepository<Subject, String> {

}
