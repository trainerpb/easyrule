package com.soham.libs.externalizedrules.poc.models;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import com.soham.javacompiler.functions.OwnFunctionExexutorContext;
import com.soham.javacompiler.functions.models.DTO_FunctionDetails;
import com.soham.libs.externalizedrules.poc.exceptions.RuleExecutionException;
import com.soham.libs.externalizedrules.poc.exceptions.RuleNotFoundException;
import com.soham.libs.externalizedrules.poc.exceptions.RuleNotInitialzedException;


/**
 * @author Soham Sengupta
 * Date: Sep 13, 2021
 * com.soham.libs.externalizedrules.poc.models
 * This is the configuration that must be initialized beforehand.
 *  
 */
public class RuleExecutionConfig {
   
	/**
	 * clsRuleConatiner : Class<?>
	 * The {@link Class} that is synthesized with rule bases
	 * This is crated, compile and loaded dynamically 
	 *
	 */
	private Class<?> clsRuleConatiner;
	
	
	/**
	 * functionDetailsList : List<DTO_FunctionDetails>
	 * This is {@link List} of DTO_FunctionDetails which are loaded
	 *  
	 *  
	 *
	 */
	private List<DTO_FunctionDetails> functionDetailsList;
	
	
	/**
	 * @param predicateList
	 * @throws Exception
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * void
	 * 
	 */
	public void  setPredicates(List<DTO_FunctionDetails> predicateList) throws Exception {
		loadRuleClass(predicateList);
		this.functionDetailsList=predicateList;
		
	}
	/**
	 * @param functionDetailsList
	 * @throws RuleNotInitialzedException
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * void
	 * This method synthesizes, compiles and loads the {@link Class} dynamically with all the rule bases 
	 * fetched from rule-repository E.g. file system, database, {@link URL} etc.
	 */
	private void loadRuleClass(List<DTO_FunctionDetails> functionDetailsList) throws RuleNotInitialzedException {
		try {
			// System.out.println("RuleExecutionConfig.loadRuleClass() Trying to set class\t"+functionDetailsList.size());
			
			this.clsRuleConatiner=OwnFunctionExexutorContext.synthesizeMultiplesFunctionClassAndLoad(generateRandomPkgName(), generateRandomClassName(), functionDetailsList.toArray(new DTO_FunctionDetails[functionDetailsList.size()]));
			// System.out.println("RuleExecutionConfig.loadRuleClass() Rules loaded ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuleNotInitialzedException(e.getMessage());
		}
	}
	/**
	 * @return random package name for synthetic {@link Class}
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * String
	 * Generates random package name for synthetic {@link Class} 
	 * @see - {@link RuleExecutionConfig#loadRuleClass(List)}
	 */
	private String generateRandomPkgName() {
		// TODO Auto-generated method stub
		return "com.soham.generated.inmemory.rules.functions";
	}
	/**
	 * @return random  name for synthetic {@link Class} 
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * String
	 * Generates random  name for synthetic {@link Class} 
	 * @see - {@link RuleExecutionConfig#loadRuleClass(List)}
	 */
	private String generateRandomClassName() {
		// TODO Auto-generated method stub
		return "FunctionalRules_"+UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @return List<DTO_FunctionDetails> 
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * List<DTO_FunctionDetails>
	 * 
	 */
	public List<DTO_FunctionDetails> getPredicates (){
		return functionDetailsList;
	}

	
	/**
	 * @return true if the {@link Class} was successfully synthesized.
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * boolean
	 * 
	 */
	public boolean isInititlized() {
		return  Objects.nonNull(this.clsRuleConatiner);
	}
	
	/**
	 * @param strRuleName
	 * @return an  {@link Optional <java.util.Function>} representation given <b>ruleName</b>. If none found, an empty {@link Optional}
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * Optional<DTO_FunctionDetails>
	 * TODO
	 */
	public Optional<DTO_FunctionDetails> getRule(String strRuleName) {
		return this.functionDetailsList.stream().filter(fd->fd.getName().equalsIgnoreCase(strRuleName)).findFirst();
	}

	
	/**
	 * @param <T>
	 * @param <R>
	 * @param ruleName
	 * @return a {@link Function} representation given <b>ruleName</b> if found. Else throws  {@link RuleNotFoundException}
	 * @throws RuleExecutionException
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * Function<T,R>
	 * 
	 */
	public <T,R> Function<T, R> getRuleFunction(String ruleName) throws RuleExecutionException {
		if(false==getRule(ruleName).isPresent()) {
			throw new RuleNotFoundException("Cannot find rule::"+ruleName);
		}
		try {
			return OwnFunctionExexutorContext.loadFunction(clsRuleConatiner, ruleName);
			
		}catch(Throwable e) {
			e.printStackTrace();
			throw new RuleExecutionException(e);
		}
		
	}
	
	
	
	/**
	 * @param <T>
	 * @param <R>
	 * @param ruleName
	 * @param inObj
	 * @return R- result from the function. 
	 * @throws RuleExecutionException
	 * Sep 13, 2021
	 * RuleExecutionConfig.java
	 * R
	 * TODO
	 */
	public <T,R> R executeRule(String ruleName,T inObj) throws RuleExecutionException {
		return (R) getRuleFunction(ruleName).apply(inObj);
	}
	
	
	
	
}
