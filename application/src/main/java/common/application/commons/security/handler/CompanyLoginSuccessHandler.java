package common.application.commons.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import common.application.commons.security.model.Company;
import common.application.job.dto.CompanyVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CompanyLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        Company user = (Company)authentication.getDetails();
        CompanyVO loninUser = user.getCompanyVO();

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loninUser);
        session.setMaxInactiveInterval(6*60);

        super.onAuthenticationSuccess(request, response, authentication);
    }
    
}
