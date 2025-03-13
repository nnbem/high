package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.application.cla.dao.ClassMenuDAO;
import common.application.cla.dto.ClassMenuVO;

@Service
public class ClassMenuServiceImpl implements ClassMenuService{

    @Autowired
	private ClassMenuDAO classMenuDAO;
    
    @Override
    public List<ClassMenuVO> getMainClassMenuList() throws SQLException {
        return classMenuDAO.selectMainClassMenu();
    }
    
    @Override
    public List<ClassMenuVO> getSubClassMenuList(String cCode) throws SQLException {
        return classMenuDAO.selectSubClassMenu(cCode);
    }
    @Override
    public ClassMenuVO getClassMenuByCname(String cName) throws SQLException {
        return classMenuDAO.selectClassMenuByCname(cName);
    }
    @Override
    public ClassMenuVO getClassMenuByCcode(String cCode) throws SQLException {
        return classMenuDAO.selectClassMenuByCcode(cCode);
    }

}
