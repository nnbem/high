package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassMenuVO;

public interface ClassMenuService {

    List<ClassMenuVO> getMainClassMenuList() throws SQLException;

	List<ClassMenuVO> getSubClassMenuList(String cCode) throws SQLException;

	ClassMenuVO getClassMenuByCcode(String cCode) throws SQLException;

	ClassMenuVO getClassMenuByCname(String cName) throws SQLException;
}
