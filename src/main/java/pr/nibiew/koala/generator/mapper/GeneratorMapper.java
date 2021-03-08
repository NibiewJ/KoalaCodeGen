package pr.nibiew.koala.generator.mapper;

import org.apache.ibatis.annotations.Param;
import pr.nibiew.koala.generator.entity.Column;
import pr.nibiew.koala.generator.entity.Table;

import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2021/3/2 14:56
 */
public interface GeneratorMapper {
    /**
     * 查询库内所有表的列表
     * @return 库内所有表
     * @param tableName 表名
     */
    List<Table> queryTables(@Param("tableName") String tableName);

    /**
     * 查询指定表内的列信息
     * @return 指定表内的列信息
     * @param tableName 表名
     */
    List<Column> queryColumns(@Param("tableName") String tableName);
}
