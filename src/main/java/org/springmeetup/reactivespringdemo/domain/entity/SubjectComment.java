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
package org.springmeetup.reactivespringdemo.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springmeetup.reactivespringdemo.util.TimestampUtils;

@Document(collection = "subjectcomments")
@TypeAlias("subjectcomment")
public class SubjectComment {

    @Id
    private String id;
    private String subjectId;
    private String author;
    private String text;
    private String timestamp;

	public SubjectComment() {
		super();
	}

	public SubjectComment(final String subjectId, final String author, final String text) {
		super();
		this.subjectId = subjectId;
		this.author = author;
		this.text = text;

		this.timestamp = TimestampUtils.computeISO8601Timestamp();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return String.format("Subject{id=%s, subjectId=%s, author=%s}", id, subjectId, author);
	}

}
