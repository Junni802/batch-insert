package covy.bulkinsert.service;

import covy.bulkinsert.mapper.BulkUpdateMapper;
import covy.bulkinsert.model.vo.ProductVo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <클래스 설명>
 *
 * @author : junni802
 * @date : 2025-02-03
 */

@Service
@RequiredArgsConstructor
public class BulkUpdateService {

    private final SqlSessionFactory sqlSessionFactory;
    private final BulkUpdateMapper mapper;

    @Transactional
    public void insertGoodsList(int number) {
        try {
            List<ProductVo> goodsList = generateDummyData(number);
            // 배치 모드로 SqlSession 생성
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            BulkUpdateMapper bulkUpdateMapper = sqlSession.getMapper(BulkUpdateMapper.class);

            // 상품 데이터를 하나씩 삽입
            for (ProductVo goods : goodsList) {
                bulkUpdateMapper.insertFirstDefaultGoodsList(goods);

            }

            // 남은 쿼리들을 한 번에 커밋
            sqlSession.flushStatements();
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("배치 삽입 중 오류 발생");
        }
    }

    @Transactional
    public void insertGoodsList1(int number) {
        try {
            List<ProductVo> goodsList = generateDummyData(number);
            // 상품 데이터를 하나씩 삽입
            for (ProductVo goods : goodsList) {
                mapper.insertFirstDefaultGoodsList(goods);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("배치 삽입 중 오류 발생");
        }
    }

    @Transactional
    public void insertGoodsList2(int number) {
        try {
            List<ProductVo> goodsList = generateDummyData(number);

            // goodsList의 사이즈가 1000개를 넘으면, 1000개씩 분할하여 삽입
            int batchSize = 1000;
            for (int i = 0; i < goodsList.size(); i += batchSize) {
                int endIndex = Math.min(i + batchSize, goodsList.size()); // 마지막 배치 처리
                List<ProductVo> batchList = goodsList.subList(i, endIndex); // 1000개씩 나누기
                mapper.insertGoodsList(batchList); // 1000개씩 insert 호출
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("배치 삽입 중 오류 발생");
        }
    }




    // 10만 건의 더미 데이터를 생성하는 메서드
    public List<ProductVo> generateDummyData(int count) {
        List<ProductVo> goodsList = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            ProductVo goods = new ProductVo();

            // 더미 데이터 생성 (goodsCd는 고유한 값을 만들어야 하므로 UUID 사용)
            goods.setGoodsCd("G" + String.format("%05d", i + 1));  // G00001, G00002, ...
            goods.setGoodsNm("상품 " + (i + 1));
            goods.setDescription("상품 " + (i + 1) + "의 설명");
            goods.setPrice(BigDecimal.valueOf(1000 + (i % 100) * 100).toString()); // 예: 1000, 1100, 1200, ...

            goodsList.add(goods);
        }

        return goodsList;
    }

}
