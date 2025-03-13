package common.application.commons.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import common.application.cla.dto.MemberVO;
import common.application.commons.security.model.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class MemberLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        Member user = (Member)authentication.getDetails();
        MemberVO loginUser = user.getMemberVO();

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);
        session.setMaxInactiveInterval(6*60);

        super.onAuthenticationSuccess(request, response, authentication);
    }
    
}
