package org.bs.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.bs.api.system.repository.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wkh
 */
public class CodeGenerator {

    /**
     * TABLE
     */
    private static final String[] TABLE = new String[]{"m_role"};
    /**
     * OVERRIDE！！！
     */
    private static final boolean OVERRIDE = false;

    public static final String DB = "jdbc:mysql://192.168.1.102:3306/aug_service?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&allowMultiQueries=true";
//    private static final String ENTITY_PATH = "/bs-system/src/main/java/com/bs/repository/mysql/entity";
//    private static final String SERVICE_PATH = "/bs-system/src/main/java/com/bs/repository/mysql/service";
//    private static final String MAPPER_PATH = "/bs-system/src/main/java/com/bs/repository/mysql/mapper";
//    private static final String SERVICE_IMPL_PATH = "/bs-system/src/main/java/com/repository/mysql/application/service/impl";

    private static final String ENTITY_PATH = "/bs-api/bs-api-system/src/main/java/org/bs/api/system/repository/entity";
    private static final String SERVICE_PATH = "/bs-module/bs-system/src/main/java/org/bs/system/service";
    private static final String MAPPER_PATH = "/bs-module/bs-system/src/main/java/org/bs/system/mapper";
    private static final String SERVICE_IMPL_PATH = "/bs-module/bs-system/src/main/java/org/bs/system/service/impl";

    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");
        return dsc;
    }

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setAuthor("wkh");
        gc.setOpen(false);
        gc.setEntityName("%sEntity");
        gc.setFileOverride(OVERRIDE);
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dataSourceConfig());

        PackageConfig pc = new PackageConfig();

        pc.setParent("");
//        pc.setEntity("com.bs.application.entity");
//        pc.setService("com.bs.application.service");
//        pc.setServiceImpl("com.bs.application.service.impl");
//        pc.setMapper("com.bs.application.mapper");

        pc.setEntity("org.bs.api.system.repository.entity");
        pc.setService("org.bs.system.service");
        pc.setServiceImpl("org.bs.system.service.impl");
        pc.setMapper("org.bs.system.mapper");

        Map<String, String> pathInfo = new HashMap<>(4);
        pathInfo.put(ConstVal.ENTITY_PATH, projectPath + ENTITY_PATH);
        pathInfo.put(ConstVal.SERVICE_PATH, projectPath + SERVICE_PATH);
        pathInfo.put(ConstVal.MAPPER_PATH, projectPath + MAPPER_PATH);
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, projectPath + SERVICE_IMPL_PATH);
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setSuperEntityColumns("deleted_at", "created_at", "updated_at");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setInclude(TABLE);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
