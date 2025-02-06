package covy.bulkinsert.service;

import covy.bulkinsert.test.annotation.SpringBootIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * <클래스 설명>
 *
 * @author : junni802
 * @date : 2025-02-03
 */
@SpringBootIntegrationTest
@RequiredArgsConstructor
class BulkUpdateServiceTest {

    private final BulkUpdateService bulkUpdateService;

    @Test
    void insertGoodsList() {
        long startTime = System.currentTimeMillis();
        bulkUpdateService.insertGoodsList(50000);
        System.out.println("최종 걸린 시각 -> " + (System.currentTimeMillis() - startTime));
    }

    @Test
    void insertGoodsList1() {
        long startTime = System.currentTimeMillis();
        bulkUpdateService.insertGoodsList1(50000);
        System.out.println("최종 걸린 시각 -> " + (System.currentTimeMillis() - startTime));
    }

    @Test
    void insertGoodsList2() {
        long startTime = System.currentTimeMillis();
        bulkUpdateService.insertGoodsList2(50000);
        System.out.println("최종 걸린 시각 -> " + (System.currentTimeMillis() - startTime));
    }
}