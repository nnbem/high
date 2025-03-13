package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.ClassMypageMenuDAO;
import common.application.cla.dto.ClassMypageMenuVO;

@Service
public class ClassMypageMenuServiceImpl implements ClassMypageMenuService{

    @Autowired
    private ClassMypageMenuDAO classMypageMenuDAO;    
    
    @Override
    public List<ClassMypageMenuVO> getClassMypageList() throws SQLException {
        return classMypageMenuDAO.selectClassMypageMenu();
    }
    @Override
    public List<ClassMypageMenuVO> getSubMypageMenuList(String cmCode) throws SQLException {
        return classMypageMenuDAO.selectSubClassMypageMenu(cmCode);
    }
    @Override
    public ClassMypageMenuVO getClassMypageMenuByCmname(String cmName) throws SQLException {
        return classMypageMenuDAO.selectClassMypageMenuByCmname(cmName);
    }
    @Override
    public ClassMypageMenuVO getClassMypageMenuByCmcode(String cmCode) throws SQLException {
        return classMypageMenuDAO.selectClassMypageMenuByCmcode(cmCode);
    }
    
}
