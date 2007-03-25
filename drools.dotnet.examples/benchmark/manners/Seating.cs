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
	
	[Serializable]
	public sealed class Seating
	{
		public bool PathDone
		{
			get
			{
				return this.pathDone;
			}
			
			set
			{
				this.pathDone = value;
			}
			
		}
		public int Id
		{
			get
			{
				return this.id;
			}
			
		}
		public System.String LeftGuestName
		{
			get
			{
				return this.leftGuestName;
			}
			
		}
		public int LeftSeat
		{
			get
			{
				return this.leftSeat;
			}
			
		}
		public int Pid
		{
			get
			{
				return this.pid;
			}
			
		}
		public System.String RightGuestName
		{
			get
			{
				return this.rightGuestName;
			}
			
		}
		public int RightSeat
		{
			get
			{
				return this.rightSeat;
			}
			
		}
		private int id;
		private int pid;
		
		private int leftSeat;
		private int rightSeat;
		
		private System.String leftGuestName;
		private System.String rightGuestName;
		
		private bool pathDone;
		
		public Seating(int id, int pid, bool pathDone, int leftSeat, System.String leftGuestName, int rightSeat, System.String rightGuestName):base()
		{
			this.id = id;
			this.pid = pid;
			this.pathDone = pathDone;
			this.leftSeat = leftSeat;
			this.leftGuestName = leftGuestName;
			this.rightSeat = rightSeat;
			this.rightGuestName = rightGuestName;
		}
		
		public override System.String ToString()
		{
			return "[Seating id=" + this.id + " , pid=" + this.pid + " , pathDone=" + this.pathDone + " , leftSeat=" + this.leftSeat + ", leftGuestName=" + this.leftGuestName + ", rightSeat=" + this.rightSeat + ", rightGuestName=" + this.rightGuestName + "]";
		}
	}
}