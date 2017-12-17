package com.ferzerkerx.treeinspector;

import com.ferzerkerx.treeinspector.service.TreeDataFileServiceImpl;
import com.ferzerkerx.treeinspector.service.TreeDataService;
import com.google.inject.AbstractModule;

public class ModuleConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(TreeDataService.class).to(TreeDataFileServiceImpl.class);
    }
}
