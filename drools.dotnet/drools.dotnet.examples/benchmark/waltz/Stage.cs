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
using System;
namespace org.drools.dotnet.examples
{
	
	/// <summary> </summary>
	/// <author>  Alexander Bagerman
	/// 
	/// </author>
	[Serializable]
	public class Stage
	{
		 public int Value
		{
			get
			{
				return this.value_Renamed;
			}
			
			set
			{
				this.value_Renamed = value;
			}
			
		}
		private const long serialVersionUID = - 2143733895059840082L;
		
		public const int START = 0;
		
		public const int DUPLICATE = 1;
		
		public const int DETECT_JUNCTIONS = 2;
		
		public const int FIND_INITIAL_BOUNDARY = 3;
		
		public const int FIND_SECOND_BOUNDARY = 4;
		
		public const int LABELING = 5;
		
		public const int PLOT_REMAINING_EDGES = 9;
		
		public const int DONE = 10;
		
		private int value_Renamed;
		
		public Stage(int value_Renamed)
		{
			this.value_Renamed = value_Renamed;
		}
		
		public static int resolveStageValue(string str)
		{
			if (str.Equals("start"))
			{
				return 0;
			}
			else if (str.Equals("duplicate"))
			{
				return 1;
			}
			else if (str.Equals("detect_junctions"))
			{
				return 2;
			}
			else if (str.Equals("find_initial_boundary"))
			{
				return 3;
			}
			else if (str.Equals("find_second_boundary"))
			{
				return 4;
			}
			else if (str.Equals("labeling"))
			{
				return 5;
			}
			else if (str.Equals("plot_remaining_edges"))
			{
				return 9;
			}
			else if (str.Equals("done"))
			{
				return 10;
			}
			else
				return - 9999999;
		}
		
		public string ToString()
		{
			return "{Stage value=" + this.value_Renamed + "}";
		}
	}
}