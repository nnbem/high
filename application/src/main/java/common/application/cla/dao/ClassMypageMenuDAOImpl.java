package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.cla.dto.ClassMypageMenuVO;

@Repository
public class ClassMypageMenuDAOImpl implements ClassMypageMenuDAO{

    @Autowired
    private SqlSession session;
    
    @Override
    public List<ClassMypageMenuVO> selectClassMypageMenu() throws SQLException {
        return session.selectList("ClassMypageMenu-Mapper.selectClassMypageMenu");
    }
    
    @Override
    public List<ClassMypageMenuVO> selectSubClassMypageMenu(String cmCode) throws SQLException {
        return session.selectList("ClassMypageMenu-Mapper.selectSubClassMypageMenu", cmCode);
    }
    @Override
    public ClassMypageMenuVO selectClassMypageMenuByCmcode(String cmCode) throws SQLException {
        return session.selectOne("ClassMypageMenu-Mapper.selectClassMypageMenuByCmcode", cmCode);
    }
    @Override
    public ClassMypageMenuVO selectClassMypageMenuByCmname(String cmName) throws SQLException {
        return session.selectOne("ClassMypageMenu-Mapper.selectClassMypageMenuByCmname", cmName);
    }
    
}
