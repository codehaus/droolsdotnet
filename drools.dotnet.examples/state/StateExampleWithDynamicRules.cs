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

namespace org.drools.dotnet.examples
{
    [TestFixture]	
	public class StateExampleWithDynamicRules
	{
        [Test]
		public void TestStateExampleWithDynamicRules()
		{
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.StateExampleUsingSalience.drl");
            PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);

            WorkingMemory workingMemory = ruleBase.NewWorkingMemory();


			State a = new State("A");
			State b = new State("B");
			State c = new State("C");
			State d = new State("D");
			State e = new State("E");
			
			// By setting dynamic to TRUE, Drools will use JavaBean
			// PropertyChangeListeners so you don't have to call modifyObject().
			//UPGRADE_NOTE: Final was removed from the declaration of 'dynamic '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			bool dynamic = true;
			
			workingMemory.assertObject(a, dynamic);
			workingMemory.assertObject(b, dynamic);
			workingMemory.assertObject(c, dynamic);
			workingMemory.assertObject(d, dynamic);
			workingMemory.assertObject(e, dynamic);
			
			workingMemory.fireAllRules();

            
			builder = new PackageBuilder();
            stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.StateExampleDynamicRule.drl");
			builder.AddPackageFromDrl(stream);
			ruleBase.AddPackage(builder.GetPackage());
			
//			workingMemory.fireAllRules();
			
			
		}
	}
}