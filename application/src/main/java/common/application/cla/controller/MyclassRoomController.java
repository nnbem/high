package common.application.cla.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.application.cla.dto.ClassHistoryVO;
import common.application.cla.dto.MemberVO;
import common.application.cla.service.ClassHistoryService;
import common.application.request.PageMaker;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/class")
public record MyclassRoomController(ClassHistoryService classHistoryService) {

    @GetMapping("/myclassroom")
    public String myClassroom(HttpSession session, Model model, @ModelAttribute PageMaker pageMaker) throws SQLException {
        MemberVO member = (MemberVO) session.getAttribute("loginUser");
        if (member == null) {
            return "redirect:/class/loginForm";
        }
    
        String mid = member.getMid();
    
        int totalCount = classHistoryService.selectSearchClassHistoryListCount(mid, pageMaker);
        pageMaker.setTotalCount(totalCount);
    
        List<ClassHistoryVO> historyList = classHistoryService.selectSearchClassHistoryList(mid, pageMaker);
    
        model.addAttribute("historyList", historyList);
        model.addAttribute("pageMaker", pageMaker);
        return "cla/myclassroom/list";
    }
    

    @GetMapping("/myclassroom/ing")
    public String myClassroom_ing(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            HttpSession session,
            Model model) {

        MemberVO member = (MemberVO) session.getAttribute("loginUser");
        if (member == null) {
            return "redirect:/class/loginForm";
        }

        String mid = member.getMid();
        System.out.println("로그인한 사용자 ID: " + mid);

        List<ClassHistoryVO> historyList = classHistoryService.getClassHistoryList(mid);

        int totalRecords = historyList.size();
        PageMaker pageMaker = new PageMaker();
        pageMaker.setPage(page);
        pageMaker.setTotalCount(totalRecords);

        model.addAttribute("historyList", historyList);
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("keyword", keyword == null ? "" : keyword);

        return "cla/myclassroom/ing";
    }

}
