/** 
* Copyright (c) 2007, Ritu Jain, Chinmay Nagarkar and Sahi Technologies Pvt Ltd
* All rights reserved.
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the Sahi Technologies Pvt. Ltd./Esahi.com  nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
using System;
using org.drools.dotnet.compiler;
using org.drools.dotnet.rule;
using NUnit.Framework;
using System.Reflection;
using org.drools.dotnet.decisiontable;


namespace org.drools.dotnet.examples
{
	[TestFixture]
	public class SpreadsheetIntegrationTest
	{
		[Test]
		public void  TestExecute()
		{
			SpreadsheetCompiler converter = new SpreadsheetCompiler();
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.resources.data.IntegrationExampleTest.xls");
			System.String drl = converter.Compile(stream, InputType.XLS);
			Assert.IsNotNull(drl);
			//COMPILE
			PackageBuilder builder = new PackageBuilder();
			builder.AddPackageFromDrl(drl);
			
			Package pkg = builder.GetPackage();
			Assert.IsNotNull(pkg);
			System.Console.Out.WriteLine(pkg.GetErrorSummary());
			Assert.AreEqual(0, builder.GetErrors().Length);
			
			RuleBase rb = RuleBaseFactory.NewRuleBase();
			rb.AddPackage(pkg);
			
			WorkingMemory wm = rb.NewWorkingMemory();
			
			//ASSERT AND FIRE
			wm.assertObject(new Cheese("stilton", 42));
			wm.assertObject(new Person("michael", "stilton", 42));
			System.Collections.IList list = new System.Collections.ArrayList();
			wm.setGlobal("list", list);
			wm.fireAllRules();
			Assert.AreEqual(1, list.Count);
		}
        private java.io.InputStream GetJavaInputStream(System.IO.Stream stream)
        {
            byte[] buffer = new byte[stream.Length];
            stream.Read(buffer, 0, buffer.Length);
            java.io.ByteArrayInputStream byteStreams = new java.io.ByteArrayInputStream(buffer);
            return byteStreams;
        }
	}
}