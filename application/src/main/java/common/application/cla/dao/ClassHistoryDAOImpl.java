package common.application.cla.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.cla.dto.ClassHistoryVO;
import common.application.request.PageMaker;

@Repository
public class ClassHistoryDAOImpl implements ClassHistoryDAO{

    @Autowired
    private SqlSession session;

    @Override
    public List<ClassHistoryVO> selectAllClassHistoryList(PageMaker pageMaker) throws SQLException {
        return session.selectList("ClassHistory-Mapper.selectAllClassHistoryList");
    }
    
    @Override
    public List<ClassHistoryVO> selectSearchClassHistoryList(PageMaker pageMaker) throws SQLException {
        int startRow = pageMaker.getStartRow();
        int endRow = startRow + pageMaker.getPerPageNum() - 1;
        
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        params.put("searchType", pageMaker.getSearchType());
        params.put("keyword", pageMaker.getKeyword());
        
        return session.selectList("ClassHistory-Mapper.selectSearchClassHistoryList", params);
    }
    @Override
    public int selectSearchClassHistoryListCount(PageMaker pageMaker) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("searchType", pageMaker.getSearchType());
        params.put("keyword", pageMaker.getKeyword());
        
        return session.selectOne("ClassHistory-Mapper.selectSearchClassHistoryListCount", params);
    }
    @Override
    public void insertEnroll(ClassHistoryVO classHistory) throws SQLException {
        session.insert("ClassHistory-Mapper.insertEnroll", classHistory);
    }
    @Override
    public List<ClassHistoryVO> selectPopularLectures() throws SQLException {
        return session.selectList("ClassHistory-Mapper.selectPopularLectures");
    }
    @Override
    public void deleteEnroll(int clno) throws SQLException {
        session.delete("ClassHistory-Mapper.deleteEnroll", clno);
    }
    @Override
    public void updateHistory(ClassHistoryVO classHistory) throws SQLException {
        session.update("ClassHistory-Mapper.updateHistory", classHistory);
        
    }

    @Override
    public List<ClassHistoryVO> getEnrolledCoursesByMid(String mid) throws SQLException {
        return session.selectList("ClassHistory-Mapper.getEnrolledCoursesByMid", mid);
    }



}
