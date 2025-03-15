package common.application.cla.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.cla.dto.ClassHistoryVO;
import common.application.request.PageMaker;

@Repository
public class ClassHistoryDAOImpl implements ClassHistoryDAO {

    @Autowired
    private SqlSession session;

    @Override
    public List<ClassHistoryVO> selectClassHistoryList(String mid) {
        return session.selectList("ClassHistory-Mapper.selectClassHistoryList", mid);
    }

    @Override
    public List<ClassHistoryVO> selectSearchClassHistoryList(String mid, PageMaker pageMaker) throws SQLException {
        int startRow = (pageMaker.getPage() - 1) * pageMaker.getPerPageNum() + 1;
        int endRow = startRow + pageMaker.getPerPageNum() - 1;

        Map<String, Object> params = new HashMap<>();
        params.put("mid", mid);
        params.put("searchType", pageMaker.getSearchType());
        params.put("keyword", pageMaker.getKeyword());
        params.put("startRow", startRow);
        params.put("endRow", endRow);

        return session.selectList("ClassHistory-Mapper.selectSearchClassHistoryList", params);
    }

    @Override
    public int selectSearchClassHistoryListCount(String mid, PageMaker pageMaker) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("searchType", pageMaker.getSearchType());
        params.put("keyword", pageMaker.getKeyword());

        return session.selectOne("ClassHistory-Mapper.selectSearchClassHistoryListCount", params);
    }

    @Override
    public List<ClassHistoryVO> selectPopularLectures() throws SQLException {
        return session.selectList("ClassHistory-Mapper.selectPopularLectures");
    }

    @Override
    public void insertEnroll(ClassHistoryVO classHistory) throws SQLException {
        session.insert("ClassHistory-Mapper.insertEnroll", classHistory);
    }

    @Override
    public void updateHistory(ClassHistoryVO classHistory) throws SQLException {
        session.update("ClassHistory-Mapper.updateHistory", classHistory);
    }

    @Override
    public void deleteEnroll(int clno) throws SQLException {
        session.delete("ClassHistory-Mapper.deleteEnroll", clno);
    }

    @Override
    public List<ClassHistoryVO> getEnrolledCoursesByMid(String mid) throws SQLException {
        return session.selectList("ClassHistory-Mapper.getEnrolledCoursesByMid", mid);
    }

    @Override
    public int getTotalRecords(String mid, String searchType, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("mid", mid);
        params.put("searchType", searchType);
        params.put("keyword", keyword);
        return session.selectOne("ClassHistory-Mapper.getTotalRecords", params);
    }
}
