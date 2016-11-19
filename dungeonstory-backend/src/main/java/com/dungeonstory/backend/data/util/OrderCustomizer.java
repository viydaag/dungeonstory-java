package com.dungeonstory.backend.data.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DatabaseMapping;

/**
 * This class is useful to create database columns in order defined in the java
 * entity.
 * 
 * @author JC
 *
 */
public class OrderCustomizer implements DescriptorCustomizer {

    @Override
    public void customize(ClassDescriptor descriptor) throws Exception {
        descriptor.setShouldOrderMappings(true);
        List<DatabaseMapping> mappings = descriptor.getMappings();
        addWeight(this.getClass(descriptor.getJavaClassName()), mappings);
    }

    private void addWeight(Class<?> cls, List<DatabaseMapping> mappings) {
        Map<String, Integer> fieldOrderMap = getColumnPositions(cls, null, 0);
        for (DatabaseMapping mapping : mappings) {
            String key = mapping.getAttributeName();
            Object obj = fieldOrderMap.get(key);
            int weight = 1;
            if (obj != null) {
                weight = Integer.parseInt(obj.toString());
            }
            mapping.setWeight(weight);
        }
    }

    private Class<?> getClass(String javaFileName) throws ClassNotFoundException {
        Class<?> cls = null;
        if (javaFileName != null && !javaFileName.equals("")) {
            cls = Class.forName(javaFileName);
        }
        return cls;
    }

    private Map<String, Integer> getColumnPositions(Class<?> classFile, Map<String, Integer> columnOrder,
            int startingPosition) {
        if (columnOrder == null) {
            columnOrder = new HashMap<>();
        }
        Field[] fields = classFile.getDeclaredFields();
        int position = startingPosition;
        for (Field field : fields) {
            columnOrder.put(field.getName(), position++);
        }
        // add also fields from mapped superclass
        if (classFile.getSuperclass() != null && classFile.getSuperclass() != Object.class) {
            this.getColumnPositions(classFile.getSuperclass(), columnOrder, position);
        }
        return columnOrder;
    }

}
