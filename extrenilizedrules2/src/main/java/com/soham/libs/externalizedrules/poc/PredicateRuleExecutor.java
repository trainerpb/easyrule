package com.soham.libs.externalizedrules.poc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.soham.libs.externalizedrules.poc.services.PredicateExecutionContextLoader;
import com.soham.libs.externalizedrules.poc.services.RuleExecutionContextLoader;


/**
 * @author Soham Sengupta
 * Date: Sep 13, 2021
 * com.soham.libs.externalizedrules.poc
 * This is an implementation of {@link RuleExecutor}, fetching rules from local file system
 * and using a specific parsing logic while loading the rules  
 */
public class PredicateRuleExecutor extends RuleExecutor<Object,Boolean> {

	/**
	 * Implementation - parsing logic 
	 */
	@Override
	public RuleExecutionContextLoader getExecutionContentLoader() {
		// TODO Auto-generated method stub
		return new PredicateExecutionContextLoader();
	}

	/**
	 *Implementation- load from local file system 
	 */
	@Override
	public InputStream getConfigStream() throws IOException {
		// TODO Auto-generated method stub
		return new FileInputStream(new File("D:\\boka\\Rules.txt"));
	}

	

	



}
