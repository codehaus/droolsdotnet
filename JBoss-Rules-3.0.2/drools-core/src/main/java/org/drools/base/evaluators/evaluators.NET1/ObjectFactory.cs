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
	/// This supports "<" and ">" etc by requiring objects to implement the comparable interface.
	/// Of course, literals will not work with comparator, as it has no way
	/// of converting from literal to the appropriate type.
	/// 
	/// </summary>
	/// <author>  Michael Neale
	/// </author>
	public class ObjectFactory
	{
		
		public static Evaluator getObjectEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return ObjectEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return ObjectNotEqualEvaluator.INSTANCE;
				
				case Evaluator.CONTAINS: 
					return ObjectContainsEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return ObjectLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return ObjectLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return ObjectGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return ObjectGreaterOrEqualEvaluator.INSTANCE;
				
				case Evaluator.EXCLUDES: 
					return ObjectExcludesEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for ObjectEvaluator");
				
			}
		}
		
		internal class ObjectEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 4532849170161094887L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectEqualEvaluator();
			
			internal ObjectEqualEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.EQUAL)
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
				return "Object ==";
			}
		}
		
		internal class ObjectNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 6995475512317781516L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectNotEqualEvaluator();
			
			internal ObjectNotEqualEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.NOT_EQUAL)
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
				return "Object !=";
			}
		}
		
		internal class ObjectLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 5327278696364237380L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectLessEvaluator();
			
			internal ObjectLessEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'comp '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.IComparable comp = (System.IComparable) object1;
				//UPGRADE_NOTE: Final was removed from the declaration of 'val '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
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
			/// <summary> </summary>
			private const long serialVersionUID = 3463248146201714137L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectLessOrEqualEvaluator();
			
			internal ObjectLessOrEqualEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'comp '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
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
			/// <summary> </summary>
			private const long serialVersionUID = 7808425299326128881L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectGreaterEvaluator();
			
			internal ObjectGreaterEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'comp '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
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
			/// <summary> </summary>
			private const long serialVersionUID = - 3337081388987517878L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectGreaterOrEqualEvaluator();
			
			internal ObjectGreaterOrEqualEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'comp '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
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
			/// <summary> </summary>
			private const long serialVersionUID = 6607233589136455455L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectContainsEvaluator();
			
			internal ObjectContainsEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.CONTAINS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object2 == null)
				{
					return false;
				}
				//UPGRADE_NOTE: Final was removed from the declaration of 'col '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.Collections.ICollection col = (System.Collections.ICollection) object1;
				return SupportClass.ICollectionSupport.Contains(col, object2);
			}
			
			public virtual System.String toString()
			{
				return "Object contains";
			}
		}
		
		internal class ObjectExcludesEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 4353038775000051165L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new ObjectExcludesEvaluator();
			
			internal ObjectExcludesEvaluator():base(Evaluator.OBJECT_TYPE, Evaluator.EXCLUDES)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object2 == null)
				{
					return false;
				}
				//UPGRADE_NOTE: Final was removed from the declaration of 'col '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.Collections.ICollection col = (System.Collections.ICollection) object1;
				return !SupportClass.ICollectionSupport.Contains(col, object2);
			}
			
			public virtual System.String toString()
			{
				return "Object excludes";
			}
		}
	}
}