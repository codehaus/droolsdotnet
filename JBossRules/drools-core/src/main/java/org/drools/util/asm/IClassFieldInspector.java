package org.drools.util.asm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IClassFieldInspector
{
    //final Map  fieldNames  = new HashMap();
    //final Map  fieldTypes  = new HashMap();
    //final Map  methodNames = new HashMap();
    //final Set  nonGetters  = new HashSet();

	List getPropertyGetters();
	
	Map getFieldNames();
	
	Map getFieldTypes();
	
	Map getGetterMethods();
}