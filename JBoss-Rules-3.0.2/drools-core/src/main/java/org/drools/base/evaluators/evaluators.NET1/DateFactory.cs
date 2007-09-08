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
	
	/// <summary> This will generate evaluators that handle dates.
	/// This will also parse strings into dates, according to 
	/// DEFAULT_FORMAT_MASK, unless it is overridden by the drools.dateformat system property.
	/// 
	/// When parsing dates from a string, no time is included.
	/// 
	/// So you can do expressions like 
	/// <code>Person(birthday <= "10-Jul-1974")</code> etc.
	/// 
	/// </summary>
	/// <author>  Michael Neale
	/// </author>
	public class DateFactory
	{
		/// <summary>Check for the system property override, if it exists </summary>
		private static System.String DateFormatMask
		{
			get
			{
				//UPGRADE_ISSUE: Method 'java.lang.System.getProperty' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1000_javalangSystem'"
				System.String fmt = System_Renamed.getProperty("drools.dateformat");
				if (fmt == null)
				{
					fmt = DateFactory.DEFAULT_FORMAT_MASK;
				}
				return fmt;
			}
			
		}
		
		private const System.String DEFAULT_FORMAT_MASK = "dd-MMM-yyyy";
		//UPGRADE_NOTE: Final was removed from the declaration of 'DATE_FORMAT_MASK '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
		private static readonly System.String DATE_FORMAT_MASK = DateFormatMask;
		
		public static Evaluator getDateEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.EQUAL: 
					return DateEqualEvaluator.INSTANCE;
				
				case Evaluator.NOT_EQUAL: 
					return DateNotEqualEvaluator.INSTANCE;
				
				case Evaluator.LESS: 
					return DateLessEvaluator.INSTANCE;
				
				case Evaluator.LESS_OR_EQUAL: 
					return DateLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.GREATER: 
					return DateGreaterEvaluator.INSTANCE;
				
				case Evaluator.GREATER_OR_EQUAL: 
					return DateGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for DateEvaluator");
				
			}
		}
		
		internal class DateEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 7248999526793624416L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DateEqualEvaluator();
			
			internal DateEqualEvaluator():base(Evaluator.DATE_TYPE, Evaluator.EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				if (object2 == null)
				{
					return false;
				}
				//UPGRADE_NOTE: Final was removed from the declaration of 'left '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.DateTime left = (System.DateTime) object1;
				
				if (left.CompareTo(org.drools.base_Renamed.evaluators.DateFactory.getRightDate(object2)) == 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			public virtual System.String toString()
			{
				return "Date ==";
			}
		}
		
		internal class DateNotEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 999744404766802074L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DateNotEqualEvaluator();
			
			internal DateNotEqualEvaluator():base(Evaluator.DATE_TYPE, Evaluator.NOT_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				if (object2 == null)
				{
					return true;
				}
				//UPGRADE_NOTE: Final was removed from the declaration of 'left '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.base_Renamed.evaluators.DateFactory.getRightDate(object2)) != 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			public virtual System.String toString()
			{
				return "Date !=";
			}
		}
		
		internal class DateLessEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 4362504881470806670L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DateLessEvaluator();
			
			internal DateLessEvaluator():base(Evaluator.DATE_TYPE, Evaluator.LESS)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'left '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.base_Renamed.evaluators.DateFactory.getRightDate(object2)) < 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			public virtual System.String toString()
			{
				return "Date <";
			}
		}
		
		internal class DateLessOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 1545183091770593710L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DateLessOrEqualEvaluator();
			
			internal DateLessOrEqualEvaluator():base(Evaluator.DATE_TYPE, Evaluator.LESS_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'left '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.base_Renamed.evaluators.DateFactory.getRightDate(object2)) <= 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			public virtual System.String toString()
			{
				return "Date <=";
			}
		}
		
		internal class DateGreaterEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = 1450531664603794369L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			public static readonly Evaluator INSTANCE = new DateGreaterEvaluator();
			
			internal DateGreaterEvaluator():base(Evaluator.DATE_TYPE, Evaluator.GREATER)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'left '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.base_Renamed.evaluators.DateFactory.getRightDate(object2)) > 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			public virtual System.String toString()
			{
				return "Date >";
			}
		}
		
		internal class DateGreaterOrEqualEvaluator:BaseEvaluator
		{
			/// <summary> </summary>
			private const long serialVersionUID = - 6149840707848164332L;
			//UPGRADE_NOTE: Final was removed from the declaration of 'INSTANCE '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			private static readonly Evaluator INSTANCE = new DateGreaterOrEqualEvaluator();
			
			internal DateGreaterOrEqualEvaluator():base(Evaluator.DATE_TYPE, Evaluator.GREATER_OR_EQUAL)
			{
			}
			
			public virtual bool evaluate(System.Object object1, System.Object object2)
			{
				//UPGRADE_NOTE: Final was removed from the declaration of 'left '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.base_Renamed.evaluators.DateFactory.getRightDate(object2)) >= 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			public virtual System.String toString()
			{
				return "Date >=";
			}
		}
		
		/// <summary>Use the simple date formatter to read the date from a string </summary>
		private static System.DateTime parseDate(System.String input)
		{
			
			//UPGRADE_NOTE: Final was removed from the declaration of 'df '. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1003'"
			//UPGRADE_ISSUE: Class 'java.text.SimpleDateFormat' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1000_javatextSimpleDateFormat'"
			//UPGRADE_ISSUE: Constructor 'java.text.SimpleDateFormat.SimpleDateFormat' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1000_javatextSimpleDateFormat'"
			SimpleDateFormat df = new SimpleDateFormat(DateFactory.DATE_FORMAT_MASK);
			try
			{
				return System.DateTime.Parse(input, df);
			}
			//UPGRADE_TODO: Class 'java.text.ParseException' was converted to 'System.FormatException' which has a different behavior. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1073_javatextParseException'"
			catch (System.FormatException e)
			{
				throw new System.ArgumentException("Invalid date input format: [" + input + "] it should follow: [" + DateFactory.DATE_FORMAT_MASK + "]");
			}
		}
		
		/// <summary>Converts the right hand side date as appropriate </summary>
		private static System.DateTime getRightDate(System.Object object2)
		{
			if (object2 == null)
			{
				//UPGRADE_TODO: The 'System.DateTime' structure does not have an equivalent to NULL. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1291'"
				return null;
			}
			if (object2 is System.String)
			{
				return parseDate((System.String) object2);
			}
			else if (object2 is System.DateTime)
			{
				return (System.DateTime) object2;
			}
			else
			{
				throw new System.ArgumentException("Unable to convert " + object2.GetType() + " to a Date.");
			}
		}
	}
}