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
	
	public class ShortFactory
	{
		public static Evaluator getShortEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return ShortEqualEvaluator.Instance;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return ShortNotEqualEvaluator.Instance;
				
				case Evaluator.__Fields.LESS: 
					return ShortLessEvaluator.Instance;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return ShortLessOrEqualEvaluator.Instance;
				
				case Evaluator.__Fields.GREATER: 
					return ShortGreaterEvaluator.Instance;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
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
					if (org.drools.dotnet.evaluator.ShortFactory.ShortEqualEvaluator.INSTANCE == null)
					{
						org.drools.dotnet.evaluator.ShortFactory.ShortEqualEvaluator.INSTANCE = new ShortEqualEvaluator();
					}
					return org.drools.dotnet.evaluator.ShortFactory.ShortEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = 8933390138182317179L;
			private static Evaluator INSTANCE;
			
			internal ShortEqualEvaluator():base(Evaluator.__Fields.SHORT_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
					if (org.drools.dotnet.evaluator.ShortFactory.ShortNotEqualEvaluator.INSTANCE == null)
					{
						org.drools.dotnet.evaluator.ShortFactory.ShortNotEqualEvaluator.INSTANCE = new ShortNotEqualEvaluator();
					}
					return org.drools.dotnet.evaluator.ShortFactory.ShortNotEqualEvaluator.INSTANCE;
				}
				
			}
			private const long serialVersionUID = - 273350270376804828L;
			private static Evaluator INSTANCE;
			
			internal ShortNotEqualEvaluator():base(Evaluator.__Fields.SHORT_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
					if (org.drools.dotnet.evaluator.ShortFactory.ShortLessEvaluator.INSTANCE == null)
					{
						org.drools.dotnet.evaluator.ShortFactory.ShortLessEvaluator.INSTANCE = new ShortLessEvaluator();
					}
					return org.drools.dotnet.evaluator.ShortFactory.ShortLessEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 1562867187426899162L;
			private static Evaluator INSTANCE;
			
			internal ShortLessEvaluator():base(Evaluator.__Fields.SHORT_TYPE, Evaluator.__Fields.LESS)
			{
			}

            public override bool evaluate(System.Object object1, System.Object object2)
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
					if (org.drools.dotnet.evaluator.ShortFactory.ShortLessOrEqualEvaluator.INSTANCE == null)
					{
						org.drools.dotnet.evaluator.ShortFactory.ShortLessOrEqualEvaluator.INSTANCE = new ShortLessOrEqualEvaluator();
					}
					return org.drools.dotnet.evaluator.ShortFactory.ShortLessOrEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 1541816846266081605L;
			private static Evaluator INSTANCE;
			
			internal ShortLessOrEqualEvaluator():base(Evaluator.__Fields.SHORT_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}

            public override bool evaluate(System.Object object1, System.Object object2)
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
					if (org.drools.dotnet.evaluator.ShortFactory.ShortGreaterEvaluator.INSTANCE == null)
					{
						org.drools.dotnet.evaluator.ShortFactory.ShortGreaterEvaluator.INSTANCE = new ShortGreaterEvaluator();
					}
					return org.drools.dotnet.evaluator.ShortFactory.ShortGreaterEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = - 3260955087091852509L;
			private static Evaluator INSTANCE;
			
			internal ShortGreaterEvaluator():base(Evaluator.__Fields.SHORT_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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
					if (org.drools.dotnet.evaluator.ShortFactory.ShortGreaterOrEqualEvaluator.INSTANCE == null)
					{
						org.drools.dotnet.evaluator.ShortFactory.ShortGreaterOrEqualEvaluator.INSTANCE = new ShortGreaterOrEqualEvaluator();
					}
					return org.drools.dotnet.evaluator.ShortFactory.ShortGreaterOrEqualEvaluator.INSTANCE;
				}
				
			}
			/// <summary> </summary>
			private const long serialVersionUID = 1254418853497580320L;
			private static Evaluator INSTANCE;
			
			internal ShortGreaterOrEqualEvaluator():base(Evaluator.__Fields.SHORT_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
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