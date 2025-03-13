package common.application.job.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.job.dto.Skill_StackVO;
import common.application.request.PageMaker;

@Repository
public class Skill_StackDAOImpl implements Skill_StackDAO {

    @Autowired
    private SqlSession session;
    
    @Override
    public List<Skill_StackVO> selectSkill_StackList(PageMaker pageMaker) throws SQLException {
        
        int startRow = pageMaker.getStartRow();
		int endRow = startRow + pageMaker.getPerPageNum()-1;

        Map<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("startRow", startRow);
		dataParam.put("endRow", endRow);
		dataParam.put("searchType", pageMaker.getSearchType());
		dataParam.put("keyword", pageMaker.getKeyword());

		
		List<Skill_StackVO> skill_StackList = session.selectList("Skill_Stack-Mapper.selectSkill_StackList", dataParam);

		return skill_StackList;
    }
    @Override
    public int selectSkill_StackListCount(PageMaker pageMaker) throws SQLException {
    return session.selectOne("Skill_Stack-Mapper.selectSkill_StackListCount", pageMaker);
}
}
