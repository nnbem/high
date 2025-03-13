package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.job.dao.JobMenuDAO;
import common.application.job.dto.JobMenuVO;

@Service
public class JobMenuServiceImpl implements JobMenuService{

    @Autowired
    private JobMenuDAO jobMenuDAO;
    
    @Override
    public List<JobMenuVO> getMainJobMenuList() throws SQLException {
        return jobMenuDAO.selectMainJobMenu();
    }
    
    @Override
    public List<JobMenuVO> getSubJobMenuList(String jCode) throws SQLException {
        return jobMenuDAO.selectSubJobMenu(jCode);
    }
    @Override
    public JobMenuVO getJobMenuByJname(String jName) throws SQLException {
        return jobMenuDAO.selectJobMenuByJname(jName);
    }
    @Override
    public JobMenuVO getJobMenuByJcode(String jCode) throws SQLException {
        return jobMenuDAO.selectJobMenuByJcode(jCode);
    }

}
