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
using System;
//UPGRADE_TODO: The type 'org.drools.base_Renamed.BaseEvaluator' could not be found. If it was not included in the conversion, there may be compiler issues. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1262'"
using BaseEvaluator = org.drools.base_Renamed.BaseEvaluator;
//UPGRADE_TODO: The type 'org.drools.spi.Evaluator' could not be found. If it was not included in the conversion, there may be compiler issues. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1262'"
using Evaluator = org.drools.spi.Evaluator;
namespace org.drools.base_Renamed.evaluators
{
	
	/// <summary> For handling simple (non collection) array types.</summary>
	/// <author>  Michael Neale
	/// </author>
	public class ArrayFactory
	{
		
		public static Evaluator getArrayEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return ArrayEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return ArrayNotEqualEvaluator.INSTANCE;
				
				case Evaluator.CONTAINS: 
					return ArrayContainsEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ArrayEvaluator");
				
			}
		}
		
		internal class ArrayEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 3988506265461766585L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ArrayEqualEvaluator();
			
			internal ArrayEqualEvaluator():base(Evaluator.ARRAY_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = 4021357517502692236L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ArrayNotEqualEvaluator();
			
			internal ArrayNotEqualEvaluator():base(Evaluator.ARRAY_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return !SupportClass.ArraySupport.Equals((System.Object[]) object1, (System.Object[]) object2);
			}
			
			public virtual System.String toString()
			{
				return "Object !=";
			}
		}
		
		internal class ArrayContainsEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 4541491453186891644L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ArrayContainsEvaluator();
			
			internal ArrayContainsEvaluator():base(Evaluator.ARRAY_TYPE, Evaluator.CONTAINS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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