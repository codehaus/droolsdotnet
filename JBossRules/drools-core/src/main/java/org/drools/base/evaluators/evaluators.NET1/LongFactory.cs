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
	
	public class LongFactory
	{
		public static Evaluator getLongEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return LongEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return LongNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return LongLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return LongLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return LongGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return LongGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for LongEvaluator");
				
			}
		}
		
		internal class LongEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 952936509469163071L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new LongEqualEvaluator();
			
			internal LongEqualEvaluator():base(Evaluator.LONG_TYPE, Evaluator.EQUAL)
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
				return "Long ==";
			}
		}
		
		internal class LongNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 6007396753250538232L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new LongNotEqualEvaluator();
			
			internal LongNotEqualEvaluator():base(Evaluator.LONG_TYPE, Evaluator.NOT_EQUAL)
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
				return "Long !=";
			}
		}
		
		internal class LongLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 4364068749553989563L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new LongLessEvaluator();
			
			internal LongLessEvaluator():base(Evaluator.LONG_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = - 6047195686511631405L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new LongLessOrEqualEvaluator();
			
			internal LongLessOrEqualEvaluator():base(Evaluator.LONG_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = - 6931144987779205475L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new LongGreaterEvaluator();
			
			internal LongGreaterEvaluator():base(Evaluator.LONG_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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
			/// <summary> </summary>
			private const long serialVersionUID = 4533604086739451238L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new LongGreaterOrEqualEvaluator();
			
			internal LongGreaterOrEqualEvaluator():base(Evaluator.LONG_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
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