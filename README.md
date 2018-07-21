# reactive-spring-demo
Reactive spring boot 2.0 demo application

###list all the subjects

curl -i http://localhost:8080/subject/all

###start event stream for fetching all the new comments for a subject
curl -i http://localhost:8080/subject-comment/all/TV-1

###add a new comment for a subject
curl -i http://localhost:8080/subject-comment/add-new/TV-1/foo/hebele-hubele


