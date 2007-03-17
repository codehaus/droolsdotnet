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
	/*
	* Copyright 2005 JBoss Inc
	* 
	* Licensed under the Apache License, Version 2.0 (the "License");
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	*      http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	*/
	
	
	
	
	
	
	/// <author>  Alexander Bagerman
	/// 
	/// </author>
	
	public class Junction
	{
		public int P1
		{
			get
			{
				return this.p1;
			}
			
			set
			{
				this.p1 = value;
			}
			
		}
		public int P2
		{
			get
			{
				return this.p2;
			}
			
			set
			{
				this.p2 = value;
			}
			
		}
		public int BasePoint
		{
			get
			{
				return this.basePoint;
			}
			
			set
			{
				this.basePoint = value;
			}
			
		}
		public int P3
		{
			get
			{
				return this.p3;
			}
			
			set
			{
				this.p3 = value;
			}
			
		}
		public string Type
		{
			get
			{
				return this.type;
			}
			
			set
			{
				this.type = value;
			}
			
		}
		public static string TEE = "tee";
		
		public static string FORK = "fork";

        public static string ARROW = "arrow";

        public static string L = "L";
		
		private int p1;
		
		private int p2;
		
		private int p3;
		
		private int basePoint;
		
		private string type;
		
		public Junction(int p1, int p2, int p3, int basePoint, string type)
		{
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			this.basePoint = basePoint;
			this.type = type;
		}
		
		public string ToString()
		{
			return "{Junction p1=" + this.p1 + ", p2=" + this.p2 + ", p3=" + this.p3 + ", basePoint=" + this.basePoint + ", type=" + this.type + "}";
		}
	}
}