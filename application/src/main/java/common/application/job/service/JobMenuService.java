package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.JobMenuVO;

public interface JobMenuService {

    List<JobMenuVO> getMainJobMenuList() throws SQLException;

    List<JobMenuVO> getSubJobMenuList(String jCode) throws SQLException;

    JobMenuVO getJobMenuByJcode(String jCode) throws SQLException;

    JobMenuVO getJobMenuByJname(String jName) throws SQLException;
}
