package extrenilizedrules.tests;

import com.soham.javacompiler.functions.OwnFunctionExexutorContext;
import com.soham.libs.externalizedrules.poc.PredicateRuleExecutor;
import com.soham.libs.externalizedrules.poc.RuleExecutor;
import com.soham.libs.externalizedrules.poc.models.RuleExecutionConfig;

import extrenilizedrules.tests.mocks.model.DTO_Customer;
import extrenilizedrules.tests.mocks.repo.Repo_Customer;

public class TestPredicates {

	public static void main(String[] args) throws Exception {
		
		  RuleExecutor<Object, Boolean> ruleExector=new PredicateRuleExecutor();
		 RuleExecutionConfig  recfg= ruleExector.initRuleConfig(); // 
		  Object obj=ruleExector.execute("my_rule1",new DTO_Customer("138", "Rohit", 1340, 35, "M", "Business")); 
		   System.out.println(obj);
		  Repo_Customer.checkoutPriceCalculationWithDiscount(OwnFunctionExexutorContext.functionToPredicate( recfg.getRuleFunction("my_rule2")));
		 	
		
	}

}
