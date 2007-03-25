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
	public class GolfingExample
	{
		
		[Test]
		public void  TestGolfingExample()
		{
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.golf.drl");
            PackageBuilder builder = new PackageBuilder();

            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            WorkingMemory workingMemory = ruleBase.NewWorkingMemory();

			
			string[] names = new string[]{"Fred", "Joe", "Bob", "Tom"};
			string[] colors = new string[]{"red", "blue", "plaid", "orange"};
			int[] positions = new int[]{1, 2, 3, 4};
			
			for (int n = 0; n < names.Length; n++)
			{
				for (int c = 0; c < colors.Length; c++)
				{
					for (int p = 0; p < positions.Length; p++)
					{
						workingMemory.assertObject(new Golfer(names[n], colors[c], positions[p]));
                        
					}
				}
			}
			
			workingMemory.fireAllRules();
		}
    }
    public class Golfer
    {
        public System.String Color
        {
            get
            {
                return this.color;
            }

        }
        public System.String Name
        {
            get
            {
                return this.name;
            }

        }
        public int Position
        {
            get
            {
                return this.position;
            }

        }
        private System.String name;
        private System.String color;
        private int position;

        public Golfer(System.String name, System.String color, int position)
        {
            this.name = name;
            this.color = color;
            this.position = position;
        }
    }

}