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

@Document(collection = "subjects")
@TypeAlias("subject")
public class Subject {

	@Id
	private String code;

	private String name;

	private String timestamp;

	public Subject() {
		super();
	}

	public Subject(final String code, final String name) {
		super();
		this.code = code;
		this.name = name;

		this.timestamp = TimestampUtils.computeISO8601Timestamp();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(final String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return String.format("Subject{code=%s, name=%s}", code, name);
	}

}
