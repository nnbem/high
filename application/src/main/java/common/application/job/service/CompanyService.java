package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.CompanyVO;

public interface CompanyService {
    List<CompanyVO> companyList()throws SQLException;
    CompanyVO company(String cno)throws SQLException;
    void registCompany(CompanyVO company)throws SQLException;
    void modifyCompany(CompanyVO company)throws SQLException;
    void removeCompany(CompanyVO company)throws SQLException;
    String companyAuthority(String cno)throws SQLException;
}
