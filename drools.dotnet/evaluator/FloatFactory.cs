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
	
	public class FloatFactory
	{
		
		public static Evaluator getFloatEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return FloatEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return FloatNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return FloatLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return FloatLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return FloatGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return FloatGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for FloatEvaluator");
				
			}
		}
		
		internal class FloatEqualEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = - 3295563005669423883L;
			public static readonly Evaluator INSTANCE = new FloatEqualEvaluator();
			
			internal FloatEqualEvaluator():base(Evaluator.__Fields.FLOAT_TYPE, Evaluator.__Fields.EQUAL)
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
				return "Float ==";
			}
		}
		
		internal class FloatNotEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 4852271063945330337L;
			public static readonly Evaluator INSTANCE = new FloatNotEqualEvaluator();
			
			internal FloatNotEqualEvaluator():base(Evaluator.__Fields.FLOAT_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				return !((System.ValueType) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Float !=";
			}
		}
		
		internal class FloatLessEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 4971007931169565583L;
			public static readonly Evaluator INSTANCE = new FloatLessEvaluator();
			
			internal FloatLessEvaluator():base(Evaluator.__Fields.FLOAT_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToSingle(((System.ValueType) object1)) < System.Convert.ToSingle(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Float <";
			}
		}
		
		internal class FloatLessOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 8475866839302691518L;
			public static readonly Evaluator INSTANCE = new FloatLessOrEqualEvaluator();
			
			internal FloatLessOrEqualEvaluator():base(Evaluator.__Fields.FLOAT_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToSingle(((System.ValueType) object1)) <= System.Convert.ToSingle(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Float <=";
			}
		}
		
		internal class FloatGreaterEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 7121251641514162807L;
			public static readonly Evaluator INSTANCE = new FloatGreaterEvaluator();
			
			internal FloatGreaterEvaluator():base(Evaluator.__Fields.FLOAT_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToSingle(((System.ValueType) object1)) > System.Convert.ToSingle(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Float >";
			}
		}
		
		internal class FloatGreaterOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 6885383763349349798L;
			public static readonly Evaluator INSTANCE = new FloatGreaterOrEqualEvaluator();
			
			internal FloatGreaterOrEqualEvaluator():base(Evaluator.__Fields.FLOAT_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToSingle(((System.ValueType) object1)) >= System.Convert.ToSingle(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Float >=";
			}
		}
	}
}