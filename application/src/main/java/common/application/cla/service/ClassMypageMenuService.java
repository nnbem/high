package common.application.cla.service;

import java.sql.SQLException;
import java.util.List;

import common.application.cla.dto.ClassMypageMenuVO;

public interface ClassMypageMenuService {
    
    List<ClassMypageMenuVO> getClassMypageList() throws SQLException;

    List<ClassMypageMenuVO> getSubMypageMenuList(String cmCode) throws SQLException;

    ClassMypageMenuVO getClassMypageMenuByCmcode(String cmCode) throws SQLException;

    ClassMypageMenuVO getClassMypageMenuByCmname(String cmName) throws SQLException;
    
}
