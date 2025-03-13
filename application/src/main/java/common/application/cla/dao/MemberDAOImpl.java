package common.application.cla.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.cla.dto.MemberVO;
import common.application.job.dto.CompanyVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SqlSession session;

    @Override
    public void deleteAllAuthorityById(String mid) throws SQLException {
        session.delete("Member-Mapper.deleteAllAuthorityById", mid);
    }

    @Override
    public void deleteMember(String mid) throws SQLException {
        session.delete("Member-Mapper.deleteMember", mid);
    }

    @Override
    public void insertAuthorities(String mid, String authority) throws SQLException {
        Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("mid",mid);
		paramMap.put("authority",authority);     
        session.insert("Member-Mapper.insertAuthorities", paramMap);
    }

    @Override
    public void insertMember(MemberVO member) throws SQLException {
        session.insert("Member-Mapper.insertMember", member);
    }

    @Override
    public List<String> selectAuthoritiesByMid(String mid) throws SQLException {
        return session.selectList("Member-Mapper.selectAuthoritiesByMid", mid);
    }

    @Override
    public MemberVO selectMemberByMid(String mid) throws SQLException {
        return session.selectOne("Member-Mapper.selectMemberByMid", mid);
    }

    @Override
    public List<MemberVO> selectMemberList() throws SQLException {
        return session.selectList("Member-Mapper.selectMemberList");
    }

    @Override
    public void updateMember(MemberVO member) throws SQLException {
        session.update("Member-Mapper.updateMember", member);
    }
    
    
    @Override
    public void deleteCompany(CompanyVO company) throws SQLException {
        session.delete("deleteCompany", company);
    }

    @Override
    public void insertCompany(CompanyVO company) throws SQLException {
        session.insert("insertCompany", company);
    }

    @Override
    public String selectAuthorityByCno(String cno) throws SQLException {
        return session.selectOne("selectAuthoritiesByCno", cno);
    }

    @Override
    public CompanyVO selectCompanyByCno(String cno) throws SQLException {
        return session.selectOne("selectCompanyByCno", cno);
    }

    @Override
    public List<CompanyVO> selectCompanyList() throws SQLException {
        return session.selectList("selectCompanyList");
    }

    @Override
    public void updateCompany(CompanyVO company) throws SQLException {
        session.update("updateCompany", company);
    }
}
