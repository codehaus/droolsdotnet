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
	
	public class ByteFactory
	{
		
		public static Evaluator getByteEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return ByteEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return ByteNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return ByteLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return ByteLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return ByteGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return ByteGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ByteEvaluator");
				
			}
		}
		
		internal class ByteEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 2123381297852695049L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ByteEqualEvaluator();
			
			internal ByteEqualEvaluator():base(Evaluator.BYTE_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return ((System.SByte) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Byte ==";
			}
		}
		
		internal class ByteNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 1745720793613936221L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ByteNotEqualEvaluator();
			
			internal ByteNotEqualEvaluator():base(Evaluator.BYTE_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				return !((System.SByte) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Byte !=";
			}
		}
		
		internal class ByteLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 7327200711399789849L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ByteLessEvaluator();
			
			internal ByteLessEvaluator():base(Evaluator.BYTE_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return (sbyte) ((System.SByte) object1) < (sbyte) ((System.SByte) object2);
			}
			
			public virtual System.String toString()
			{
				return "Byte <";
			}
		}
		
		internal class ByteLessOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 5455645713080692170L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ByteLessOrEqualEvaluator();
			
			internal ByteLessOrEqualEvaluator():base(Evaluator.BYTE_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return (sbyte) ((System.SByte) object1) <= (sbyte) ((System.SByte) object2);
			}
			
			public virtual System.String toString()
			{
				return "Byte <=";
			}
		}
		
		internal class ByteGreaterEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 3319688501086570921L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ByteGreaterEvaluator();
			
			internal ByteGreaterEvaluator():base(Evaluator.BYTE_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return (sbyte) ((System.SByte) object1) > (sbyte) ((System.SByte) object2);
			}
			
			public virtual System.String toString()
			{
				return "Byte >";
			}
		}
		
		internal class ByteGreaterOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 8173066470532237341L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new ByteGreaterOrEqualEvaluator();
			
			internal ByteGreaterOrEqualEvaluator():base(Evaluator.BYTE_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return (sbyte) ((System.SByte) object1) >= (sbyte) ((System.SByte) object2);
			}
			
			public virtual System.String toString()
			{
				return "Byte >=";
			}
		}
	}
}