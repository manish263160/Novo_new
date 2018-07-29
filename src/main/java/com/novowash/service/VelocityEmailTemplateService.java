package com.novowash.service;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * This class would use Velocity Engine to formulate the templates.
 *
 */
@Service
public class VelocityEmailTemplateService {

	Logger logger = Logger.getLogger(VelocityEmailTemplateService.class);
	
	
	@Autowired
	VelocityEngine velocityEngine;

	/**
	 * Method to get the Email template using the model object and template path.
	 * @param model - Map of keys and objects which need to be replaced in template
	 * @param templatePath - relative path of template files.
	 * @return String.
	 */
	public String geContentFromTemplate(Map<String, Object> model, String templatePath) {
		logger.info("VelocityEmailTemplateService : getContentFromTemplate "+templatePath);
		VelocityContext context = new VelocityContext();
		StringWriter stringWriter = new StringWriter();
		context.put("StringUtils", StringUtils.class);
		if (model != null) {
			for(String key : model.keySet()){
				context.put(key, model.get(key));
			}
			try {
				velocityEngine.mergeTemplate(templatePath, "UTF-8", context, stringWriter);
			} catch (Exception e) {
				logger.warn("Exception when getting the Email template ",e);
			}
		}
		String text = stringWriter.toString();
		logger.info("VelocityEmailTemplateService : getContentFromTemplate Email Content ");
		return text;
	}

	@Bean
	public VelocityEngine velocityEngine() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("input.encoding", "UTF-8");
		properties.setProperty("output.encoding", "UTF-8");
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine velocityEngine = new VelocityEngine(properties);
		return velocityEngine;
	}
}
