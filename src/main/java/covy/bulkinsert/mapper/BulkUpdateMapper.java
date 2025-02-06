package covy.bulkinsert.mapper;

import covy.bulkinsert.model.vo.ProductVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * <클래스 설명>
 *
 * @author : junni802
 * @date : 2025-02-03
 */

@Mapper
public interface BulkUpdateMapper {

    int insertFirstDefaultGoodsList(ProductVo inVo);

    int insertGoodsList(List<ProductVo> inVos);

}
