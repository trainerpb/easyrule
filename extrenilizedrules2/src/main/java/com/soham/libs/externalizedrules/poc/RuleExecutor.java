package com.soham.libs.externalizedrules.poc;

import java.io.IOException;
import java.io.InputStream;

import com.soham.libs.externalizedrules.poc.exceptions.RuleExecutionException;
import com.soham.libs.externalizedrules.poc.exceptions.RuleNotInitialzedException;
import com.soham.libs.externalizedrules.poc.models.RuleExecutionConfig;
import com.soham.libs.externalizedrules.poc.services.RuleExecutionContextLoader;

/**
 * @author Soham Sengupta
 * 
 * This is the abstract class defining rule execution method.
 * This needs to be sub-classed by consumer application code
 * providing implementation for the two abstract methods
 * 
 */
public abstract class RuleExecutor<T,R> {

	/**
	 * This is the {@link RuleExecutionConfig} object which must be initialized 
	 * before a rule is executed. i.e. before {@linkplain #execute(String, Object)} is called
	 */
	private RuleExecutionConfig executionConfig;
	
	
	/**
	 * @return {@link RuleExecutionConfig} once this method succeeds
	 * @throws RuleExecutionException
	 * @throws RuleNotInitialzedException
	 * @throws IOException
	 */
	public final RuleExecutionConfig initRuleConfig() throws RuleExecutionException,RuleNotInitialzedException, IOException{
		
		RuleExecutionContextLoader ruleExecutionContexttLoader=getExecutionContentLoader();
		InputStream inputStream=getConfigStream();
		this.executionConfig=ruleExecutionContexttLoader.load(inputStream);
		
		
		if(false==executionConfig.isInititlized()) {
			throw new RuleExecutionException(new RuntimeException("Rule not intialized:: "));
		}
		return executionConfig;
		
		
	}
	/**
	 * This method needs to be implemented by consumer code
	 * @return {@link RuleExecutionContextLoader}
	 */
	public abstract RuleExecutionContextLoader getExecutionContentLoader();
	
	/**
	 * This method needs to be implemented by consumer code
	 * @return {@link InputStream}
	 * @throws IOException
	 */
	public abstract InputStream getConfigStream() throws IOException;
	
	/**
	 * This method is not supposed to be overridden. 
	 * @param ruleName
	 * @param inputObj
	 * @return {@link Object}
	 * @throws RuleExecutionException
	 */
	public final Object execute(String ruleName,T inputObj) throws RuleExecutionException {
		return this.executionConfig.executeRule(ruleName, inputObj);
	}
	
	
	
	
}
