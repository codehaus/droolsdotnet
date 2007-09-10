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
	
	public class FloatFactory
	{
		
		public static Evaluator getFloatEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return FloatEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return FloatNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return FloatLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return FloatLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return FloatGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return FloatGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for FloatEvaluator");
				
			}
		}
		
		internal class FloatEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 3295563005669423883L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new FloatEqualEvaluator();
			
			internal FloatEqualEvaluator():base(Evaluator.FLOAT_TYPE, Evaluator.EQUAL)
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
				return "Float ==";
			}
		}
		
		internal class FloatNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 4852271063945330337L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new FloatNotEqualEvaluator();
			
			internal FloatNotEqualEvaluator():base(Evaluator.FLOAT_TYPE, Evaluator.NOT_EQUAL)
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
				return "Float !=";
			}
		}
		
		internal class FloatLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 4971007931169565583L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new FloatLessEvaluator();
			
			internal FloatLessEvaluator():base(Evaluator.FLOAT_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = 8475866839302691518L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new FloatLessOrEqualEvaluator();
			
			internal FloatLessOrEqualEvaluator():base(Evaluator.FLOAT_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = 7121251641514162807L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new FloatGreaterEvaluator();
			
			internal FloatGreaterEvaluator():base(Evaluator.FLOAT_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = - 6885383763349349798L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new FloatGreaterOrEqualEvaluator();
			
			internal FloatGreaterOrEqualEvaluator():base(Evaluator.FLOAT_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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