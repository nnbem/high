package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassListVO;
import common.application.request.PageMaker;

public interface ClassListService {

    List<ClassListVO> selectClassList(PageMaker pageMaker) throws SQLException;

    List<ClassListVO> selectClassListByField(PageMaker pageMaker) throws SQLException;

    int selectClassListCount(PageMaker pageMaker) throws SQLException;

    int selectClassListCountByField(PageMaker pageMaker) throws SQLException;
}
