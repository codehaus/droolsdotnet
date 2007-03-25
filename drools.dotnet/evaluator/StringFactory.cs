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
using BaseEvaluator = org.drools.@base.BaseEvaluator;
using Evaluator = org.drools.spi.Evaluator;
namespace org.drools.dotnet.evaluator
{
	public class StringFactory
	{
		
		public static Evaluator getStringEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return StringEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return StringNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.MATCHES: 
					return StringMatchesEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for StringEvaluator");
				
			}
		}
		
		internal class StringEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 5282693491345148054L;
			public static readonly Evaluator INSTANCE = new StringEqualEvaluator();
			
			internal StringEqualEvaluator():base(Evaluator.__Fields.STRING_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "String ==";
			}
		}
		
		internal class StringNotEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 3385245390840913608L;
			public static readonly Evaluator INSTANCE = new StringNotEqualEvaluator();
			
			internal StringNotEqualEvaluator():base(Evaluator.__Fields.STRING_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return !(object2 == null);
				}
				
				return !object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "String !=";
			}
		}
		
		internal class StringMatchesEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 5934192092501066510L;
			public static readonly Evaluator INSTANCE = new StringMatchesEvaluator();
			
			internal StringMatchesEvaluator():base(Evaluator.__Fields.STRING_TYPE, Evaluator.__Fields.MATCHES)
			{
			}
			
			public override bool evaluate(object object1, object object2)
			{
				System.String pattern = (System.String) object2;
				System.String target = (System.String) object1;
				
				if (object1 == null)
				{
					return false;
				}
				
				//TODO: possibly use a WeakHashMap cache of regex expressions
				//downside is could cause a lot of hashing if the patterns are dynamic
				//if the patterns are static, then it will not be a problem. Perhaps compiler can recognise patterns
				//in the input string using /pattern/ etc.. and precompile it, in which case object2 will be a Pattern.
                return target.Equals(pattern); // target.matches(pattern);
			}
			
			public virtual System.String toString()
			{
				return "String !=";
			}
		}
	}
}