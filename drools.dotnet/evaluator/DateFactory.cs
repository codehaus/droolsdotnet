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
using java.text;
using System.Globalization;
namespace org.drools.dotnet.evaluator
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

                System.String fmt = null; // System_Renamed.getProperty("drools.dateformat");
				if (fmt == null)
				{
					fmt = DateFactory.DEFAULT_FORMAT_MASK;
				}
				return fmt;
			}
			
		}
		
		private const System.String DEFAULT_FORMAT_MASK = "dd-MMM-yyyy";
		private static readonly System.String DATE_FORMAT_MASK = DateFormatMask;
		
		public static Evaluator getDateEvaluator(int operator_Renamed)
		{
			switch (operator_Renamed)
			{
				
				case Evaluator.__Fields.EQUAL: 
					return DateEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.NOT_EQUAL: 
					return DateNotEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS: 
					return DateLessEvaluator.INSTANCE;
				
				case Evaluator.__Fields.LESS_OR_EQUAL: 
					return DateLessOrEqualEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER: 
					return DateGreaterEvaluator.INSTANCE;
				
				case Evaluator.__Fields.GREATER_OR_EQUAL: 
					return DateGreaterOrEqualEvaluator.INSTANCE;
				
				default: 
					throw new System.SystemException("Operator '" + operator_Renamed + "' does not exist for DateEvaluator");
				
			}
		}
		
		internal class DateEqualEvaluator:BaseEvaluator
		{
			private const long serialVersionUID = - 7248999526793624416L;
			public static readonly Evaluator INSTANCE = new DateEqualEvaluator();
			
			internal DateEqualEvaluator():base(Evaluator.__Fields.DATE_TYPE, Evaluator.__Fields.EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 == null;
				}
				if (object2 == null)
				{
					return false;
				}
				System.DateTime left = (System.DateTime) object1;
				
				if (left.CompareTo(org.drools.dotnet.evaluator.DateFactory.getRightDate(object2)) == 0)
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
			private const long serialVersionUID = - 999744404766802074L;
			public static readonly Evaluator INSTANCE = new DateNotEqualEvaluator();
			
			internal DateNotEqualEvaluator():base(Evaluator.__Fields.DATE_TYPE, Evaluator.__Fields.NOT_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				if (object1 == null)
				{
					return object2 != null;
				}
				if (object2 == null)
				{
					return true;
				}
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.dotnet.evaluator.DateFactory.getRightDate(object2)) != 0)
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
			private const long serialVersionUID = - 4362504881470806670L;
			public static readonly Evaluator INSTANCE = new DateLessEvaluator();
			
			internal DateLessEvaluator():base(Evaluator.__Fields.DATE_TYPE, Evaluator.__Fields.LESS)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.dotnet.evaluator.DateFactory.getRightDate(object2)) < 0)
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
			private const long serialVersionUID = - 1545183091770593710L;
			public static readonly Evaluator INSTANCE = new DateLessOrEqualEvaluator();
			
			internal DateLessOrEqualEvaluator():base(Evaluator.__Fields.DATE_TYPE, Evaluator.__Fields.LESS_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.dotnet.evaluator.DateFactory.getRightDate(object2)) <= 0)
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
			private const long serialVersionUID = 1450531664603794369L;
			public static readonly Evaluator INSTANCE = new DateGreaterEvaluator();
			
			internal DateGreaterEvaluator():base(Evaluator.__Fields.DATE_TYPE, Evaluator.__Fields.GREATER)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.dotnet.evaluator.DateFactory.getRightDate(object2)) > 0)
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
			private const long serialVersionUID = - 6149840707848164332L;
			public static readonly Evaluator INSTANCE = new DateGreaterOrEqualEvaluator();
			
			internal DateGreaterOrEqualEvaluator():base(Evaluator.__Fields.DATE_TYPE, Evaluator.__Fields.GREATER_OR_EQUAL)
			{
			}
			
			public override bool evaluate(System.Object object1, System.Object object2)
			{
				System.DateTime left = (System.DateTime) object1;
				if (left.CompareTo(org.drools.dotnet.evaluator.DateFactory.getRightDate(object2)) >= 0)
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
		
		private static System.DateTime parseDate(System.String input)
		{
			//IFormatProvider prov = 
			//SimpleDateFormat df = new SimpleDateFormat(DateFactory.DATE_FORMAT_MASK);
			try
			{
                return System.DateTime.ParseExact(input, DateFactory.DEFAULT_FORMAT_MASK, CultureInfo.InvariantCulture);
			}
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
                //It was returning null earlier
                return new DateTime();
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