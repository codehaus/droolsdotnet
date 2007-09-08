package org.drools;

public class DroolsEnv {
	public static boolean isJava()
	{
		String out = System.getProperty("drools.compiler","CSHARP");
		if(out.equals("CSHARP"))
			return false;
		return true;
	}
}
