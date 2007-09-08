package org.drools.util.asm;

import java.lang.reflect.Constructor;

import org.drools.DroolsEnv;

public class IntrospectionFactory 
{
	public static IClassFieldInspector CreateClassFieldInspector(Class clazz)
	{
		IClassFieldInspector x = null;
		try
		{
			//if(DroolsEnv.isJava())
			//{
				x = new ClassFieldInspector(clazz);
			/*}
			else
			{
				Class cl = Class.forName("cli.Prototype1.ClassFieldInspector");
				Constructor c = cl.getConstructor(new Class[]{Class.class});
				x = (IClassFieldInspector)c.newInstance(new Object[]{clazz});
			}*/
			
		}
		catch(Exception ex)
		{
			/*placeholder */ //System.Console.Out.WriteLine(ex.getMessage());
		}
		return x;
		
	}

}
