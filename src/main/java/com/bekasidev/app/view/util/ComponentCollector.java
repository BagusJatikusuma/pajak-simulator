/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import java.awt.Component;
import java.util.List;

/**
 *
 * @author bagus
 */
public class ComponentCollector {
    private String componentId;
    private Component component;
    private List<Component> childComponentList;
    private Component componentParent;

    public ComponentCollector() {
    } 
    
    public ComponentCollector(String componentId, Component component, List<Component> childComponentList, Component componentParent) {
        this.componentId = componentId;
        this.component = component;
        this.childComponentList = childComponentList;
        this.componentParent = componentParent;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public List<Component> getChildComponentList() {
        return childComponentList;
    }

    public void setChildComponentList(List<Component> childComponentList) {
        this.childComponentList = childComponentList;
    }

    public Component getComponentParent() {
        return componentParent;
    }

    public void setComponentParent(Component componentParent) {
        this.componentParent = componentParent;
    }
    
    
    
}
