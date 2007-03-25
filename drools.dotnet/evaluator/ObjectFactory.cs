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
	public class ObjectFactory
	{
		
		public static Evaluator getObjectEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return ObjectEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return ObjectNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.CONTAINS: 
					return ObjectContainsEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return ObjectLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return ObjectLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return ObjectGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return ObjectGreaterOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.EXCLUDES: 
					return ObjectExcludesEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ObjectEvaluator");
				
			}
		}
		
		internal class ObjectEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 4532849170161094887L;
			public static readonly Evaluator INSTANCE = new ObjectEqualEvaluator();
			
			internal ObjectEqualEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				return object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Object ==";
			}
		}
		
		internal class ObjectNotEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 6995475512317781516L;
			public static readonly Evaluator INSTANCE = new ObjectNotEqualEvaluator();
			
			internal ObjectNotEqualEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return !(object2 == null);
				}
				return !object1.Equals(object2);
			}
			
			public virtual System.String toString()
			{
				return "Object !=";
			}
		}
		
		internal class ObjectLessEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 5327278696364237380L;
			public static readonly Evaluator INSTANCE = new ObjectLessEvaluator();
			
			internal ObjectLessEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.IComparable comp = (System.IComparable) object1;
				int val = comp.CompareTo(object2);
				return val < 0;
			}
			
			public virtual System.String toString()
			{
				return "Object <";
			}
		}
		
		internal class ObjectLessOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 3463248146201714137L;
			public static readonly Evaluator INSTANCE = new ObjectLessOrEqualEvaluator();
			
			internal ObjectLessOrEqualEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.IComparable comp = (System.IComparable) object1;
				return comp.CompareTo(object2) <= 0;
			}
			
			public virtual System.String toString()
			{
				return "Object <=";
			}
		}
		
		internal class ObjectGreaterEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 7808425299326128881L;
			public static readonly Evaluator INSTANCE = new ObjectGreaterEvaluator();
			
			internal ObjectGreaterEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.IComparable comp = (System.IComparable) object1;
				return comp.CompareTo(object2) > 0;
			}
			
			public virtual System.String toString()
			{
				return "Object >";
			}
		}
		
		internal class ObjectGreaterOrEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 3337081388987517878L;
			public static readonly Evaluator INSTANCE = new ObjectGreaterOrEqualEvaluator();
			
			internal ObjectGreaterOrEqualEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.IComparable comp = (System.IComparable) object1;
				return comp.CompareTo(object2) >= 0;
			}
			
			public virtual System.String toString()
			{
				return "Object >=";
			}
		}
		
		internal class ObjectContainsEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 6607233589136455455L;
			public static readonly Evaluator INSTANCE = new ObjectContainsEvaluator();
			
			internal ObjectContainsEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.CONTAINS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object2 == null)
				{
					return false;
				}
				System.Collections.ICollection col = (System.Collections.ICollection) object1;
                return EvaluatorUtil.CollectionsContains(col, object2);
			}
			
			public virtual System.String toString()
			{
				return "Object contains";
			}
		}
		
		internal class ObjectExcludesEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = 4353038775000051165L;
			public static readonly Evaluator INSTANCE = new ObjectExcludesEvaluator();
			
			internal ObjectExcludesEvaluator():base(Evaluator.__Fields.OBJECT_TYPE, Evaluator.__Fields.EXCLUDES)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object2 == null)
				{
					return false;
				}
				System.Collections.ICollection col = (System.Collections.ICollection) object1;
				return !EvaluatorUtil.CollectionsContains(col, object2);
			}
			
			public virtual System.String toString()
			{
				return "Object excludes";
			}
		}
	}
}