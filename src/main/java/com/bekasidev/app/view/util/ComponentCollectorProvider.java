/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.Pane;

/**
 *
 * @author bagus
 */
public class ComponentCollectorProvider {
    private static Map<String, ComponentCollector> componentMapper;
    private static Map<String, Pane> fxComponentMapper;
    
    public static Map<String, ComponentCollector> getComponentMapper() {
        if (componentMapper == null) componentMapper = new HashMap<>();
        return componentMapper;
    }
    
    public static Map<String, Pane> getComponentFXMapper() {
        if (fxComponentMapper == null) fxComponentMapper = new HashMap<>();
        return fxComponentMapper;
    }
    
    public static void addComponent(
            String componentId, 
            Component component, 
            List<Component> childComponentList, 
            Component componentParent) {
        Map<String, ComponentCollector> comMap 
                = getComponentMapper();
        ComponentCollector compCollector = new ComponentCollector(componentId, component,childComponentList, componentParent);
        comMap.put(componentId, compCollector);
    }
    
    public static void addFxComponent(
            String componentId, 
            Pane pane) {
        Map<String, Pane> comMap 
                = getComponentFXMapper();
        comMap.put(componentId, pane);
    }
    
    public static void addComponentChild(List<Component> childComponentList, String componentParent) {
        ComponentCollector componentCollector
                = getComponentMapper().get(componentParent);
        if (componentCollector == null) {
            System.out.println("ada masalah teknis");
        }
        
        componentCollector.setChildComponentList(childComponentList);
        
    }
    
    public static void addComponentParent(Component componentParent, String componentChild) {
        ComponentCollector componentCollector
                = getComponentMapper().get(componentChild);
        if (componentCollector == null) {
            System.out.println("ada masalah teknis");
        }
        
        componentCollector.setComponentParent(componentParent);
    }
}
