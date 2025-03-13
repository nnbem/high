package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import common.application.job.dto.MypageMenuVO;

public interface MypageMenuService {

    List<MypageMenuVO> getMainMypageList() throws SQLException;

    List<MypageMenuVO> getSubMypageMenuList(String mCode) throws SQLException;

    MypageMenuVO getMypageMenuByMcode(String mCode) throws SQLException;

    MypageMenuVO getMypageMenuByMname(String mName) throws SQLException;

}
