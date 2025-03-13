package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.MemberDAO;
import common.application.cla.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public List<String> authoritiesByMid(String mid) throws SQLException {
        return memberDAO.selectAuthoritiesByMid(mid);
    }

    @Override
    public MemberVO memberByMid(String mid) throws SQLException {
        MemberVO member = memberDAO.selectMemberByMid(mid);
        if (member != null) {
            List<String> authorities = memberDAO.selectAuthoritiesByMid(mid);
            member.setAuthorities(authorities);
            System.out.println("[DEBUG] 사용자: " + mid + ", 권한: " + authorities);
        }
        return member;
    }

    @Override
    public List<MemberVO> memberList() throws SQLException {
        return memberDAO.selectMemberList();
    }

    @Override
    public void modifyAuthorities(String mid, List<String> authorities) throws SQLException {
        memberDAO.deleteAllAuthorityById(mid);
		
		if(authorities.size()>0)for(String authority:authorities) {
			memberDAO.insertAuthorities(mid, authority);
		}
    }

    @Override
    public void modifyMember(MemberVO member) throws SQLException {
        memberDAO.updateMember(member);
    }

    @Override
    public void registMember(MemberVO member) throws SQLException {
        String authority = "ROLE_USER";
        member.setEnabled(1);
        memberDAO.insertMember(member);
	    memberDAO.insertAuthorities(member.getMid(), authority);
    }

    @Override
    public void removeMember(String mid) throws SQLException {
        memberDAO.deleteMember(mid);
    }

}
