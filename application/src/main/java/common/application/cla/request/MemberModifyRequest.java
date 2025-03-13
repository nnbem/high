package common.application.cla.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import common.application.cla.dto.MemberVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberModifyRequest {
    private String mid;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private LocalDate birth;
    private String email;
    private String address;
    private String phone;
    private String pwd;
    private String name;
    private MultipartFile picture;


    public MemberVO toMemberVO(){
        MemberVO member = new MemberVO();
        member.setMid(mid);
        member.setPwd(pwd);
        member.setName(name);
        member.setEmail(email);
        member.setAddress(address);
        member.setBirth(birth);
        member.setPhone(phone);
        return member;
    }
}
