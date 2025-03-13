package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.MemberVO;
import common.application.job.dto.CompanyVO;

public interface MemberDAO {
     List<MemberVO> selectMemberList()throws SQLException;
     MemberVO selectMemberByMid(String mid)throws SQLException;
     void insertMember(MemberVO member)throws SQLException;
     void updateMember(MemberVO member)throws SQLException;
     void deleteMember(String mid)throws SQLException;
     List<String> selectAuthoritiesByMid(String mid)throws SQLException;
     void insertAuthorities(String mid, String authority)throws SQLException;
     void deleteAllAuthorityById(String mid)throws SQLException;

     List<CompanyVO> selectCompanyList()throws SQLException;
     CompanyVO selectCompanyByCno(String cno)throws SQLException;
     void insertCompany(CompanyVO company)throws SQLException;
     void updateCompany(CompanyVO company)throws SQLException;
     void deleteCompany(CompanyVO company)throws SQLException;
     String selectAuthorityByCno(String cno)throws SQLException;     
}
