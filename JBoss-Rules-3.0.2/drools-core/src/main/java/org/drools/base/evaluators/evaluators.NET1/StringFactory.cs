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
	
	/// <summary> This is the misc "bucket" evaluator factory for objects.
	/// It is fairly limited in operations, 
	/// and what operations are available are dependent on the exact type.
	/// 
	/// </summary>
	/// <author>  Michael Neale
	/// </author>
	public class StringFactory
	{
		
		public static Evaluator getStringEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return StringEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return StringNotEqualEvaluator.INSTANCE;
				
				case Evaluator.MATCHES: 
					return StringMatchesEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for StringEvaluator");
				
			}
		}
		
		internal class StringEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 5282693491345148054L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new StringEqualEvaluator();
			
			internal StringEqualEvaluator():base(Evaluator.STRING_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "String ==";
			}
		}
		
		internal class StringNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 3385245390840913608L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new StringNotEqualEvaluator();
			
			internal StringNotEqualEvaluator():base(Evaluator.STRING_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return !(object2 == null);
				}
				
				return !object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "String !=";
			}
		}
		
		internal class StringMatchesEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 5934192092501066510L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new StringMatchesEvaluator();
			
			internal StringMatchesEvaluator():base(Evaluator.STRING_TYPE, Evaluator.MATCHES)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'pattern '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.String pattern = (System.String) object2;
				//UPGRADE_NOTE: Final was removed from the declaration of 'target '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.String target = (System.String) object1;
				
				if (object1 == null)
				{
					return false;
				}
				
				//TODO: possibly use a WeakHashMap cache of regex expressions
				//downside is could cause a lot of hashing if the patterns are dynamic
				//if the patterns are static, then it will not be a problem. Perhaps compiler can recognise patterns
				//in the input string using /pattern/ etc.. and precompile it, in which case object2 will be a Pattern.
				return target.matches(pattern);
			}
			
			public virtual System.String toString()
			{
				return "String !=";
			}
		}
	}
}