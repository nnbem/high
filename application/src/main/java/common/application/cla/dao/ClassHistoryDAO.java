package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.application.cla.dto.ClassHistoryVO;
import common.application.request.PageMaker;

public interface ClassHistoryDAO {
    
    List<ClassHistoryVO> selectClassHistoryList(String mid);

    // 검색 조건 및 페이지네이션을 적용한 수강신청 내역 리스트 조회
    List<ClassHistoryVO> selectSearchClassHistoryList(String mid, PageMaker pageMaker) throws SQLException;
    
    // 검색 조건을 적용한 수강신청 내역 총 건수 조회
    int selectSearchClassHistoryListCount(String mid, PageMaker pageMaker) throws SQLException;
    
    // 인기 강의(수강신청 건수 기준) 조회
    List<ClassHistoryVO> selectPopularLectures() throws SQLException;

    // 수강신청 등록
    void insertEnroll(ClassHistoryVO classHistory) throws SQLException;
    
    // 수강 진행도(시청시간) 업데이트
    void updateHistory(ClassHistoryVO classHistory) throws SQLException;
    
    // 수강신청 취소 (삭제)
    void deleteEnroll(int clno) throws SQLException;

    List<ClassHistoryVO> getEnrolledCoursesByMid(String mid) throws SQLException;

    int getTotalRecords(@Param("mid") String mid, 
                        @Param("searchType") String searchType, 
                        @Param("keyword") String keyword);

}
