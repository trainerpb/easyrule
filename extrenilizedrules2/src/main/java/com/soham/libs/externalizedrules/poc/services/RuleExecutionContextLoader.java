package com.soham.libs.externalizedrules.poc.services;

import java.io.InputStream;
import java.net.URL;

import com.soham.libs.externalizedrules.poc.exceptions.RuleNotInitialzedException;
import com.soham.libs.externalizedrules.poc.models.RuleExecutionConfig;


/**
 * @author Soham Sengupta
 * Date: Sep 13, 2021
 * com.soham.libs.externalizedrules.poc.services
 * This is an abstraction on how rule is loaded from a source. 
 * It must be noted that it deal with how rules are loaded from persistence/streams into memory, without regard to
 * where they reside. They can reside anywhere from local file system to any stream, database , {@link URL} etc.
 * Consumer application must provide implementation for the same 
 */
public interface RuleExecutionContextLoader {

	/**
	 * @param inputStream
	 * @return
	 * @throws RuleNotInitialzedException
	 * Sep 13, 2021
	 * RuleExecutionContextLoader.java
	 * RuleExecutionConfig
	 * TODO
	 */
	RuleExecutionConfig load(InputStream inputStream) throws RuleNotInitialzedException;
	
}
