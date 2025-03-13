package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.cla.dto.ClassMenuVO;

@Repository
public class ClassMenuDAOImpl implements ClassMenuDAO{

    @Autowired
	private SqlSession session;

    
    
    @Override
    public List<ClassMenuVO> selectMainClassMenu() throws SQLException {
        return session.selectList("ClassMenu-Mapper.selectMainClassMenu");
    }
    
    @Override
    public List<ClassMenuVO> selectSubClassMenu(String cCode) throws SQLException {
        return session.selectList("ClassMenu-Mapper.selectSubClassMenu", cCode);
    }
    @Override
    public ClassMenuVO selectClassMenuByCcode(String cCode) throws SQLException {
        return session.selectOne("ClassMenu-Mapper.selectClassMenuByCcode", cCode);
    }
    @Override
    public ClassMenuVO selectClassMenuByCname(String cName) throws SQLException {
        return session.selectOne("ClassMenu-Mapper.selectClassMenuByCname", cName);
    }

    
}
