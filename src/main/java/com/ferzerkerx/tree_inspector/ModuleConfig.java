package com.ferzerkerx.tree_inspector;

import com.ferzerkerx.tree_inspector.service.TreeDataFileServiceImpl;
import com.ferzerkerx.tree_inspector.service.TreeDataService;
import com.google.inject.AbstractModule;

public class ModuleConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(TreeDataService.class).to(TreeDataFileServiceImpl.class);
    }
}
