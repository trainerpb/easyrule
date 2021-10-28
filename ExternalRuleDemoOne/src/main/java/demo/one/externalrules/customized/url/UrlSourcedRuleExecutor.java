package demo.one.externalrules.customized.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.soham.libs.externalizedrules.poc.RuleExecutor;
import com.soham.libs.externalizedrules.poc.services.PredicateExecutionContextLoader;
import com.soham.libs.externalizedrules.poc.services.RuleExecutionContextLoader;

public class UrlSourcedRuleExecutor extends RuleExecutor<Object, Boolean> {

	@Override
	public InputStream getConfigStream() throws IOException {
		
		URL url=new URL("http://localhost:8080/a/Rules.txt");
	 	HttpURLConnection connection=(HttpURLConnection) url.openConnection();
	 	return connection.getInputStream();
	}

	@Override
	public RuleExecutionContextLoader getExecutionContentLoader() {
		// TODO Auto-generated method stub
		return new PredicateExecutionContextLoader();
	}

}
