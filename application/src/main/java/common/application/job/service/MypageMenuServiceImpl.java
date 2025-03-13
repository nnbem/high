package common.application.job.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.job.dao.MypageMenuDAO;
import common.application.job.dto.MypageMenuVO;

@Repository
public class MypageMenuServiceImpl implements MypageMenuService {

    @Autowired
    private MypageMenuDAO mypageMenuDAO;

    @Override
    public List<MypageMenuVO> getMainMypageList() throws SQLException {
        return mypageMenuDAO.selectMainMypageMenu();
    }
    @Override
    public List<MypageMenuVO> getSubMypageMenuList(String mCode) throws SQLException {
        return mypageMenuDAO.selectSubMypageMenu(mCode);
    }
    @Override
    public MypageMenuVO getMypageMenuByMname(String mName) throws SQLException {
        return mypageMenuDAO.selectMypageByMname(mName);
    }
    @Override
    public MypageMenuVO getMypageMenuByMcode(String mCode) throws SQLException {
        return mypageMenuDAO.selectMypageByMcode(mCode);
    }

}
