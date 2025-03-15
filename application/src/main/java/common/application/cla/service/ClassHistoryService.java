package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassHistoryVO;
import common.application.request.PageMaker;

public interface ClassHistoryService {

    List<ClassHistoryVO> getClassHistoryList(String mid);
    
    // 검색 조건 및 페이징 적용된 수강신청 내역 조회
    List<ClassHistoryVO> selectSearchClassHistoryList(String mid, PageMaker pageMaker) throws SQLException;

    // 검색 조건에 맞는 전체 건수 조회
    int selectSearchClassHistoryListCount(String mid, PageMaker pageMaker) throws SQLException;
    
    // 인기 강의 조회
    List<ClassHistoryVO> selectPopularLectures() throws SQLException;
    
    
    // 수강신청 등록
    void insertEnroll(ClassHistoryVO classHistory) throws SQLException;
    
    // 수강 진행도(시청시간) 업데이트
    void updateHistory(ClassHistoryVO classHistory) throws SQLException;
    
    // 수강신청 취소 (삭제)
    void deleteEnroll(int clno) throws SQLException;
    
    public List<ClassHistoryVO> getEnrolledCoursesByMid(String mid) throws SQLException; 
    
    
}
