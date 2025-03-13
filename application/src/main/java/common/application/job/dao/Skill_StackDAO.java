package common.application.job.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.Skill_StackVO;
import common.application.request.PageMaker;

public interface Skill_StackDAO {
    List<Skill_StackVO> selectSkill_StackList(PageMaker pageMaker) throws SQLException;
    int selectSkill_StackListCount(PageMaker pageMaker) throws SQLException;
}
