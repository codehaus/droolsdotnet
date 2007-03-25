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
using System.Text;
using org.drools.dotnet.compiler;
using org.drools.dotnet.rule;
using System.IO;

namespace org.drools.dotnet.examples.precompiled
{
    [TestFixture]
	public class TroubleTicketExample
	{
		
		/// <param name="args">
		/// </param>
		[Test]
		public void TestASavePkg()
		{
           System.IO.Stream stream =      Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.TroubleTicket.drl");
			PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            FileStream fstream = new FileStream("TroubleticketRuleBase.rlb", FileMode.Create);
            ruleBase.Save(fstream);
            fstream.Close();
            stream.Close();
 		}

        [Test]
        public void TestBLoadPkg()
        {
            //AppDomain.CurrentDomain.Load("tempAssembly.dll");
            FileStream stream = new FileStream("TroubleticketRuleBase.rlb", FileMode.Open);
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.Load(stream);
            WorkingMemory workingMemory = ruleBase.NewWorkingMemory();

            Customer a = new Customer("A", "Gold");
            Customer b = new Customer("B", "Platinum");
            Customer c = new Customer("C", "Silver");
            Customer d = new Customer("D", "Silver");

            Ticket t1 = new Ticket(a);
            Ticket t2 = new Ticket(b);
            Ticket t3 = new Ticket(c);
            Ticket t4 = new Ticket(d);

            FactHandle fa = workingMemory.assertObject(a);
            FactHandle fb = workingMemory.assertObject(b);
            FactHandle fc = workingMemory.assertObject(c);
            FactHandle fd = workingMemory.assertObject(d);

            FactHandle ft1 = workingMemory.assertObject(t1);
            FactHandle ft2 = workingMemory.assertObject(t2);
            FactHandle ft3 = workingMemory.assertObject(t3);
            FactHandle ft4 = workingMemory.assertObject(t4);

            workingMemory.fireAllRules();

            t3.Status = "Done";

            workingMemory.modifyObject(ft3, t3);

            try
            {
                System.Console.Error.WriteLine("[[ Sleeping 5 seconds ]]");
                System.Threading.Thread.Sleep(new System.TimeSpan((System.Int64)10000 * 5000));
            }
            catch (System.Threading.ThreadInterruptedException e)
            {
                //SupportClass.WriteStackTrace(e, Console.Error);
            }

            System.Console.Error.WriteLine("[[ awake ]]");
        }
	}



}