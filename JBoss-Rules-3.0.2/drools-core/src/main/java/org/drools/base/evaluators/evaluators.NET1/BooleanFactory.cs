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
	
	public class BooleanFactory
	{
		
		public static Evaluator getBooleanEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return BooleanEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return BooleanNotEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for BooleanEvaluator");
				
			}
		}
		
		internal class BooleanEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 7891019213259874134L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new BooleanEqualEvaluator();
			
			internal BooleanEqualEvaluator():base(Evaluator.BOOLEAN_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return ((System.Boolean) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Boolean ==";
			}
		}
		
		internal class BooleanNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 451325761056297938L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new BooleanNotEqualEvaluator();
			
			internal BooleanNotEqualEvaluator():base(Evaluator.BOOLEAN_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				return !((System.Boolean) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Boolean !=";
			}
		}
	}
}