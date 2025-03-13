package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.ClassHistoryDAO;
import common.application.cla.dto.ClassHistoryVO;
import common.application.request.PageMaker;

@Service
public class ClassHistoryServiceImpl implements ClassHistoryService{

    @Autowired
    private ClassHistoryDAO classHistoryDAO;
    
    @Override
    public List<ClassHistoryVO> selectAllClassHistoryList(PageMaker pageMaker) throws SQLException {
        // 전체 수강신청 내역 조회 (Mapper에서 페이징/검색 없이 전체를 가져오는 쿼리 사용)
        return classHistoryDAO.selectAllClassHistoryList(pageMaker);
    }

    @Override
    public List<ClassHistoryVO> selectSearchClassHistoryList(PageMaker pageMaker) throws SQLException {
        // 검색 조건 및 페이징을 적용한 수강신청 내역 조회
        return classHistoryDAO.selectSearchClassHistoryList(pageMaker);
    }

    @Override
    public int selectSearchClassHistoryListCount(PageMaker pageMaker) throws SQLException {
        // 검색 조건에 맞는 전체 건수 조회
        return classHistoryDAO.selectSearchClassHistoryListCount(pageMaker);
    }

    @Override
    public List<ClassHistoryVO> selectPopularLectures() throws SQLException {
        // 인기 강의 조회 (페이지네이션 없이 전체 인기 강의 목록 조회)
        return classHistoryDAO.selectPopularLectures();
    }

    @Override
    public void insertEnroll(ClassHistoryVO classHistory) throws SQLException {
        // 수강신청 등록
        classHistoryDAO.insertEnroll(classHistory);
    }

    @Override
    public void updateHistory(ClassHistoryVO classHistory) throws SQLException {
        // 수강 진행도(시청시간) 업데이트
        classHistoryDAO.updateHistory(classHistory);
    }

    @Override
    public void deleteEnroll(int clno) throws SQLException {
        // 수강신청 취소 (삭제)
        classHistoryDAO.deleteEnroll(clno);
    }

    public List<ClassHistoryVO> getEnrolledCoursesByMid(String mid) throws SQLException {
        return classHistoryDAO.getEnrolledCoursesByMid(mid);
    }
    
}
