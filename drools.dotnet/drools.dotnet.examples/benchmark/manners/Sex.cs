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
	
	
	
	public sealed class Sex
	{
		public static readonly Sex M = new Sex(0);
		public static readonly Sex F = new Sex(1);
		
		public const System.String stringM = "m";
		public const System.String stringF = "f";
		public static readonly System.String[] sexList = new System.String[]{stringM, stringF};
		
		private int sex;
		
		private Sex(int sex)
		{
			this.sex = sex;
		}
		
		public System.String getSex()
		{
			return sexList[this.sex];
		}
		
		public static Sex resolve(System.String sex)
		{
			if (stringM.Equals(sex))
			{
				return Sex.M;
			}
			else if (stringF.Equals(sex))
			{
				return Sex.F;
			}
			else
			{
				throw new System.SystemException("Sex '" + sex + "' does not exist for Sex Enum");
			}
		}
		
		public override System.String ToString()
		{
			return getSex();
		}
		
		public  override bool Equals(System.Object object_Renamed)
		{
			return this == object_Renamed;
		}
		
		public override int GetHashCode()
		{
			return this.sex;
		}
	}
}