package common.application.job.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.application.job.dto.MypageMenuVO;

@Repository
public class MypageMenuDAOImpl implements MypageMenuDAO{

    @Autowired
    private SqlSession session;

    @Override
    public List<MypageMenuVO> selectMainMypageMenu() throws SQLException {
        return session.selectList("MypageMenu-Mapper.selectMainMypageMenu");
    }
    @Override
    public List<MypageMenuVO> selectSubMypageMenu(String mCode) throws SQLException {
        return session.selectList("MypageMenu-Mapper.selectSubMypageMenu", mCode);
    }
    @Override
    public MypageMenuVO selectMypageByMcode(String mCode) throws SQLException {
        return session.selectOne("MypageMenu-Mapper.selectMypageByMcode", mCode);
    }

    @Override
    public MypageMenuVO selectMypageByMname(String mName) throws SQLException {
        return session.selectOne("MypageMenu-Mapper.selectMypageByMname", mName);
    }

}
