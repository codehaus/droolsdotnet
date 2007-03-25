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
using System.Text;
using System.Reflection;
using System.IO;

namespace org.drools.dotnet.examples
{
	[TestFixture]
	public class RuleSetPerformanceTest
	{
		
		[Test]
		public void  TestPerformance()
		{
			RuleSetPerformanceTest test = new RuleSetPerformanceTest();
			//test.test2KRuleFire3Conditions();
			//test.test2KRuleFire2Conditions();
			//test.test2KRuleFire();
			test.testFiveHundredRuleFire();
			//test.testFiftyRuleFire();
			//test.testFiveRuleFire();
		}
		
		private RuleBase readRule(System.String file, string fileName)
		{
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.benchmark." + file);
            PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            FileStream fstream = new FileStream(fileName, FileMode.Create);
            ruleBase.Save(fstream);
            fstream.Close();

            return ruleBase;
		}
		

		/// <summary> test the performance with 50 facts and 5 rules. The test measures
		/// the load, assert and fire time.
		/// </summary>
		public virtual void  testFiveRuleFire()
		{
			try
			{
				int factCount = 50000;
				System.String file = "5_rules.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				for (int c = 0; c < loop; c++)
				{
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
                    RuleBase ruleBase = null;
                    if (c == 0)
                        ruleBase = readRule(file, "C:\\PerformanceTest_5Rules.rlb");
                    else
                        ruleBase = readPrecompiled("C:\\PerformanceTest_5Rules.rlb");
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Account acc = new Account();
						acc.AccountId = "acc" + idx;
						acc.Title = "mr";
						acc.Status = "standard";
						objects.Add(acc);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//UPGRADE_TODO: Method 'java.util.Iterator.hasNext' was converted to 'System.Collections.IEnumerator.MoveNext' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratorhasNext'"
					while (itr.MoveNext())
					{
						//UPGRADE_TODO: Method 'java.util.Iterator.next' was converted to 'System.Collections.IEnumerator.Current' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratornext'"
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
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

        private RuleBase readPrecompiled(string fileName)
        {
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            FileStream fstream = new FileStream(fileName, FileMode.Open);
            ruleBase.Load(fstream);
            fstream.Close();

            return ruleBase;
        }
		
		/// <summary> 
		/// 
		/// </summary>
		public virtual void  testFiftyRuleFire()
		{
			try
			{
				int factCount = 50000;
				System.String file = "50_rules.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				for (int c = 0; c < loop; c++)
				{
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
                    RuleBase ruleBase = null;
                    if (c == 0)
                        ruleBase = readRule(file, "C:\\PerformanceTest_50Rules.rlb");
                    else
                        ruleBase = readPrecompiled("C:\\PerformanceTest_50Rules.rlb");
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Account acc = new Account();
						acc.AccountId = "acc" + idx;
						acc.Title = "mr";
						acc.Status = "standard";
						objects.Add(acc);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//UPGRADE_TODO: Method 'java.util.Iterator.hasNext' was converted to 'System.Collections.IEnumerator.MoveNext' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratorhasNext'"
					while (itr.MoveNext())
					{
						//UPGRADE_TODO: Method 'java.util.Iterator.next' was converted to 'System.Collections.IEnumerator.Current' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratornext'"
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
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
		public virtual void  testFiveHundredRuleFire()
		{
			try
			{
				int factCount = 50000;
				System.String file = "500_rules.drl";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				for (int c = 0; c < loop; c++)
				{
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
                    RuleBase ruleBase = null;
                    if(c == 0)
                        ruleBase = readRule(file, "PerformanceTest_500Rules.rlb");
                    else
                        ruleBase = readPrecompiled("PerformanceTest_500Rules.rlb");
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Account acc = new Account();
						acc.AccountId = "acc" + idx;
						acc.Title = "mr";
						acc.Status = "standard";
						objects.Add(acc);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//UPGRADE_TODO: Method 'java.util.Iterator.hasNext' was converted to 'System.Collections.IEnumerator.MoveNext' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratorhasNext'"
					while (itr.MoveNext())
					{
						//UPGRADE_TODO: Method 'java.util.Iterator.next' was converted to 'System.Collections.IEnumerator.Current' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratornext'"
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
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
		public virtual void  test2KRuleFire()
		{
			try
			{
				int factCount = 50000;
				System.String file = "2000_rules_1condition.rule";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				for (int c = 0; c < loop; c++)
				{
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
                    RuleBase ruleBase = null;
                    if (c == 0)
                        ruleBase = readRule(file, "C:\\PerformanceTest_2KRules.rlb");
                    else
                        ruleBase = readPrecompiled("C:\\PerformanceTest_2KRules.rlb");
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Account acc = new Account();
						acc.AccountId = "acc" + idx;
						acc.Title = "mr";
						acc.Status = "standard";
						objects.Add(acc);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//UPGRADE_TODO: Method 'java.util.Iterator.hasNext' was converted to 'System.Collections.IEnumerator.MoveNext' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratorhasNext'"
					while (itr.MoveNext())
					{
						//UPGRADE_TODO: Method 'java.util.Iterator.next' was converted to 'System.Collections.IEnumerator.Current' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratornext'"
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
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
		
		public virtual void  test2KRuleFire2Conditions()
		{
			try
			{
				int factCount = 50000;
				System.String file = "2000_rules_2condition.rule";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				for (int c = 0; c < loop; c++)
				{
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
                    RuleBase ruleBase = null;
                    if (c == 0)
                        ruleBase = readRule(file, "C:\\PerformanceTest_2K2Rules.rlb");
                    else
                        ruleBase = readPrecompiled("C:\\PerformanceTest_2K2Rules.rlb");
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Account acc = new Account();
						acc.AccountId = "acc" + idx;
						acc.Title = "mr";
						acc.Status = "standard";
						objects.Add(acc);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//UPGRADE_TODO: Method 'java.util.Iterator.hasNext' was converted to 'System.Collections.IEnumerator.MoveNext' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratorhasNext'"
					while (itr.MoveNext())
					{
						//UPGRADE_TODO: Method 'java.util.Iterator.next' was converted to 'System.Collections.IEnumerator.Current' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratornext'"
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
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
		
		public virtual void  test2KRuleFire3Conditions()
		{
			try
			{
				int factCount = 50000;
				System.String file = "2000_rules_3condition.rule";
				int loop = 5;
				long totalload = 0;
				long totalassert = 0;
				long totalfire = 0;
				for (int c = 0; c < loop; c++)
				{
					long loadStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
                    RuleBase ruleBase = null;
                    if (c == 0)
                        ruleBase = readRule(file, "C:\\PerformanceTest_2K3Rules.rlb");
                    else
                        ruleBase = readPrecompiled("C:\\PerformanceTest_2K3Rules.rlb");
					long loadEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long loadet = loadEnd - loadStart;
					totalload += loadet;
					System.Console.Out.WriteLine("time to load " + file + " " + loadet + "ms");
					WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
					System.Collections.ArrayList objects = new System.Collections.ArrayList();
					// create the objects
					for (int idx = 0; idx < factCount; idx++)
					{
						Account acc = new Account();
						acc.AccountId = "acc" + idx;
						acc.Title = "mr";
						acc.Status = "standard";
						objects.Add(acc);
					}
					System.Collections.IEnumerator itr = objects.GetEnumerator();
					long assertStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					//UPGRADE_TODO: Method 'java.util.Iterator.hasNext' was converted to 'System.Collections.IEnumerator.MoveNext' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratorhasNext'"
					while (itr.MoveNext())
					{
						//UPGRADE_TODO: Method 'java.util.Iterator.next' was converted to 'System.Collections.IEnumerator.Current' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javautilIteratornext'"
						workingMemory.assertObject(itr.Current);
					}
					long assertEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long assertet = assertEnd - assertStart;
					totalassert += assertet;
					System.Console.Out.WriteLine("time to assert " + assertet + " ms");
					long fireStart = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					workingMemory.fireAllRules();
					long fireEnd = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
					long fireet = fireEnd - fireStart;
					totalfire += fireet;
					System.Console.Out.WriteLine("time to fireAllRules " + fireet + " ms");
					workingMemory.dispose();
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