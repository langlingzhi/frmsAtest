package com.nuanshui.frms.test.config;

import com.nuanshui.frms.test.command.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource  {
    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        log.info("typeKey: {}",typeKey);
        if (typeKey.equals(DataSourceType.write.getType())){
            return DataSourceType.write.getType();
        }
        if(typeKey.equals(DataSourceType.app.getType())){
            return DataSourceType.app.getType();
        }
        if(typeKey == null || typeKey.equals(DataSourceType.cs.getType())){
            return DataSourceType.cs.getType();
        }
        return DataSourceType.read.getType();
    }
}
