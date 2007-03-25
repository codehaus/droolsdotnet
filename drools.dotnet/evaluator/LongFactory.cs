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
	
	public class LongFactory
	{
		public static Evaluator getLongEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return LongEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return LongNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return LongLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return LongLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return LongGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return LongGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for LongEvaluator");
				
			}
		}
		
		internal class LongEqualEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = 952936509469163071L;
			public static readonly Evaluator INSTANCE = new LongEqualEvaluator();
			
			internal LongEqualEvaluator():base(Evaluator.__Fields.LONG_TYPE, Evaluator.__Fields.EQUAL)
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
				return "Long ==";
			}
		}
		
		internal class LongNotEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 6007396753250538232L;
			public static readonly Evaluator INSTANCE = new LongNotEqualEvaluator();
			
			internal LongNotEqualEvaluator():base(Evaluator.__Fields.LONG_TYPE, Evaluator.__Fields.NOT_EQUAL)
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
				return "Long !=";
			}
		}
		
		internal class LongLessEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 4364068749553989563L;
			public static readonly Evaluator INSTANCE = new LongLessEvaluator();
			
			internal LongLessEvaluator():base(Evaluator.__Fields.LONG_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt64(((System.ValueType) object1)) < System.Convert.ToInt64(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Long <";
			}
		}
		
		internal class LongLessOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 6047195686511631405L;
			public static readonly Evaluator INSTANCE = new LongLessOrEqualEvaluator();
			
			internal LongLessOrEqualEvaluator():base(Evaluator.__Fields.LONG_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt64(((System.ValueType) object1)) <= System.Convert.ToInt64(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Long <=";
			}
		}
		
		internal class LongGreaterEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 6931144987779205475L;
			public static readonly Evaluator INSTANCE = new LongGreaterEvaluator();
			
			internal LongGreaterEvaluator():base(Evaluator.__Fields.LONG_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt64(((System.ValueType) object1)) > System.Convert.ToInt64(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Long >";
			}
		}
		
		internal class LongGreaterOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 4533604086739451238L;
			public static readonly Evaluator INSTANCE = new LongGreaterOrEqualEvaluator();
			
			internal LongGreaterOrEqualEvaluator():base(Evaluator.__Fields.LONG_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt64(((System.ValueType) object1)) >= System.Convert.ToInt64(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Long >=";
			}
		}
	}
}