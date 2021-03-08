package pr.nibiew.koala.generator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pr.nibiew.koala.generator.entity.Table;
import pr.nibiew.koala.generator.service.GeneratorService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2021/2/18 14:30
 */
@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GeneratorService generatorService;


    @GetMapping({"/","","/index"})
    public String index (ModelMap model) throws Exception {
        return "index";
    }

    @GetMapping("/tableList")
    public String tableList(ModelMap model) throws Exception {
        model.put("lists", generatorService.queryTables(new Table()));
        return "tableList";
    }

    @GetMapping("/columnsList/{tableName}")
    public String columnsList(@PathVariable String tableName, ModelMap model) throws Exception {
        Table table = new Table();
        table.setTableName(tableName);
        model.put("tableName", tableName);
        model.put("lists", generatorService.queryColumnsByTableName(table));
        return "columnsList";
    }

    @GetMapping("/generatorTableJavaBean/{tableName}")
    public String generatorTable(ModelMap model, @PathVariable String tableName) throws Exception {
        Table table = new Table();
        table.setTableName(tableName);
        List<Table> tables = generatorService.queryTables(table);
        if (tables.size()==1) {
            table = tables.get(0);
            model.put("tableName", tableName);
            model.put("templateName", GeneratorService.columnToJava(tableName));
            model.put("templateContext", generatorService.generatorJavaBean(table));
        }
        return "codeTemplate";
    }
}
    