package com.maxic.towers.web.processing;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class DoveFileLoader implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	
	public Resource test() {
		Resource resource = resourceLoader.getResource("file:webapp/static/dove.txt");
		return resource;
	}
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	
}
