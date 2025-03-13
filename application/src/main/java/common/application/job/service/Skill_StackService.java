package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.Skill_StackVO;
import common.application.request.PageMaker;

public interface Skill_StackService {
    public List<Skill_StackVO> list(PageMaker pageMaker) throws SQLException;
}
