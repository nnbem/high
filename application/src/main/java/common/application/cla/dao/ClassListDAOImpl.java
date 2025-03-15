package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.cla.dto.ClassListVO;
import common.application.request.PageMaker;

@Repository
public class ClassListDAOImpl implements ClassListDAO {

    @Autowired
    private SqlSession session;

    @Override
    public List<ClassListVO> selectClassList(PageMaker pageMaker) throws SQLException {
        int offset = pageMaker.getStartRow()-1;
		int limit = pageMaker.getPerPageNum();
		RowBounds bounds = new RowBounds(offset,limit);

        //List<BoardVO> boardList = session.selectList("Board-Mapper.selectSearchBoardList",pageMaker,bounds);
        List<ClassListVO> classList = session.selectList("ClassList-Mapper.selectClassList", pageMaker, bounds);
        return classList;
    }

    @Override
    public List<ClassListVO> selectClassListByField(PageMaker pageMaker) throws SQLException {
        int offset = pageMaker.getStartRow() - 1;
        int limit = pageMaker.getPerPageNum();
        RowBounds bounds = new RowBounds(offset, limit);
        return session.selectList("ClassList-Mapper.selectClassListByField", pageMaker, bounds);
    }

    @Override
    public int selectClassListCount(PageMaker pageMaker) throws SQLException {
        return session.selectOne("ClassList-Mapper.selectClassListCount", pageMaker);
    }

    @Override
    public int selectClassListCountByField(PageMaker pageMaker) throws SQLException {
        return session.selectOne("ClassList-Mapper.selectClassListCountByField", pageMaker);
    }
}
