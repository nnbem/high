package common.application.cla.request;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import common.application.cla.dto.MemberVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegistRequest { 
	
	private String mid;  //아이디
	private String pwd; //패스워드
	private String name; //이름
	private String phone; //전화번호
	private String email;  //이메일
	private String address;
	private MultipartFile picture; // 사진파일 경로/파일명
	@DateTimeFormat(pattern = "yyyy-MM-DD")
	private LocalDate birth;
	
	public MemberVO toMemberVO(){
		MemberVO member = new MemberVO();
		member.setMid(mid);
		member.setPwd(pwd);
		member.setName(name);
		member.setEmail(email);
		member.setAddress(address);
		member.setBirth(birth);
		member.setPhone(phone);
		// String phoneTemp="";
		//  for(String p : phone ) {
		// 	 phoneTemp+=p;
		//  }
		//  member.setPhone(phoneTemp);

		return member;
	}

}






