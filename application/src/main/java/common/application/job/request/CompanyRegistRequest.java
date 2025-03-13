package common.application.job.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import common.application.job.dto.CompanyVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRegistRequest {
    private String cno;
    private String pwd;
    private String com_name;
    private String ceo;
    private String phone;
    private String email;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private LocalDate regdate;

	public CompanyVO toComponyVO(){
		CompanyVO company = new CompanyVO();
        company.setCno(cno);
        company.setPwd(pwd);
        company.setCom_name(com_name);
        company.setCeo(ceo);
        company.setPhone(phone);
        company.setEmail(email);
        company.setAddress(address);
        company.setRegdate(regdate);
		return company;
	}    

}
