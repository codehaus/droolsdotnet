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



namespace org.drools.dotnet.examples
{
	
    [TestFixture]
	public class HonestPoliticianExample
	{
		
		[Test]
		public void  TestHonestPolitician()
		{
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.HonestPolitician.drl");
            PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            WorkingMemory workingMemory = ruleBase.NewWorkingMemory();
            	
			

			Politician blair = new Politician("blair", true);
			Politician bush = new Politician("bush", true);
			Politician chirac = new Politician("chirac", true);
			Politician schroder = new Politician("schroder", true);
			
			workingMemory.assertObject(blair);
			workingMemory.assertObject(bush);
			workingMemory.assertObject(chirac);
			workingMemory.assertObject(schroder);
			
			workingMemory.fireAllRules();
			
		
		}
		
	}
    public class Politician
    {
        public bool Honest
        {
            get
            {
                return honest;
            }

            set
            {
                this.honest = value;
            }

        }
        public System.String Name
        {
            get
            {
                return name;
            }

        }
        private System.String name;

        private bool honest;

        public Politician(System.String name, bool honest)
         {
            this.name = name;
            this.honest = honest;
        }
    }

    public class Hope
    {
    }

}