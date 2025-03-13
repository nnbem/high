package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassListVO;
import common.application.request.PageMaker;

public interface ClassListDAO {

    // 전체 강의 목록 조회 (페이지네이션)
    List<ClassListVO> selectClassList(PageMaker pageMaker) throws SQLException;

    // 특정 분야 강의 목록 조회 (페이지네이션)
    List<ClassListVO> selectClassListByField(PageMaker pageMaker) throws SQLException;

    // 전체 강의 개수 조회
    int selectClassListCount(PageMaker pageMaker) throws SQLException;

    // 특정 분야 강의 개수 조회
    int selectClassListCountByField(PageMaker pageMaker) throws SQLException;

}
