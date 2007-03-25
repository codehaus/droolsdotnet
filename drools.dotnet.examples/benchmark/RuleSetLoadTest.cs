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
using System.Text;
using System.IO;

namespace org.drools.dotnet.examples
{
	[TestFixture]
	public class RuleSetLoadTest
	{
		
		[Test]
		public void  TestRuleSetLoading()
		{
			RuleSetLoadTest test = new RuleSetLoadTest();
			test.testOneThousandLoad();
			
			// generate the drl first to run this test
			//test.testFourThousandLoad();
		}
		
		private RuleBase readRule(System.String file)
		{
            System.IO.Stream stream = new System.IO.FileStream(file, FileMode.Open);
            PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            return ruleBase;
		}
		
		public virtual void  testOneThousandLoad()
		{
			try
			{
				System.String file = "1000_rules_type1.drl";
				long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
				RuleBase ruleBase = readRule(file);
				long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
				System.Console.Out.WriteLine("time to load " + file + " " + (loadEnd - loadStart) + "ms");
				WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
			}
			catch (System.Exception e)
			{
			//	SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
		
		public virtual void  testFourThousandLoad()
		{
			try
			{
                System.String file = "4000_rules_type1.drl";
				long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
				RuleBase ruleBase = readRule(file);
				long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
				System.Console.Out.WriteLine("time to load " + file + " " + (loadEnd - loadStart) + "ms");
				WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
			}
			catch (System.Exception e)
			{
//				SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
	}
}