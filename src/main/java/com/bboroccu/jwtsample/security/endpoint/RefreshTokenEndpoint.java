package com.bboroccu.jwtsample.security.endpoint;

import com.bboroccu.jwtsample.common.Constants;
import com.bboroccu.jwtsample.model.entity.User;
import com.bboroccu.jwtsample.security.auth.jwt.extractor.TokenExtractor;
import com.bboroccu.jwtsample.security.auth.jwt.verifier.TokenVerifier;
import com.bboroccu.jwtsample.security.config.JwtSettings;
import com.bboroccu.jwtsample.security.exceptions.InvalidJwtToken;
import com.bboroccu.jwtsample.security.model.UserContext;
import com.bboroccu.jwtsample.security.model.token.JwtToken;
import com.bboroccu.jwtsample.security.model.token.JwtTokenFactory;
import com.bboroccu.jwtsample.security.model.token.RawAccessJwtToken;
import com.bboroccu.jwtsample.security.model.token.RefreshToken;
import com.bboroccu.jwtsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class RefreshTokenEndpoint {
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @RequestMapping(value = "/auth/token", method = RequestMethod.GET)
    public
    @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(Constants.AUTHENTICATION_HEADER_NAME));
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti))
            throw new InvalidJwtToken();
        String subject = refreshToken.getSubject();
        User user = userService.getByUserId(subject).orElseThrow(() -> new UsernameNotFoundException("User not found : " + subject));
        if (user.getRole() == null) throw new InsufficientAuthenticationException("user has no roles assinged");
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().authority()));
        UserContext userContext = UserContext.create(user.getUserId(), authorities);
        return tokenFactory.createAccessJwtToken(userContext);
    }
}
