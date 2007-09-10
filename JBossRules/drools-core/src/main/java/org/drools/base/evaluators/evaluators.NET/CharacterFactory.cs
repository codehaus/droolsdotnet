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
	
	public class CharacterFactory
	{
		
		public static Evaluator getCharacterEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return CharacterEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return CharacterNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return CharacterLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return CharacterLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return CharacterGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return CharacterGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for CharacterEvaluator");
				
			}
		}
		
		internal class CharacterEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 8766645269581805269L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new CharacterEqualEvaluator();
			
			internal CharacterEqualEvaluator():base(Evaluator.CHAR_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return ((System.Char) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Character ==";
			}
		}
		
		internal class CharacterNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 8010152240062213440L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new CharacterNotEqualEvaluator();
			
			internal CharacterNotEqualEvaluator():base(Evaluator.CHAR_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				return !((System.Char) object1).Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Character !=";
			}
		}
		
		internal class CharacterLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 5236106171143422684L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new CharacterLessEvaluator();
			
			internal CharacterLessEvaluator():base(Evaluator.CHAR_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return ((System.Char) object1) < ((System.Char) object2);
			}
			
			public virtual System.String toString()
			{
				return "Character <";
			}
		}
		
		internal class CharacterLessOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 8064001658173531244L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new CharacterLessOrEqualEvaluator();
			
			internal CharacterLessOrEqualEvaluator():base(Evaluator.CHAR_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return ((System.Char) object1) <= ((System.Char) object2);
			}
			
			public virtual System.String toString()
			{
				return "Character <=";
			}
		}
		
		internal class CharacterGreaterEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 7622623046585316842L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new CharacterGreaterEvaluator();
			
			internal CharacterGreaterEvaluator():base(Evaluator.CHAR_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return ((System.Char) object1) > ((System.Char) object2);
			}
			
			public virtual System.String toString()
			{
				return "Character >";
			}
		}
		
		internal class CharacterGreaterOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 8587935558617586015L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new CharacterGreaterOrEqualEvaluator();
			
			internal CharacterGreaterOrEqualEvaluator():base(Evaluator.CHAR_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				return ((System.Char) object1) >= ((System.Char) object2);
			}
			
			public virtual System.String toString()
			{
				return "Character >=";
			}
		}
	}
}