package common.application.cla.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassMypageMenuVO;

public interface ClassMypageMenuDAO {

    List<ClassMypageMenuVO> selectClassMypageMenu() throws SQLException;

    List<ClassMypageMenuVO> selectSubClassMypageMenu(String cmCode) throws SQLException;

    ClassMypageMenuVO selectClassMypageMenuByCmcode(String cmCode) throws SQLException;

    ClassMypageMenuVO selectClassMypageMenuByCmname(String cmName) throws SQLException;
}
