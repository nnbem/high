package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.ClassHistoryDAO;
import common.application.cla.dto.ClassHistoryVO;
import common.application.request.PageMaker;

@Service
public class ClassHistoryServiceImpl implements ClassHistoryService {

    @Autowired
    private ClassHistoryDAO classHistoryDAO;

    @Override
    public List<ClassHistoryVO> getClassHistoryList(String mid) {
        return classHistoryDAO.selectClassHistoryList(mid);
    }

    @Override
    public List<ClassHistoryVO> selectSearchClassHistoryList(String mid, PageMaker pageMaker) throws SQLException {
        return classHistoryDAO.selectSearchClassHistoryList(mid, pageMaker);
    }

    @Override
    public int selectSearchClassHistoryListCount(String mid, PageMaker pageMaker) throws SQLException {
        return classHistoryDAO.selectSearchClassHistoryListCount(mid, pageMaker);
    }

    @Override
    public List<ClassHistoryVO> selectPopularLectures() throws SQLException {
        return classHistoryDAO.selectPopularLectures();
    }

    @Override
    public void insertEnroll(ClassHistoryVO classHistory) throws SQLException {
        classHistoryDAO.insertEnroll(classHistory);
    }

    @Override
    public void updateHistory(ClassHistoryVO classHistory) throws SQLException {
        classHistoryDAO.updateHistory(classHistory);
    }

    @Override
    public void deleteEnroll(int clno) throws SQLException {
        classHistoryDAO.deleteEnroll(clno);
    }

    @Override
    public List<ClassHistoryVO> getEnrolledCoursesByMid(String mid) throws SQLException {
        return classHistoryDAO.getEnrolledCoursesByMid(mid);
    }

}