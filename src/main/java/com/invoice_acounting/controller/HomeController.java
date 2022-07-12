package com.invoice_acounting.controller;



import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;
import com.intuit.oauth2.exception.InvalidRequestException;
import com.invoice_acounting.service.OAuth2PlatformClientFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dderose
 *
 */
@Controller
public class HomeController {


    @Value("${OAuth2AppClientId}")
    private String clientId;

    @Value("${OAuth2AppClientSecret}")
    private String clientSecret;

    @Value("${OAuth2AppRedirectUri}")
    private String redirectUri;
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    OAuth2PlatformClientFactory factory;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/connected")
    public String connected() {
        return "connected";
    }

    /**
     * Controller mapping for connectToQuickbooks button
     * @return
     */
    @RequestMapping("/connectToQuickbooks")
    public View connectToQuickbooks(HttpSession session) {
        logger.info("inside connectToQuickbooks ");
        OAuth2Config oauth2Config = factory.getOAuth2Config();

        String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");

        String csrf = oauth2Config.generateCSRFToken();
        session.setAttribute("csrfToken", csrf);
        try {
            List<Scope> scopes = new ArrayList<Scope>();
            scopes.add(Scope.Accounting);
            return new RedirectView(oauth2Config.prepareUrl(scopes, redirectUri, csrf), true, true, false);
        } catch (InvalidRequestException e) {
            logger.error("Exception calling connectToQuickbooks ", e);
        }
        return null;
    }

}
