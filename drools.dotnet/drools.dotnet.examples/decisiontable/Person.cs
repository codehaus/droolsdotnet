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
using System.Collections;
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
	
	public class Person
	{
		virtual public System.String Status
		{
			get
			{
				return this.status;
			}
			
			set
			{
				this.status = value;
			}
			
		}
		virtual public System.String Likes
		{
			get
			{
				return this.likes;
			}
			
		}
		virtual public System.String Name
		{
			get
			{
				return this.name;
			}
			
		}
		virtual public int Age
		{
			get
			{
				return this.age;
			}
			
		}
		virtual public bool Alive
		{
			get
			{
				return this.alive;
			}
			
			set
			{
				this.alive = value;
			}
			
		}
		virtual public char Sex
		{
			get
			{
				return this.sex;
			}
			
			set
			{
				this.sex = value;
			}
			
		}
		private System.String name;
		private System.String likes;
		private int age;
		
		private char sex;
		
		private bool alive;
		
		private System.String status;
		
		public Person(System.String name):this(name, "", 0)
		{
		}
		
		public Person(System.String name, System.String likes):this(name, likes, 0)
		{
		}
		
		public Person(System.String name, System.String likes, int age)
		{
			this.name = name;
			this.likes = likes;
			this.age = age;
            
            
		}
		
		public override System.String ToString()
		{
			return "[Person name='" + this.name + "']";
		}
	}
}