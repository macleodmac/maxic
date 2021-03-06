package com.maxic.towers.web.controllers;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.maxic.towers.web.model.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8950498780886605265L;
	private final String appUrl;
	private final Locale locale;
	private final User user;

	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
		super(user);
		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public User getUser() {
		return user;
	}
	
	
}
