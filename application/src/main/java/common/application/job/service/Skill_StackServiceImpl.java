package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.job.dao.Skill_StackDAO;
import common.application.job.dto.Skill_StackVO;
import common.application.request.PageMaker;

@Service
public class Skill_StackServiceImpl implements Skill_StackService {

    @Autowired
    private Skill_StackDAO skill_StackDAO;

    @Override
    public List<Skill_StackVO> list(PageMaker pageMaker) throws SQLException {
        List<Skill_StackVO> skill_StackList = skill_StackDAO.selectSkill_StackList(pageMaker);
        int totalCount = skill_StackDAO.selectSkill_StackListCount(pageMaker);
        pageMaker.setTotalCount(totalCount);
        return skill_StackList;
    }
}
