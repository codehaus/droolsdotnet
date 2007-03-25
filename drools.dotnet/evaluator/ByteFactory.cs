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
	
	public class ByteFactory
	{
		
		public static Evaluator getByteEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return ByteEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return ByteNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return ByteLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return ByteLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return ByteGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return ByteGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ByteEvaluator");
				
			}
		}
		
		internal class ByteEqualEvaluator:BaseEvaluator
		{
			
			private const long serialVersionUID = - 2123381297852695049L;
			public static readonly Evaluator INSTANCE = new ByteEqualEvaluator();
			
			internal ByteEqualEvaluator():base(Evaluator.__Fields.BYTE_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 1745720793613936221L;
			public static readonly Evaluator INSTANCE = new ByteNotEqualEvaluator();
			
			internal ByteNotEqualEvaluator():base(Evaluator.__Fields.BYTE_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 7327200711399789849L;
			public static readonly Evaluator INSTANCE = new ByteLessEvaluator();
			
			internal ByteLessEvaluator():base(Evaluator.__Fields.BYTE_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 5455645713080692170L;
			public static readonly Evaluator INSTANCE = new ByteLessOrEqualEvaluator();
			
			internal ByteLessOrEqualEvaluator():base(Evaluator.__Fields.BYTE_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = - 3319688501086570921L;
			public static readonly Evaluator INSTANCE = new ByteGreaterEvaluator();
			
			internal ByteGreaterEvaluator():base(Evaluator.__Fields.BYTE_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
			private const long serialVersionUID = 8173066470532237341L;
			public static readonly Evaluator INSTANCE = new ByteGreaterOrEqualEvaluator();
			
			internal ByteGreaterOrEqualEvaluator():base(Evaluator.__Fields.BYTE_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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