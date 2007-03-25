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
using org.drools.@base;

namespace org.drools.dotnet.evaluator
{
	
	public class ArrayFactory
	{
		
		public static Evaluator getArrayEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return ArrayEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return ArrayNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.CONTAINS: 
					return ArrayContainsEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ArrayEvaluator");
				
			}
		}
		
		internal class ArrayEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 3988506265461766585L;
			public static readonly Evaluator INSTANCE = new ArrayEqualEvaluator();
			
			internal ArrayEqualEvaluator():base(Evaluator.__Fields.ARRAY_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Array ==";
			}
		}
		
		internal class ArrayNotEqualEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = 4021357517502692236L;
			public static readonly Evaluator INSTANCE = new ArrayNotEqualEvaluator();
			
			internal ArrayNotEqualEvaluator():base(Evaluator.__Fields.ARRAY_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				return EvaluatorUtil.ArrayEquals((System.Object[]) object1, (System.Object[]) object2);
			}
			
			public virtual System.String toString()
			{
				return "Object !=";
			}
		}
		
		internal class ArrayContainsEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = - 4541491453186891644L;
			public static readonly Evaluator INSTANCE = new ArrayContainsEvaluator();
			
			internal ArrayContainsEvaluator():base(Evaluator.__Fields.ARRAY_TYPE, Evaluator.__Fields.CONTAINS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object2 == null)
				{
					return false;
				}
				if (System.Array.BinarySearch((System.Object[]) object1, object2) == - 1)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			
			public virtual System.String toString()
			{
				return "Array contains";
			}
		}
	}
}