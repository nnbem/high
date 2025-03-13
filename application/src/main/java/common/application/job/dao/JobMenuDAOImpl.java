package common.application.job.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.job.dto.JobMenuVO;

@Repository
public class JobMenuDAOImpl implements JobMenuDAO{

    @Autowired
	private SqlSession session;
    
    @Override
    public List<JobMenuVO> selectMainJobMenu() throws SQLException {
        return session.selectList("JobMenu-Mapper.selectMainJobMenu");
    }
    
    @Override
    public List<JobMenuVO> selectSubJobMenu(String jCode) throws SQLException {
        return session.selectList("JobMenu-Mapper.selectSubJobMenu", jCode);
    }
    @Override
    public JobMenuVO selectJobMenuByJcode(String jCode) throws SQLException {
        return session.selectOne("JobMenu-Mapper.selectJobMenuByCcode", jCode);
    }
    @Override
    public JobMenuVO selectJobMenuByJname(String jName) throws SQLException {
        return session.selectOne("JobMenu-Mapper.selectJobMenuByCname", jName);
    }

}
