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
	
	public class IntegerFactory
	{
		public static Evaluator getIntegerEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return IntegerEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return IntegerNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return IntegerLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return IntegerLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return IntegerGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return IntegerGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for IntegerEvaluator");
				
			}
		}
		
		internal class IntegerEqualEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = 7723739052946963265L;
			public static readonly Evaluator INSTANCE = new IntegerEqualEvaluator();
			
			internal IntegerEqualEvaluator():base(Evaluator.__Fields.INTEGER_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return ((System.ValueType) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Integer ==";
			}
		}
		
		internal class IntegerNotEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 9113145485945747879L;
			public static readonly Evaluator INSTANCE = new IntegerNotEqualEvaluator();
			
			internal IntegerNotEqualEvaluator():base(Evaluator.__Fields.INTEGER_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return (object2 != null);
				}
				return !((System.ValueType) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Integer !=";
			}
		}
		
		internal class IntegerLessEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 4190533166100633474L;
			public static readonly Evaluator INSTANCE = new IntegerLessEvaluator();
			
			internal IntegerLessEvaluator():base(Evaluator.__Fields.INTEGER_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt32(((System.ValueType) object1)) < System.Convert.ToInt32(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Integer <";
			}
		}
		
		internal class IntegerLessOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 4044888400673214480L;
			public static readonly Evaluator INSTANCE = new IntegerLessOrEqualEvaluator();
			
			internal IntegerLessOrEqualEvaluator():base(Evaluator.__Fields.INTEGER_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt32(((System.ValueType) object1)) <= System.Convert.ToInt32(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Integer <=";
			}
		}
		
		internal class IntegerGreaterEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 5347620757145017588L;
			public static readonly Evaluator INSTANCE = new IntegerGreaterEvaluator();
			
			internal IntegerGreaterEvaluator():base(Evaluator.__Fields.INTEGER_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt32(((System.ValueType) object1)) > System.Convert.ToInt32(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Integer >";
			}
		}
		
		internal class IntegerGreaterOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 7520187005496650583L;
			public static readonly Evaluator INSTANCE = new IntegerGreaterOrEqualEvaluator();
			
			internal IntegerGreaterOrEqualEvaluator():base(Evaluator.__Fields.INTEGER_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt32(((System.ValueType) object1)) >= System.Convert.ToInt32(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Integer >=";
			}
		}
	}
}