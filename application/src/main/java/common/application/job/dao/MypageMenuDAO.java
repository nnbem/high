package common.application.job.dao;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.MypageMenuVO;

public interface MypageMenuDAO {

    List<MypageMenuVO> selectMainMypageMenu() throws SQLException;
    
    List<MypageMenuVO> selectSubMypageMenu(String mCode) throws SQLException;

    MypageMenuVO selectMypageByMcode(String mCode) throws SQLException;

    MypageMenuVO selectMypageByMname(String mName) throws SQLException;

}
