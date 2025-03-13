package common.application.cla.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.application.cla.dto.ClassHistoryVO;
import common.application.cla.dto.MemberVO;
import common.application.cla.service.ClassHistoryService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/class")
public record ClassHistoryController(ClassHistoryService classHistoryService) {

    @GetMapping("/myclassroom/list")
    public String list(HttpSession session, Model model) throws Exception {
        // 세션에서 MemberVO 객체를 가져옴
        MemberVO member = (MemberVO) session.getAttribute("loginUser");
        if (member == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        String mid = member.getMid();

        List<ClassHistoryVO> historyList = classHistoryService.getEnrolledCoursesByMid(mid);
        model.addAttribute("historyList", historyList);

        // 실제 뷰 이름(예: myclassroom/list.jsp 혹은 타임리프 템플릿 이름)에 맞게 수정
        return "myclassroom/list";
    }

    @PostMapping("/myclassroom/update")
    public String update(HttpSession session, @ModelAttribute ClassHistoryVO classHistory) throws Exception {
        // 로그인 상태 확인 (필요 시 업데이트 대상에 대한 본인 소유 확인 등 추가 검증 가능)
        MemberVO member = (MemberVO) session.getAttribute("loginUser");
        if (member == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        // 예: 진행도나 시청시간 업데이트
        classHistoryService.updateHistory(classHistory);

        return "redirect:/myclassroom/list";
    }

    @GetMapping("/myclassroom/remove")
    public String remove(HttpSession session, int clno) throws Exception {
        // 로그인 상태 확인 (필요 시 해당 수강신청이 로그인 사용자 소유인지 확인)
        MemberVO member = (MemberVO) session.getAttribute("loginUser");
        if (member == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        classHistoryService.deleteEnroll(clno);

        return "redirect:/myclassroom/list";
    }

    // 인기 강의 조회 (메인 화면 등에 표시)
    @GetMapping("/popular")
    public void popular(Model model) throws Exception {
        List<ClassHistoryVO> popularLectures = classHistoryService.selectPopularLectures();
        model.addAttribute("popularLectures", popularLectures);
    }

    
}
