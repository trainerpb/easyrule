package demo.one.externalrules;

import com.soham.javacompiler.functions.OwnFunctionExexutorContext;
import com.soham.libs.externalizedrules.poc.PredicateRuleExecutor;
import com.soham.libs.externalizedrules.poc.RuleExecutor;
import com.soham.libs.externalizedrules.poc.models.RuleExecutionConfig;

import demo.one.externalrules.customized.url.UrlSourcedRuleExecutor;
import demo.one.externalrules.repo.Repo_Customer;

public class DemoOne_MainClass {

	public static void main(String[] args) throws Exception {
		// This can be any custom implementation.
		// Step-1: Create RuleExecutor<T,R> 
		 RuleExecutor<Object, Boolean> ruleExector=new UrlSourcedRuleExecutor();
		 // Step-2: initRuleConfig
		 RuleExecutionConfig  recfg= ruleExector.initRuleConfig(); // 
		  
		 Repo_Customer.checkoutPriceCalculationWithDiscount(
				 OwnFunctionExexutorContext.functionToPredicate( recfg.getRuleFunction("my_rule2")));
		 

	}

}
