package common.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import common.application.cla.dto.ClassHistoryVO;
import common.application.cla.service.ClassHistoryService;

@SpringBootTest
@Transactional
public class TestClassHistoryService {

    @Autowired
    private ClassHistoryService classHistoryService;

    @Test
    void testInsertEnroll() {  // JUnit5에서는 public 필요 없음
        ClassHistoryVO classHistory = new ClassHistoryVO();
        classHistory.setMid("asdf77");
        classHistory.setClno(101);

        try {
            classHistoryService.insertEnroll(classHistory);
            assertNotNull(classHistory);
            System.out.println("✅ insertEnroll 테스트 성공!");
        } catch (SQLException e) {
            fail("❌ SQLException 발생: " + e.getMessage());
        }
    }
}
