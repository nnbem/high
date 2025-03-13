package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.ClassListDAO;
import common.application.cla.dto.ClassListVO;
import common.application.request.PageMaker;

@Service
public class ClassListServiceImpl implements ClassListService {

    @Autowired
    private ClassListDAO classListDAO;

    @Override
    public List<ClassListVO> selectClassList(PageMaker pageMaker) throws SQLException {
        return classListDAO.selectClassList(pageMaker);
    }

    @Override
    public List<ClassListVO> selectClassListByField(PageMaker pageMaker) throws SQLException {
        return classListDAO.selectClassListByField(pageMaker);
    }

    @Override
    public int selectClassListCount(PageMaker pageMaker) throws SQLException {
        return classListDAO.selectClassListCount(pageMaker);
    }

    @Override
    public int selectClassListCountByField(PageMaker pageMaker) throws SQLException {
        return classListDAO.selectClassListCountByField(pageMaker);
    }
}
