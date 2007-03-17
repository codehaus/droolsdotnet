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

namespace org.drools.dotnet.examples
{
    [TestFixture]	
	public class SharedNodePerformanceTest
	{
		
		[Test]
		public void TestShareNodePerformanceTest()
		{
			SharedNodePerformanceTest test = new SharedNodePerformanceTest();
			test.testFiveSharedNodes();
			test.testFourSharedNodes();
			test.testThreeSharedNodes();
			test.testTwoSharedNodes();
			test.testOneSharedNodes();
			test.testNoneSharedNodes();
		}
		
		private RuleBase readRule(string file)
		{
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.benchmark." + file);

            PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            return ruleBase;
		}
		
		/// <summary> test the performance with 50 facts and 5 rules. The test measures
		/// the load, assert and fire time.
		/// </summary>
		public void  testFiveSharedNodes()
		{
			try
			{
				int factCount = 5000;
				string file = "20rules_5shared.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				long aveloadmem = 0;
				long aveassertmem = 0;
				System.Diagnostics.Process rt = System.Diagnostics.Process.GetCurrentProcess();
				for (int c = 0; c < loop; c++)
				{
					System.GC.Collect();
					//long memt1 = rt.totalMemory();
					//long memf1 = rt.freeMemory();
					//long used1 = memt1 - memf1;
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					RuleBase ruleBase = readRule(file);
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//long memt2 = rt.totalMemory();
					//long memf2 = rt.freeMemory();
					//long used2 = memt2 - memf2;
					long loadet = loadEnd - loadStart;
					System.GC.Collect();
					totalload += loadet;
					//aveloadmem += (used2 - used1);
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					//System.Console.Out.WriteLine("load memory used " + ((used2 - used1) / 1024) + " kb");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Address addr = new Address();
						addr.City = "boston";
						addr.State = "ma";
						addr.HouseType = "single family";
						addr.Status = "not listed";
						addr.Country = "usa";
						addr.AccountId = "acc" + idx;
						objects.Add(addr);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					//long memt3 = rt.totalMemory();
					//long memf3 = rt.freeMemory();
					//long used3 = memt3 - memf3;
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					while (itr.MoveNext())
					{
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//long memt4 = rt.totalMemory();
					//long memf4 = rt.freeMemory();
					//long used4 = memt4 - memf4;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					//aveassertmem += (used4 - used3);
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					//System.Console.Out.WriteLine("assert memory used " + ((used4 - used3) / 1024) + " kb");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
					System.GC.Collect();
					System.Console.Out.WriteLine("");
				}
				System.Console.Out.WriteLine(file);
				System.Console.Out.WriteLine("number of objects asserted " + factCount);
				System.Console.Out.WriteLine("average load " + (totalload / loop) + " ms");
				//System.Console.Out.WriteLine("average load mem " + (aveloadmem / 1024 / loop) + " kb");
				System.Console.Out.WriteLine("average assert " + (totalassert / loop) + " ms");
				//System.Console.Out.WriteLine("average assert mem " + (aveassertmem / 1024 / loop) + " kb");
				System.Console.Out.WriteLine("average fire " + (totalfire / loop) + " ms");
			}
			catch (System.Exception e)
			{
				//SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
		
		/// <summary> 
		/// 
		/// </summary>
		public void  testFourSharedNodes()
		{
			try
			{
				int factCount = 5000;
				string file = "20rules_4shared.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				System.Diagnostics.Process rt = System.Diagnostics.Process.GetCurrentProcess();
				for (int c = 0; c < loop; c++)
				{
					System.GC.Collect();
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					RuleBase ruleBase = readRule(file);
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Address addr = new Address();
						addr.City = "boston";
						addr.State = "ma";
						addr.HouseType = "single family";
						addr.Status = "not listed";
						addr.Country = "usa" + idx;
						objects.Add(addr);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					while (itr.MoveNext())
					{
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
					System.GC.Collect();
				}
				System.Console.Out.WriteLine(file);
				System.Console.Out.WriteLine("number of objects asserted " + factCount);
				System.Console.Out.WriteLine("average load " + (totalload / loop) + " ms");
				System.Console.Out.WriteLine("average assert " + (totalassert / loop) + " ms");
				System.Console.Out.WriteLine("average fire " + (totalfire / loop) + " ms");
			}
			catch (System.Exception e)
			{
				//SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
		
		/// <summary> 
		/// 
		/// </summary>
		public void  testThreeSharedNodes()
		{
			try
			{
				int factCount = 5000;
				string file = "20rules_3shared.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				System.Diagnostics.Process rt = System.Diagnostics.Process.GetCurrentProcess();
				for (int c = 0; c < loop; c++)
				{
					System.GC.Collect();
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					RuleBase ruleBase = readRule(file);
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Address addr = new Address();
						addr.City = "boston";
						addr.State = "ma";
						addr.HouseType = "single family";
						addr.Status = "not listed" + idx;
						addr.Country = "usa";
						objects.Add(addr);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					while (itr.MoveNext())
					{
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
					System.GC.Collect();
				}
				System.Console.Out.WriteLine(file);
				System.Console.Out.WriteLine("number of objects asserted " + factCount);
				System.Console.Out.WriteLine("average load " + (totalload / loop) + " ms");
				System.Console.Out.WriteLine("average assert " + (totalassert / loop) + " ms");
				System.Console.Out.WriteLine("average fire " + (totalfire / loop) + " ms");
			}
			catch (System.Exception e)
			{
				//SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
		
		/// <summary> 
		/// 
		/// </summary>
		public void  testTwoSharedNodes()
		{
			try
			{
				int factCount = 5000;
				string file = "20rules_2shared.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				System.Diagnostics.Process rt = System.Diagnostics.Process.GetCurrentProcess();
				for (int c = 0; c < loop; c++)
				{
					System.GC.Collect();
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					RuleBase ruleBase = readRule(file);
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Address addr = new Address();
						addr.City = "boston";
						addr.State = "ma";
						addr.HouseType = "single family" + idx;
						addr.Status = "not listed";
						addr.Country = "usa";
						objects.Add(addr);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					while (itr.MoveNext())
					{
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
					System.GC.Collect();
				}
				System.Console.Out.WriteLine(file);
				System.Console.Out.WriteLine("number of objects asserted " + factCount);
				System.Console.Out.WriteLine("average load " + (totalload / loop) + " ms");
				System.Console.Out.WriteLine("average assert " + (totalassert / loop) + " ms");
				System.Console.Out.WriteLine("average fire " + (totalfire / loop) + " ms");
			}
			catch (System.Exception e)
			{
				//SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
		
		public void  testOneSharedNodes()
		{
			try
			{
				int factCount = 5000;
				string file = "20rules_1shared.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				System.Diagnostics.Process rt = System.Diagnostics.Process.GetCurrentProcess();
				for (int c = 0; c < loop; c++)
				{
					System.GC.Collect();
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					RuleBase ruleBase = readRule(file);
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Address addr = new Address();
						addr.City = "boston";
						addr.State = "ma" + idx;
						addr.HouseType = "single family";
						addr.Status = "not listed";
						addr.Country = "usa";
						objects.Add(addr);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					while (itr.MoveNext())
					{
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
					System.GC.Collect();
				}
				System.Console.Out.WriteLine(file);
				System.Console.Out.WriteLine("number of objects asserted " + factCount);
				System.Console.Out.WriteLine("average load " + (totalload / loop) + " ms");
				System.Console.Out.WriteLine("average assert " + (totalassert / loop) + " ms");
				System.Console.Out.WriteLine("average fire " + (totalfire / loop) + " ms");
			}
			catch (System.Exception e)
			{
				//SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
		
		public void  testNoneSharedNodes()
		{
			try
			{
				int factCount = 5000;
				string file = "20rules_0shared.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				System.Diagnostics.Process rt = System.Diagnostics.Process.GetCurrentProcess();
				for (int c = 0; c < loop; c++)
				{
					System.GC.Collect();
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					RuleBase ruleBase = readRule(file);
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Address addr = new Address();
						addr.AccountId = "acc" + idx;
						addr.City = "boston";
						addr.State = "ma";
						addr.HouseType = "single family";
						addr.Status = "not listed";
						addr.Country = "usa";
						objects.Add(addr);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					while (itr.MoveNext())
					{
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.GC.Collect();
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
					System.GC.Collect();
				}
				System.Console.Out.WriteLine(file);
				System.Console.Out.WriteLine("number of objects asserted " + factCount);
				System.Console.Out.WriteLine("average load " + (totalload / loop) + " ms");
				System.Console.Out.WriteLine("average assert " + (totalassert / loop) + " ms");
				System.Console.Out.WriteLine("average fire " + (totalfire / loop) + " ms");
			}
			catch (System.Exception e)
			{
				//SupportClass.WriteStackTrace(e, Console.Error);
			}
		}
	}
}