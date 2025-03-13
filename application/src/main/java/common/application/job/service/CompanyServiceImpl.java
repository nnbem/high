package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.MemberDAO;
import common.application.job.dto.CompanyVO;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private MemberDAO memberDAO;

    @Override
    public CompanyVO company(String cno) throws SQLException {
        return memberDAO.selectCompanyByCno(cno);
    }

    @Override
    public String companyAuthority(String cno) throws SQLException {
        return memberDAO.selectAuthorityByCno(cno);
    }

    @Override
    public List<CompanyVO> companyList() throws SQLException {
        return memberDAO.selectCompanyList();
    }

    @Override
    public void modifyCompany(CompanyVO company) throws SQLException {
        memberDAO.updateCompany(company);
    }

    @Override
    public void registCompany(CompanyVO company) throws SQLException {
        company.setAuthority("ROLE_COMPANY");
        company.setEnabled(1);
        memberDAO.insertCompany(company);
    }

    @Override
    public void removeCompany(CompanyVO company) throws SQLException {
        memberDAO.deleteCompany(company);
    }

}
