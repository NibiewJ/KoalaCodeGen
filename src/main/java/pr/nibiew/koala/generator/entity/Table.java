package pr.nibiew.koala.generator.entity;

import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2021/2/18 17:19
 */
public class Table {

    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 表的引擎
     */
    private String engine;
    /**
     * 表的备注
     */
    private String comments;
    /**
     * 表的主键
     */
    private Column pk;
    /**
     * 表的列名(不包含主键)
     */
    private List<Column> columns;

    /**
     * 类名(第一个字母大写)，如：sys_user => SysUser
     */
    private String classNameUpperCamelCase;
    /**
     * 类名(第一个字母小写)，如：sys_user => sysUser
     */
    private String classNameLowerCamelCase;


    public Table() {
    }

    public Table(String tableName, String engine, String comments, Column pk, List<Column> columns, String classNameUpperCamelCase, String classNameLowerCamelCase) {
        this.tableName = tableName;
        this.engine = engine;
        this.comments = comments;
        this.pk = pk;
        this.columns = columns;
        this.classNameUpperCamelCase = classNameUpperCamelCase;
        this.classNameLowerCamelCase = classNameLowerCamelCase;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", engine='" + engine + '\'' +
                ", comments='" + comments + '\'' +
                ", pk=" + pk +
                ", columns=" + columns +
                ", classNameUpperCamelCase='" + classNameUpperCamelCase + '\'' +
                ", classNameLowerCamelCase='" + classNameLowerCamelCase + '\'' +
                '}';
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Column getPk() {
        return pk;
    }

    public void setPk(Column pk) {
        this.pk = pk;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getClassNameUpperCamelCase() {
        return classNameUpperCamelCase;
    }

    public void setClassNameUpperCamelCase(String classNameUpperCamelCase) {
        this.classNameUpperCamelCase = classNameUpperCamelCase;
    }

    public String getClassNameLowerCamelCase() {
        return classNameLowerCamelCase;
    }

    public void setClassNameLowerCamelCase(String classNameLowerCamelCase) {
        this.classNameLowerCamelCase = classNameLowerCamelCase;
    }
}
    