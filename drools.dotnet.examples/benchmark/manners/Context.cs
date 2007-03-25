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
	public sealed class Context
	{
		public System.String StringValue
		{
			get
			{
				return stateStrings[this.state];
			}
			
		}

        public int State
        {
            get
            {
                return this.state;
            }
            set
            {
                this.state = value;
            }
        }
		
		public const int START_UP = 0;
		public const int ASSIGN_SEATS = 1;
		public const int MAKE_PATH = 2;
		public const int CHECK_DONE = 3;
		public const int PRINT_RESULTS = 4;
		
		public static readonly System.String[] stateStrings = new System.String[]{"START_UP", "ASSIGN_SEATS", "MAKE_PATH", "CHECK_DONE", "PRINT_RESULTS"};
		
		private int state;
		
		public Context(System.String state)
		{
			if ("start".Equals(state))
			{
				this.state = Context.START_UP;
			}
			else
			{
				throw new System.SystemException("Context '" + state + "' does not exist for Context Enum");
			}
		}
		
		public Context(int state)
		{
			this.state = state;
		}
		
		
		public bool isState(int state)
		{
			return this.state == state;
		}
		
		
		
		public override System.String ToString()
		{
			return "[Context state=" + StringValue + "]";
		}
	}
}