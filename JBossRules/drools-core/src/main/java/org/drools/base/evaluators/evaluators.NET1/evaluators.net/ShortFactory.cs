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
	
	public class ShortFactory
	{
		public static Evaluator getShortEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return ShortEqualEvaluator.Instance;
				
				case Evaluator.NOT_EQUAL: 
					return ShortNotEqualEvaluator.Instance;
				
				case Evaluator.LESS: 
					return ShortLessEvaluator.Instance;
				
				case Evaluator.LESS_OR_EQUAL: 
					return ShortLessOrEqualEvaluator.Instance;
				
				case Evaluator.GREATER: 
					return ShortGreaterEvaluator.Instance;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return ShortGreaterOrEqualEvaluator.Instance;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ShortEvaluator");
				
			}
		}
		
		internal class ShortEqualEvaluator:BaseEvaluator
		{
			public static Evaluator Instance
			{
				get
				{
					if (org.drools.base_Renamed.evaluators.ShortFactory.ShortEqualEvaluator.INSTANCE == null)
					{
						org.drools.base_Renamed.evaluators.ShortFactory.ShortEqualEvaluator.INSTANCE = new ShortEqualEvaluator();
					}
					return org.drools.base_Renamed.evaluators.ShortFactory.ShortEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = 8933390138182317179L;
			private static Evaluator INSTANCE;
			
			internal ShortEqualEvaluator():base(Evaluator.SHORT_TYPE, Evaluator.EQUAL)
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
				return "Short ==";
			}
		}
		
		internal class ShortNotEqualEvaluator:BaseEvaluator
		{
			public static Evaluator Instance
			{
				get
				{
					if (org.drools.base_Renamed.evaluators.ShortFactory.ShortNotEqualEvaluator.INSTANCE == null)
					{
						org.drools.base_Renamed.evaluators.ShortFactory.ShortNotEqualEvaluator.INSTANCE = new ShortNotEqualEvaluator();
					}
					return org.drools.base_Renamed.evaluators.ShortFactory.ShortNotEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 273350270376804828L;
			private static Evaluator INSTANCE;
			
			internal ShortNotEqualEvaluator():base(Evaluator.SHORT_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return !(object2 == null);
				}
				return !((System.ValueType) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Short !=";
			}
		}
		
		internal class ShortLessEvaluator:BaseEvaluator
		{
			public static Evaluator Instance
			{
				get
				{
					if (org.drools.base_Renamed.evaluators.ShortFactory.ShortLessEvaluator.INSTANCE == null)
					{
						org.drools.base_Renamed.evaluators.ShortFactory.ShortLessEvaluator.INSTANCE = new ShortLessEvaluator();
					}
					return org.drools.base_Renamed.evaluators.ShortFactory.ShortLessEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 1562867187426899162L;
			private static Evaluator INSTANCE;
			
			internal ShortLessEvaluator():base(Evaluator.SHORT_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt16(((System.ValueType) object1)) < System.Convert.ToInt16(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Short <";
			}
		}
		
		internal class ShortLessOrEqualEvaluator:BaseEvaluator
		{
			public static Evaluator Instance
			{
				get
				{
					if (org.drools.base_Renamed.evaluators.ShortFactory.ShortLessOrEqualEvaluator.INSTANCE == null)
					{
						org.drools.base_Renamed.evaluators.ShortFactory.ShortLessOrEqualEvaluator.INSTANCE = new ShortLessOrEqualEvaluator();
					}
					return org.drools.base_Renamed.evaluators.ShortFactory.ShortLessOrEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 1541816846266081605L;
			private static Evaluator INSTANCE;
			
			internal ShortLessOrEqualEvaluator():base(Evaluator.SHORT_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt16(((System.ValueType) object1)) <= System.Convert.ToInt16(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Boolean <=";
			}
		}
		
		internal class ShortGreaterEvaluator:BaseEvaluator
		{
			public static Evaluator Instance
			{
				get
				{
					if (org.drools.base_Renamed.evaluators.ShortFactory.ShortGreaterEvaluator.INSTANCE == null)
					{
						org.drools.base_Renamed.evaluators.ShortFactory.ShortGreaterEvaluator.INSTANCE = new ShortGreaterEvaluator();
					}
					return org.drools.base_Renamed.evaluators.ShortFactory.ShortGreaterEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 3260955087091852509L;
			private static Evaluator INSTANCE;
			
			internal ShortGreaterEvaluator():base(Evaluator.SHORT_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt16(((System.ValueType) object1)) > System.Convert.ToInt16(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Short >";
			}
		}
		
		internal class ShortGreaterOrEqualEvaluator:BaseEvaluator
		{
			public static Evaluator Instance
			{
				get
				{
					if (org.drools.base_Renamed.evaluators.ShortFactory.ShortGreaterOrEqualEvaluator.INSTANCE == null)
					{
						org.drools.base_Renamed.evaluators.ShortFactory.ShortGreaterOrEqualEvaluator.INSTANCE = new ShortGreaterOrEqualEvaluator();
					}
					return org.drools.base_Renamed.evaluators.ShortFactory.ShortGreaterOrEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = 1254418853497580320L;
			private static Evaluator INSTANCE;
			
			internal ShortGreaterOrEqualEvaluator():base(Evaluator.SHORT_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return System.Convert.ToInt16(((System.ValueType) object1)) >= System.Convert.ToInt16(((System.ValueType) object2));
			}
			
			public virtual System.String toString()
			{
				return "Short >=";
			}
		}
	}
}