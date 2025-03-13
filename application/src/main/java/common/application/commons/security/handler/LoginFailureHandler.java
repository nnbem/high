package common.application.commons.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        String msg = exception.getMessage();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println("<script>");
        out.println("alert('"+msg+".');");
        out.println("history.go(-1)");
        out.println("</script>");
        
        out.close();
    }
    
}
