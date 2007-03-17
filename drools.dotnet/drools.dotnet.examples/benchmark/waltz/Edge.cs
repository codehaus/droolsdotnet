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
	
	public class Edge
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
		public bool Joined
		{
			get
			{
				return this.joined;
			}
			
			set
			{
				this.joined = value;
			}
			
		}
		public string Label
		{
			get
			{
				return this.label;
			}
			
			set
			{
				this.label = value;
			}
			
		}
		public bool Plotted
		{
			get
			{
				return this.plotted;
			}
			
			set
			{
				this.plotted = value;
			}
			
		}
		private int p1;
		
		private int p2;
		
		private bool joined;
		
		private string label;
		
		private bool plotted;
		
		public const string NIL = "empty";
		
		public const string B = "B";
		
		public const string PLUS = "+";
		
		public const string MINUS = "-";
		
		public Edge(int p1, int p2, bool joined, string label, bool plotted)
		{
			this.p1 = p1;
			this.p2 = p2;
			this.joined = joined;
			this.label = label;
			this.plotted = plotted;
		}
		
		public string ToString()
		{
			return "( Edge p1=" + this.p1 + ", p2=" + this.p2 + ", joined=" + this.joined + ", label=" + this.label + ", plotted=" + this.plotted + " )";
		}
	}
}