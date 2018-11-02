/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bagus
 */
public class ComponentCollectorProvider {
    private static Map<String, ComponentCollector> componentMapper;
    
    public static Map<String, ComponentCollector> getComponentMapper() {
        if (componentMapper == null) componentMapper = new HashMap<>();
        return componentMapper;
    }
}
