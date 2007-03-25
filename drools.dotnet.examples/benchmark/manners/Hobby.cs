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
namespace org.drools.dotnet.examples
{
	
	
	public class Hobby
	{
		public const System.String stringH1 = "h1";
		public const System.String stringH2 = "h2";
		public const System.String stringH3 = "h3";
		public const System.String stringH4 = "h4";
		public const System.String stringH5 = "h5";
		
		public static readonly System.String[] hobbyStrings = new System.String[]{stringH1, stringH2, stringH3, stringH4, stringH5};
		
		public static readonly Hobby H1 = new Hobby(1);
		public static readonly Hobby H2 = new Hobby(2);
		public static readonly Hobby H3 = new Hobby(3);
		public static readonly Hobby H4 = new Hobby(4);
		public static readonly Hobby H5 = new Hobby(5);
		
		private System.String hobbyStr;
		private int hobbyIndex;
		
		
		private Hobby(int hobby)
		{
			this.hobbyIndex = hobby - 1;
			this.hobbyStr = hobbyStrings[this.hobbyIndex];
		}
		
		public System.String getHobby()
		{
			return this.hobbyStr;
		}
		
		public static Hobby resolve(System.String hobby)
		{
			if (stringH1.Equals(hobby))
			{
				return Hobby.H1;
			}
			else if (stringH2.Equals(hobby))
			{
				return Hobby.H2;
			}
			else if (stringH3.Equals(hobby))
			{
				return Hobby.H3;
			}
			else if (stringH4.Equals(hobby))
			{
				return Hobby.H4;
			}
			else if (stringH5.Equals(hobby))
			{
				return Hobby.H5;
			}
			else
			{
				throw new System.SystemException("Hobby '" + hobby + "' does not exist for Hobby Enum");
			}
		}
		
		public override System.String ToString()
		{
			return getHobby();
		}
		
		public  override bool Equals(System.Object object_Renamed)
		{
			return (this == object_Renamed);
		}
		
		public override int GetHashCode()
		{
			return this.hobbyIndex;
		}
	}
}