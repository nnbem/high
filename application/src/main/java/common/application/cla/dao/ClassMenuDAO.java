package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassMenuVO;

public interface ClassMenuDAO {
    
    List<ClassMenuVO> selectMainClassMenu() throws SQLException; 
    
	// 서브메뉴
	List<ClassMenuVO> selectSubClassMenu(String cCode) throws SQLException;

	// 메뉴정보
	ClassMenuVO selectClassMenuByCcode(String cCode) throws SQLException;

	ClassMenuVO selectClassMenuByCname(String cName) throws SQLException;

}
