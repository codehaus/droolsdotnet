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
	
	public class IntegerFactory
	{
		public static Evaluator getIntegerEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return IntegerEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return IntegerNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return IntegerLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return IntegerLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return IntegerGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return IntegerGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for IntegerEvaluator");
				
			}
		}
		
		internal class IntegerEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 7723739052946963265L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new IntegerEqualEvaluator();
			
			internal IntegerEqualEvaluator():base(Evaluator.INTEGER_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = - 9113145485945747879L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new IntegerNotEqualEvaluator();
			
			internal IntegerNotEqualEvaluator():base(Evaluator.INTEGER_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = 4190533166100633474L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new IntegerLessEvaluator();
			
			internal IntegerLessEvaluator():base(Evaluator.INTEGER_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = - 4044888400673214480L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new IntegerLessOrEqualEvaluator();
			
			internal IntegerLessOrEqualEvaluator():base(Evaluator.INTEGER_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = - 5347620757145017588L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new IntegerGreaterEvaluator();
			
			internal IntegerGreaterEvaluator():base(Evaluator.INTEGER_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = 7520187005496650583L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new IntegerGreaterOrEqualEvaluator();
			
			internal IntegerGreaterOrEqualEvaluator():base(Evaluator.INTEGER_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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