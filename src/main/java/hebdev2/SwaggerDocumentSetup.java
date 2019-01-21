package hebdev2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerDocumentSetup extends HttpServlet{
	
	/*
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("HEB Dev 2 Challenge API");
		beanConfig.setDescription("Core API");
		
		beanConfig.setSchemes(new String[]{"http"});
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/hebdev2/services");
		beanConfig.setResourcePackage("hebdev2.services");
		
		beanConfig.setScan(true);
	}

}
