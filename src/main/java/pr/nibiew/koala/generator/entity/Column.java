package pr.nibiew.koala.generator.entity;

/**
 * @author JG.Hannibal
 * @since 2021/2/18 17:20
 */
public class Column {

    /**
     * 列名
     */
    private String columnName;
    /**
     * 列名类型
     */
    private String dataType;
    /**
     * 列名备注
     */
    private String comments;

    /**
     * 属性名称(大驼峰)，如：user_name => UserName
     */
    private String attrNameUpperCamelCase;
    /**
     * 属性名称(小驼峰)，如：user_name => userName
     */
    private String attrNameLowerCamelCase;
    /**
     * 属性类型
     */
    private String attrType;
    /**
     * auto_increment
     */
    private String extra;

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", comments='" + comments + '\'' +
                ", attrNameUpperCamelCase='" + attrNameUpperCamelCase + '\'' +
                ", attrNameLowerCamelCase='" + attrNameLowerCamelCase + '\'' +
                ", attrType='" + attrType + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }

    public Column() {
    }

    public Column(String columnName, String dataType, String comments, String attrNameUpperCamelCase, String attrNameLowerCamelCase, String attrType, String extra) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.comments = comments;
        this.attrNameUpperCamelCase = attrNameUpperCamelCase;
        this.attrNameLowerCamelCase = attrNameLowerCamelCase;
        this.attrType = attrType;
        this.extra = extra;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAttrNameUpperCamelCase() {
        return attrNameUpperCamelCase;
    }

    public void setAttrNameUpperCamelCase(String attrNameUpperCamelCase) {
        this.attrNameUpperCamelCase = attrNameUpperCamelCase;
    }

    public String getAttrNameLowerCamelCase() {
        return attrNameLowerCamelCase;
    }

    public void setAttrNameLowerCamelCase(String attrNameLowerCamelCase) {
        this.attrNameLowerCamelCase = attrNameLowerCamelCase;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
    