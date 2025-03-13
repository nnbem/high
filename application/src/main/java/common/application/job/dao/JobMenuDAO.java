package common.application.job.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.JobMenuVO;

public interface JobMenuDAO {

    List<JobMenuVO> selectMainJobMenu() throws SQLException; 
    
	// 서브메뉴
	List<JobMenuVO> selectSubJobMenu(String jCode) throws SQLException;

	// 메뉴정보
	JobMenuVO selectJobMenuByJcode(String jCode) throws SQLException;

	JobMenuVO selectJobMenuByJname(String jName) throws SQLException;

}
