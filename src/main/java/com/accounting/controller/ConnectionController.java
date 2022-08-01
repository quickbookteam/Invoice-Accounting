package com.accounting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.accounting.config.OAuth2PlatformClientFactory;
import com.accounting.util.QBOServiceHelper;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;
import com.intuit.oauth2.exception.InvalidRequestException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ConnectionController {

	@Autowired
	private QBOServiceHelper qBOServiceHelper;
	
	@Autowired
	OAuth2PlatformClientFactory factory;

	@Value("${OAuth2AppRedirectUri}")
	private String redirectUri;

	@RequestMapping("/connectToQuickbooks")
	public View connectToQuickbooks() {
		log.info("inside connectToQuickbooks ");
		OAuth2Config oauth2Config = factory.getOAuth2Config();

		String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");

		String csrf = oauth2Config.generateCSRFToken();
//		session.setAttribute("csrfToken", csrf);
		try {
			List<Scope> scopes = new ArrayList<Scope>();
			scopes.add(Scope.All);
			return new RedirectView(oauth2Config.prepareUrl(scopes, redirectUri, csrf), true, true, false);
		} catch (InvalidRequestException e) {
			log.error("Exception calling connectToQuickbooks ", e);
		}
		return null;
	}

	
}
