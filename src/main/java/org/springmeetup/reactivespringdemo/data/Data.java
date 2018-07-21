/*
 * =============================================================================
 *
 *   Copyright (c) 2017, Daniel Fernandez (http://github.com/danielfernandez)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package org.springmeetup.reactivespringdemo.data;


import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springmeetup.reactivespringdemo.domain.entity.Subject;
import org.springmeetup.reactivespringdemo.domain.entity.SubjectComment;

import reactor.core.publisher.Mono;

/*
 * Class containing the test data that will be used, as well as the logic required to
 * insert it into MongoDB at application startup.
 */
public class Data {

    private static final String LOGGER_INITIALIZE = Data.class.getName() + ".INITIALIZE";


    public static final List<Subject> SUBJECTS =
            Arrays.asList(
                    new Subject("TV-1", "TV-1 Channel"),
                    new Subject("TV-2", "TV-2 Channel"),
                    new Subject("TV-3", "TV-3 Channel"));


    public static void initializeAllData(final ReactiveMongoTemplate mongoTemplate) {
        
        /*
         *  Drop collections, then create them again
         */
        final Mono<Void> initializeCollections =
                mongoTemplate
                        .dropCollection(Subject.class)
                        .then(mongoTemplate.dropCollection(SubjectComment.class))
                        .then(mongoTemplate.createCollection(Subject.class))
                        .then(mongoTemplate.createCollection(SubjectComment.class, CollectionOptions.empty().size(104857600).capped())) // max: 100MBytes
                        .then();

        /*
         * Add some test data to the collections: teams and players will come from the
         * utility Data class, but we will generate matches between teams randomly each
         * time the application starts (for the fun of it)
         */
        final Mono<Void> initializeData =
                mongoTemplate
                        // Insert all the teams into the corresponding collection and log
                        .insert(Data.SUBJECTS, Subject.class)
                        .log(LOGGER_INITIALIZE, Level.FINEST)
                        .then();


        /*
         * Perform the initialization, blocking (that's OK, we are bootstrapping a testing app)
         */
        initializeCollections.then(initializeData).block();
        
    }
    

    private Data() {
        super();
    }

}
