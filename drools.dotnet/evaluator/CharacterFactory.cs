/** 
* Copyright (c) 2007, Ritu Jain, Chinmay Nagarkar and Sahi Technologies Pvt Ltd
* All rights reserved.
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the Sahi Technologies Pvt. Ltd./Esahi.com  nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
using System;
using BaseEvaluator = org.drools.@base.BaseEvaluator;
using Evaluator = org.drools.spi.Evaluator;
namespace org.drools.dotnet.evaluator
{
	
	public class CharacterFactory
	{
		
		public static Evaluator getCharacterEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return CharacterEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return CharacterNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return CharacterLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return CharacterLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return CharacterGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return CharacterGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for CharacterEvaluator");
				
			}
		}
		
		internal class CharacterEqualEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = 8766645269581805269L;
			public static readonly Evaluator INSTANCE = new CharacterEqualEvaluator();
			
			internal CharacterEqualEvaluator():base(Evaluator.__Fields.CHAR_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 8010152240062213440L;
			public static readonly Evaluator INSTANCE = new CharacterNotEqualEvaluator();
			
			internal CharacterNotEqualEvaluator():base(Evaluator.__Fields.CHAR_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 5236106171143422684L;
			public static readonly Evaluator INSTANCE = new CharacterLessEvaluator();
			
			internal CharacterLessEvaluator():base(Evaluator.__Fields.CHAR_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 8064001658173531244L;
			public static readonly Evaluator INSTANCE = new CharacterLessOrEqualEvaluator();
			
			internal CharacterLessOrEqualEvaluator():base(Evaluator.__Fields.CHAR_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 7622623046585316842L;
			public static readonly Evaluator INSTANCE = new CharacterGreaterEvaluator();
			
			internal CharacterGreaterEvaluator():base(Evaluator.__Fields.CHAR_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 8587935558617586015L;
			public static readonly Evaluator INSTANCE = new CharacterGreaterOrEqualEvaluator();
			
			internal CharacterGreaterOrEqualEvaluator():base(Evaluator.__Fields.CHAR_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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