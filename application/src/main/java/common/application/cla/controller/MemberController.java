package common.application.cla.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import common.application.cla.dto.MemberVO;
import common.application.cla.request.MemberModifyRequest;
import common.application.cla.request.MemberRegistRequest;
import common.application.cla.service.MemberService;
import common.application.commons.exception.NotExistPictureFileException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/class")
public record MemberController(MemberService memberService, @Value("${savePath.member.picture}") String picturePath) {

	@GetMapping("/join")
	public String registForm()throws Exception {
        String url = "/cla/common/join";
        return url;
	}

    @PostMapping(value = "/join/post", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(MemberRegistRequest regRequest, ModelAndView mnv)throws Exception {
		String url = "/cla/common/join_success";
		try {
			MultipartFile multi = regRequest.getPicture();
			String fileName = savePicture(null, multi);

			// DB 저장
			MemberVO member = regRequest.toMemberVO();
			member.setPicture(fileName);

			memberService.registMember(member);

		} catch (NotExistPictureFileException e) {
			url = "/error/400";
			e.printStackTrace();
		} catch (Exception e) {
			url = "/error/500";
			e.printStackTrace();
		}

		mnv.setViewName(url);
		return mnv;
	}


    @GetMapping("/idCheck")
	@ResponseBody
	public String idCheck(String mid) throws Exception {
		String result = "duplicated";
		MemberVO member = memberService.memberByMid(mid);
		if (member == null)
			result = "";

		return result;
	}

	@GetMapping("/mypage")
	public ModelAndView mypage(HttpSession session, ModelAndView mnv) throws Exception{
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		if(member == null){
            mnv.setViewName("redirect:/class/loginForm");
            return mnv;			
		}
		String loginUser = member.getMid();
		System.out.println(loginUser);
		if(loginUser == null){
			mnv.setViewName("redirect:/class/loginForm");
			return mnv;
		}
		mnv.addObject("member", member);
		mnv.setViewName("cla/mypage/info");
		return mnv;
	}

	@GetMapping("/mypage/modify")
	public ModelAndView infoModify(HttpSession session, ModelAndView mnv) throws Exception{
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		if(member == null){
            mnv.setViewName("redirect:/class/loginForm");
            return mnv;			
		}
		String loginUser = member.getMid();
		System.out.println(loginUser);
		if(loginUser == null){
			mnv.setViewName("redirect:/class/loginForm");
			return mnv;
		}
		mnv.addObject("member", member);
		mnv.setViewName("cla/mypage/modify");
		return mnv;
	}

	@PostMapping(value = "/mypage/modify/post", produces = "text/plain;charset=utf-8")
	public ModelAndView infoModifyPost(MemberModifyRequest req, ModelAndView mnv)throws Exception{
		String url = "/cla/common/modify_success";

		MemberVO member = req.toMemberVO();
		MultipartFile multi = req.getPicture();
		String oldPicture = memberService.memberByMid(member.getMid()).getPicture();
		
		//파일 저장 및 삭제
		try {
		   String fileName = savePicture(oldPicture, multi);
		   member.setPicture(fileName);
		}catch (NotExistPictureFileException e) {
		   member.setPicture(oldPicture);
		}catch (Exception e) {
		   url = "/error/500.jsp";
		   e.printStackTrace();
		}
		
		// DB 수정
		memberService.modifyMember(member);
		mnv.setViewName(url);
		return mnv;		
	}


	@GetMapping("/getPicture")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String mid)throws Exception {
		ResponseEntity entity = null;

		MemberVO member = null;
		try {
			 member = memberService.memberByMid(mid);
		}catch(SQLException e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (member == null)
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

		String picture = member.getPicture();
		String imgPath = this.picturePath;

		InputStream in = null;

		try {
			in = new FileInputStream(new File(imgPath, picture));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
			return entity;
			
		}catch(IOException e) {
			System.out.println("Not Founded ::: "+member.getMid()+":"+member.getPicture());
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}


    public String savePicture(String oldPicture, MultipartFile multi)
		throws NotExistPictureFileException, IllegalStateException, IOException {

		if (multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 1)
			throw new NotExistPictureFileException();

		// 저장 파일명
		String fileName = null;

		// 파일저장폴더설정
		String uploadPath = this.picturePath;

		// 파일유무확인, 저장 파일명 결정

		String uuid = UUID.randomUUID().toString().replace("-", "");
		fileName = uuid + "$$" + multi.getOriginalFilename();
		File storeFile = new File(uploadPath, fileName);

		// 파일경로 생성
		storeFile.mkdirs();

		// local HDD 에 저장.
		multi.transferTo(storeFile);

		// 기존파일 삭제
		if (oldPicture != null && !oldPicture.isEmpty()) {
			File oldFile = new File(uploadPath, oldPicture);
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}

		return fileName;
	}

}
