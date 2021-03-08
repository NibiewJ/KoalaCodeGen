package pr.nibiew.koala.generator.service;

import com.google.common.base.CaseFormat;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pr.nibiew.koala.generator.entity.Column;
import pr.nibiew.koala.generator.entity.Table;
import pr.nibiew.koala.generator.mapper.GeneratorMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2021/2/18 17:18
 */
@Service
public class GeneratorService {

    private TemplateEngine thymeleaf;

    @PostConstruct
    public void setThymeleaf() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setSuffix(".vm");
        templateResolver.setPrefix("templates/javasource/");

        templateResolver.setTemplateMode(TemplateMode.TEXT);

        TemplateEngine templateEngine = new TemplateEngine();

        templateEngine.setTemplateResolver(templateResolver);

        this.thymeleaf = templateEngine;
    }

    @Resource
    private GeneratorMapper generatorMapper;

    /**
     * 数据库类型Java类型 映射表
     */
    private HashMap<String, String> dataTypeAttrTypeMap = new HashMap() {{
        put("tinyint", "Integer");
        put("smallint", "Integer");
        put("mediumint", "Integer");
        put("int", "Integer");
        put("integer", "Integer");
        put("bigint", "Long");
        put("float", "Float");
        put("double", "Double");
        put("decimal", "BigDecimal");
        put("bit", "Boolean");
        put("char", "String");
        put("varchar", "String");
        put("tinytext", "String");
        put("text", "String");
        put("mediumtext", "String");
        put("longtext", "String");
        put("date", "Date");
        put("datetime", "Date");
        put("timestamp", "Date");
        put("NUMBER", "Integer");
        put("INT", "Integer");
        put("INTEGER", "Integer");
        put("BINARY_INTEGER", "Integer");
        put("LONG", "String");
        put("FLOAT", "Float");
        put("BINARY_FLOAT", "Float");
        put("DOUBLE", "Double");
        put("BINARY_DOUBLE", "Double");
        put("DECIMAL", "BigDecimal");
        put("CHAR", "String");
        put("VARCHAR", "String");
        put("VARCHAR2", "String");
        put("NVARCHAR", "String");
        put("NVARCHAR2", "String");
        put("CLOB", "String");
        put("BLOB", "String");
        put("DATE", "Date");
        put("DATETIME", "Date");
        put("TIMESTAMP", "Date");
        put("TIMESTAMP(6)", "Date");
        put("int8", "Long");
        put("int4", "Integer");
        put("int2", "Integer");
        put("numeric", "BigDecimal");
        put("nvarchar", "String");
    }};

    /**
     * 代码生成的配置
     */
    private HashMap<String, String> codeConfig = new HashMap(){{
        put("mainPath", "com.nesc.generator");
        put("package", "com.nesc.generator");
        put("entityPackage", "com.nesc.generator.entity");
        put("entitySuffix", "PO");
        put("moduleName", "generator");
        put("author","jinweibin");
        put("email","plz.wb.jin@gmail.com");
    }};

    /**
     * 查询表
     *
     * @param table
     * @return
     */
    public List<Table> queryTables(Table table) {
        List<Table> tables = generatorMapper.queryTables(table.getTableName());
        tables.stream().forEach((item) -> {
            item.setClassNameUpperCamelCase(tableToJava(item.getTableName(), new String[]{"tbl"}));
            item.setClassNameLowerCamelCase(columnToJava(item.getClassNameUpperCamelCase()));
            item.setColumns(queryColumnsByTableName(item));
        });
        return tables;
    }

    /**
     * 查询指定表的列
     *
     * @param table
     * @return 列对象列表
     */
    public List<Column> queryColumnsByTableName(Table table) {
        List<Column> columns = generatorMapper.queryColumns(table.getTableName());
        columns.stream().forEach((item) -> {
            item.setAttrNameLowerCamelCase(columnToJava(item.getColumnName()));
            item.setAttrNameUpperCamelCase(columnToJavaUpper(item.getColumnName()));
            item.setAttrType(dataTypeAttrTypeMap.get(item.getDataType()));
        });
        return columns;
    }

    /**
     * 生成Java对象代码
     *
     * @param table
     * @return
     */
    public String generatorJavaBean(Table table) {
        Context context = new Context();
        for (String configKey : codeConfig.keySet()) {
            context.setVariable(configKey, codeConfig.get(configKey));
        }
        context.setVariable("tableConfig", table);
        context.setVariable("datetime", new Date());
        return thymeleaf.process("Test", context);
    }

    /**
     * 表名转换成Java类名
     */
    private static String tableToJava(String tableName, String[] tablePrefixArray) {
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                if (tableName.startsWith(tablePrefix)) {
                    tableName = tableName.replaceFirst(tablePrefix, "");
                }
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJavaUpper(String columnName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnName);
    }

}
    