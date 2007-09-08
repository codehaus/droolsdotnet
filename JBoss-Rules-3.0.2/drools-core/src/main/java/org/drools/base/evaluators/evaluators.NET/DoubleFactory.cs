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
	
	public class DoubleFactory
	{
		
		public static Evaluator getDoubleEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return DoubleEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return DoubleNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return DoubleLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return DoubleLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return DoubleGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return DoubleGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for DoubleEvaluator");
				
			}
		}
		
		internal class DoubleEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 8638265291388692160L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DoubleEqualEvaluator();
			
			internal DoubleEqualEvaluator():base(Evaluator.DOUBLE_TYPE, Evaluator.EQUAL)
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
				return "Double ==";
			}
		}
		
		internal class DoubleNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 1605810860861480665L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DoubleNotEqualEvaluator();
			
			internal DoubleNotEqualEvaluator():base(Evaluator.DOUBLE_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				return !((System.ValueType) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Double !=";
			}
		}
		
		internal class DoubleLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 6256590818217451743L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DoubleLessEvaluator();
			
			internal DoubleLessEvaluator():base(Evaluator.DOUBLE_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToDouble(((System.ValueType) object1)) < System.Convert.ToDouble(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Double <";
			}
		}
		
		internal class DoubleLessOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 3663317930533546094L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DoubleLessOrEqualEvaluator();
			
			internal DoubleLessOrEqualEvaluator():base(Evaluator.DOUBLE_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToDouble(((System.ValueType) object1)) <= System.Convert.ToDouble(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Double <=";
			}
		}
		
		internal class DoubleGreaterEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 1420842292058943594L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DoubleGreaterEvaluator();
			
			internal DoubleGreaterEvaluator():base(Evaluator.DOUBLE_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToDouble(((System.ValueType) object1)) > System.Convert.ToDouble(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Double >";
			}
		}
		
		internal class DoubleGreaterOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 468558955316190757L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new DoubleGreaterOrEqualEvaluator();
			
			internal DoubleGreaterOrEqualEvaluator():base(Evaluator.DOUBLE_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToDouble(((System.ValueType) object1)) >= System.Convert.ToDouble(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Double >=";
			}
		}
	}
}