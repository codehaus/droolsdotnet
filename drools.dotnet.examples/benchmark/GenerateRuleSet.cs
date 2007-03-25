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
namespace org.drools.dotnet.examples
{
	[TestFixture]
	public class GenerateRuleSet
	{
		
		internal string linebreak = System.Environment.NewLine;
		internal string tab = "\t";
		
		/// <summary> </summary>
		public GenerateRuleSet()
		{
		}
		
        
		public void  generate3Condition(string outputFile, int count)
		{
			try
			{
				System.IO.StreamWriter writer = new System.IO.StreamWriter(outputFile, false, System.Text.Encoding.Default);
				System.Text.StringBuilder buf = new System.Text.StringBuilder();
				buf.Append("package org.drools.samples" + linebreak);
				buf.Append("import org.drools.dotnet.examples" + linebreak + linebreak);
				// now loop
				for (int idx = 0; idx < count; idx++)
				{
					buf.Append("rule rule" + idx + "" + linebreak);
					buf.Append("  when" + linebreak);
					buf.Append("    acc : Account(Status == \"standard\", Title == \"mr\", AccountId == \"acc" + idx + "\")" + linebreak);
					buf.Append("  then" + linebreak);
					buf.Append("    System.Console.Out.WriteLine(\"rule" + idx + " fired\");" + linebreak);
					buf.Append("end" + linebreak + linebreak);
                    
				}
				writer.Write(buf.ToString());
				writer.Close();
				System.Console.Out.WriteLine("Generated " + count + " rules to " + outputFile);
			}
			catch (System.Exception e)
			{
				
			}
		}
		
		public void  generate2Condition(string outputFile, int count)
		{
			try
			{
				
				System.IO.StreamWriter writer = new System.IO.StreamWriter(outputFile, false, System.Text.Encoding.Default);
				System.Text.StringBuilder buf = new System.Text.StringBuilder();
				// delcare the package
				buf.Append("package org.drools.samples" + linebreak);
				buf.Append("import org.drools.dotnet.examples" + linebreak + linebreak);
				// now loop
				for (int idx = 0; idx < count; idx++)
				{
					buf.Append("rule rule" + idx + "" + linebreak);
					buf.Append("  when" + linebreak);
					buf.Append("    acc : Account(Status == \"standard\", AccountId == \"acc" + idx + "\")" + linebreak);
					buf.Append("  then" + linebreak);
                    buf.Append("    System.Console.Out.WriteLine(\"rule" + idx + " fired\");" + linebreak);
					buf.Append("end" + linebreak + linebreak);
				}
				writer.Write(buf.ToString());
				writer.Close();
				System.Console.Out.WriteLine("Generated " + count + " rules to " + outputFile);
			}
			catch (System.Exception e)
			{
				
			}
		}
		
		public void  generateOneCondition(string outputFile, int count)
		{
			try
			{
				System.IO.StreamWriter writer = new System.IO.StreamWriter(outputFile, false, System.Text.Encoding.Default);
				System.Text.StringBuilder buf = new System.Text.StringBuilder();
				// delcare the package
				buf.Append("package org.drools.samples" + linebreak);
				buf.Append("import org.drools.dotnet.examples" + linebreak + linebreak);
				// now loop
				for (int idx = 0; idx < count; idx++)
				{
					buf.Append("rule rule" + idx + "" + linebreak);
					buf.Append("  when" + linebreak);
					buf.Append("    acc : Account(AccountId == \"acc" + idx + "\")" + linebreak);
					buf.Append("  then" + linebreak);
                    buf.Append("    System.Console.Out.WriteLine(\"rule" + idx + " fired\");" + linebreak);
					buf.Append("end" + linebreak + linebreak);
				}
				writer.Write(buf.ToString());
				writer.Close();
				System.Console.Out.WriteLine("Generated " + count + " rules to " + outputFile);
			}
			catch (System.Exception e)
			{
				
			}
		}
		
		[Test]
		public void  TestGenerateRuleSet()
		{
			// the number of rules
			int count = 1000;
			string objectName = "Account";
			string outputFileType1 = count + "_rules_type1.drl";
            string outputFileType2 = count + "_rules_type2.drl";
            string outputFileType3 = count + "_rules_type3.drl";
			
			GenerateRuleSet grs = new GenerateRuleSet();
            grs.generateOneCondition(outputFileType1, count);
            grs.generate2Condition(outputFileType2, count);
            grs.generate3Condition(outputFileType3, count);
		}
	}
}