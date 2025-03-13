package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.MemberVO;

public interface MemberService {
    List<MemberVO> memberList()throws SQLException;
    MemberVO memberByMid(String mid)throws SQLException;
    void registMember(MemberVO member)throws SQLException;
    void modifyMember(MemberVO member)throws SQLException;
    void removeMember(String mid)throws SQLException;
    List<String> authoritiesByMid(String mid)throws SQLException;
    void modifyAuthorities(String mid, List<String> authorities)throws SQLException;
}
