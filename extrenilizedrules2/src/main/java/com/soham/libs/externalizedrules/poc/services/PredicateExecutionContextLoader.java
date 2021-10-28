package com.soham.libs.externalizedrules.poc.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.soham.javacompiler.functions.models.DTO_FunctionDetails;
import com.soham.libs.externalizedrules.poc.exceptions.RuleNotInitialzedException;
import com.soham.libs.externalizedrules.poc.models.RuleExecutionConfig;

/**
 * @author ssengup1
 * Date: Sep 13, 2021
 * com.soham.libs.externalizedrules.poc.services
 * 
 * This is an implementation of {@link RuleExecutionContextLoader}
 * This was used for a PoC. 
 * 
 */
public class PredicateExecutionContextLoader implements RuleExecutionContextLoader {

	private static final String _DELIMITERS = "<BR>";
	private static final String _EQUALS_TO_DELIMITER = ":::";
	
	private List<DTO_FunctionDetails> functionDetails=new ArrayList<DTO_FunctionDetails>();
	
	/**
	 *Custom loading/parsing technique
	 */
	@Override
	public RuleExecutionConfig load(InputStream inputStream) throws RuleNotInitialzedException {
		
		StringBuffer sb=new StringBuffer();
		try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream))){
			String line;
			while(null!=(line=bufferedReader.readLine())) {
				sb.append(line);
			}
			String strRulebaseProps=sb.toString();
			String[] pairs=strRulebaseProps.split(_DELIMITERS);
			// System.out.println("PredicateExecutionContextLoader.load()"+strRulebaseProps+"\t"+pairs.length);
			if(null==pairs||pairs.length==0) {
				throw new RuleNotInitialzedException("Error while initializing rules.Did you forget to use the delimiter="+_DELIMITERS);
			}
			
			for(int i=0;i<pairs.length;i++) {
				try {
					String strEntry=pairs[i];
					if(strEntry.isEmpty()) {
						throw new RuleNotInitialzedException("Empty predicate not allowed at entryNumber#"+i);
					}
					String[] subPairs=strEntry.split(_EQUALS_TO_DELIMITER);
					if(null==subPairs||subPairs.length!=3) {
						throw new RuleNotInitialzedException("Error while initializing rules.Did you forget to use the delimiter="+_EQUALS_TO_DELIMITER);
					}
					String key=subPairs[0].trim();//name of the function
					String strClassT=subPairs[1].trim(); // <T>
					
					String lamdaExpressionCode=subPairs[2].trim(); // LamdaCodeSnippet
					String strClassR="Boolean";
					DTO_FunctionDetails dto_FunctionDetails=new DTO_FunctionDetails(key, lamdaExpressionCode, strClassT, strClassR);
					functionDetails.add(dto_FunctionDetails);
				}catch(Exception e) {
					e.printStackTrace();
					System.err.println("Could not initialuze "+pairs[i]);
				}
				
			}
			
			if(functionDetails.size()>0) {
				functionDetails.forEach(System.out::println);
			
				RuleExecutionConfig config= new RuleExecutionConfig();
				config.setPredicates(functionDetails);
				
				return config;
				
			}else {
				throw new RuleNotInitialzedException("No predicate rules found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuleNotInitialzedException("Error while initializing rules",e);
		}
	}

	
}
