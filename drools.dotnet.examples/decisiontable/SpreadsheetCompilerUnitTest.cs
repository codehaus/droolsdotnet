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
using NUnit.Framework;
using System.Reflection;
using System.IO;
using org.drools.dotnet.decisiontable;

namespace org.drools.dotnet.examples
{
    [TestFixture]	
	public class SpreadsheetCompilerUnitTest
	{
		[Test]
		public void  TestLoadFromClassPath()
		{
			
			SpreadsheetCompiler converter = new SpreadsheetCompiler();

            System.String drl = converter.Compile(new System.IO.FileStream(System.Environment.CurrentDirectory + "\\resources\\data\\MultiSheetDST.xls", System.IO.FileMode.Open), InputType.XLS);
            Assert.IsNotNull(drl);
			
			
			Assert.IsTrue(drl.IndexOf("rule \"How cool am I_12\"") > drl.IndexOf("rule \"How cool am I_11\""));
			Assert.IsTrue(drl.IndexOf("import example.model.User;") > - 1);
			Assert.IsTrue(drl.IndexOf("import example.model.Car;") > - 1);
		}
		
        [Test]
		public void  TestLoadSpecificWorksheet()
		{
			SpreadsheetCompiler converter = new SpreadsheetCompiler();
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.resources.data.MultiSheetDST.xls");
            System.String drl = converter.Compile(stream, "Another Sheet");
			Assert.IsNotNull(drl);
		}
		
        [Test]
		public void  TestLoadCsv()
		{
			SpreadsheetCompiler converter = new SpreadsheetCompiler();
			System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.resources.data.ComplexWorkbook.csv");
            System.String drl = converter.Compile(stream, InputType.CSV);
			Assert.IsNotNull(drl);
			Assert.IsTrue(drl.IndexOf("myObject.setIsValid(1, 2)") > 0);
			Assert.IsTrue(drl.IndexOf("myObject.size () > 50") > 0);
			//System.out.println(drl);
		}
		
        [Test]
		public void  TestLoadBasic()
		{
			SpreadsheetCompiler converter = new SpreadsheetCompiler();
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.resources.data.BasicWorkbook.xls");
			System.String drl = converter.Compile(stream, InputType.XLS);
			
			Assert.IsNotNull(drl);
			Assert.IsTrue(drl.IndexOf("This is a function block") > - 1);
			Assert.IsTrue(drl.IndexOf("global Class1 obj1;") > - 1);
			//System.out.println(drl);
		}

 	}
}