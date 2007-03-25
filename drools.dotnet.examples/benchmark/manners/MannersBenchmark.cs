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
using java.util;
using org.drools.dotnet.compiler;
using org.drools.dotnet.rule;

namespace org.drools.dotnet.examples
{
	[TestFixture]
	public class MannersBenchmark
	{
		
		[Test]
		public void  TestMannerBenchmark()
		{
            System.IO.Stream stream = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.rules.benchmark.manners.manners.drl");
            PackageBuilder builder = new PackageBuilder();
            builder.AddPackageFromDrl(stream);
            Package pkg = builder.GetPackage();
            RuleBase ruleBase = RuleBaseFactory.NewRuleBase();
            ruleBase.AddPackage(pkg);
            WorkingMemory workingMemory = ruleBase.NewWorkingMemory();


            
            System.IO.Stream inStr = Assembly.GetAssembly(this.GetType()).GetManifestResourceStream("org.drools.dotnet.examples.resources.benchmark.manners.manners64.dat");
			System.Collections.IList list = getInputObjects(inStr);
			for (System.Collections.IEnumerator it = list.GetEnumerator(); it.MoveNext(); )
			{
				System.Object obj = it.Current;
				workingMemory.assertObject(obj);
			}
			
			workingMemory.assertObject(new Count(1));
			
			long start = (System.DateTime.Now.Ticks - 621355968000000000) / 10000;
			workingMemory.fireAllRules();
			System.Console.Out.WriteLine((System.DateTime.Now.Ticks - 621355968000000000) / 10000 - start);
		}
		
		/// <summary> Convert the facts from the <code>InputStream</code> to a list of
		/// objects.
		/// </summary>
		System.Collections.IList getInputObjects(System.IO.Stream inputStream)
		{
			System.Collections.IList list = new System.Collections.ArrayList();
			
			System.IO.StreamReader br = new System.IO.StreamReader(new System.IO.StreamReader(inputStream, System.Text.Encoding.Default).BaseStream, new System.IO.StreamReader(inputStream, System.Text.Encoding.Default).CurrentEncoding);
			
			System.String line;
			while ((line = br.ReadLine()) != null)
			{
				if (line.Trim().Length == 0 || line.Trim().StartsWith(";"))
				{
					continue;
				}

                string[] tokens = line.Split(new string[] { "(", ")", " " }, StringSplitOptions.RemoveEmptyEntries);
				System.String type = tokens[0];
				
				if ("guest".Equals(type))
				{
					if (!"name".Equals(tokens[1]))
					{
						throw new System.IO.IOException("expected 'name' in: " + line);
					}
					System.String name = tokens[2];
                    if (!"sex".Equals(tokens[3]))
					{
						throw new System.IO.IOException("expected 'sex' in: " + line);
					}
                    System.String sex = tokens[4];
                    if (!"hobby".Equals(tokens[5]))
					{
						throw new System.IO.IOException("expected 'hobby' in: " + line);
					}
                    System.String hobby = tokens[6];
					
					Guest guest = new Guest(name, Sex.resolve(sex), Hobby.resolve(hobby));
					
					list.Add(guest);
				}
				
				if ("last_seat".Equals(type))
				{
                    if (!"seat".Equals(tokens[1]))
					{
						throw new System.IO.IOException("expected 'seat' in: " + line);
					}
                    list.Add(new LastSeat(System.Int32.Parse(tokens[2])));
				}
				
				if ("context".Equals(type))
				{
                    if (!"state".Equals(tokens[1]))
					{
						throw new System.IO.IOException("expected 'state' in: " + line);
					}
                    list.Add(new Context(tokens[2]));
				}
			}
			inputStream.Close();
			
			return list;
		}
		
		private System.IO.Stream generateData()
		{
		    int numGuests = 16;
		    int numSeats = 16;
		    int minHobbies = 2;
		    int maxHobbies = 3;
		
			System.String LINE_SEPARATOR = System.Environment.NewLine;
			
			System.IO.StringWriter writer = new System.IO.StringWriter();
			
			int maxMale = numGuests / 2;
			int maxFemale = numGuests / 2;
			
			int maleCount = 0;
			int femaleCount = 0;
			
			// init hobbies
			System.Collections.IList hobbyList = new System.Collections.ArrayList();
			for (int i = 1; i <= maxHobbies; i++)
			{
				hobbyList.Add("h" + i);
			}
			
			System.Random rnd = new System.Random();
			for (int i = 1; i <= numGuests; i++)
			{
				//UPGRADE_ISSUE: Method 'java.util.Random.nextBoolean' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1000_javautilRandomnextBoolean'"
				char sex = (rnd.Next(2) == 0)?'m':'f';
				if (sex == 'm' && maleCount == maxMale)
				{
					sex = 'f';
				}
				if (sex == 'f' && femaleCount == maxFemale)
				{
					sex = 'm';
				}
				if (sex == 'm')
				{
					maleCount++;
				}
				if (sex == 'f')
				{
					femaleCount++;
				}
				
				System.Collections.IList guestHobbies = new System.Collections.ArrayList(hobbyList);
				
				int numHobbies = minHobbies + rnd.Next(maxHobbies - minHobbies + 1);
				for (int j = 0; j < numHobbies; j++)
				{
					int hobbyIndex = rnd.Next(guestHobbies.Count);
					System.String hobby = (System.String) guestHobbies[hobbyIndex];
					writer.Write("(guest (name n" + i + ") (sex " + sex + ") (hobby " + hobby + "))" + LINE_SEPARATOR);
					guestHobbies.RemoveAt(hobbyIndex);
				}
			}
			writer.Write("(last_seat (seat " + numSeats + "))" + LINE_SEPARATOR);
			
			writer.Write(LINE_SEPARATOR);
			writer.Write("(context (state start))" + LINE_SEPARATOR);

            return new System.IO.MemoryStream(new UnicodeEncoding().GetBytes(writer.GetStringBuilder().ToString())); //(writer.GetStringBuilder().ToString()).ToCharArray()
		}
	}
}