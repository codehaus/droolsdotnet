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
using java.beans;
namespace org.drools.dotnet.examples
{
	
	/// <author>  Peter Lin
	/// 
	/// A simple test bean that represents a generic account. It could be
	/// a bank account, shopping card account, or any type of membership
	/// account with a nationwide company.
	/// </author>
	[Serializable]
	public class Account
	{
		public string Title
		{
			get
			{
				return this.title;
			}
			
			set
			{
				if (!value.Equals(this.title))
				{
					string old = this.title;
					this.title = value;
					notifyListener("title", old, this.title);
				}
			}
			
		}
		public string First
		{
			get
			{
				return this.first;
			}
			
			set
			{
				if (!value.Equals(this.first))
				{
					string old = this.first;
					this.first = value;
					notifyListener("first", old, this.first);
				}
			}
			
		}
		public string Last
		{
			get
			{
				return this.last;
			}
			
			set
			{
				if (!value.Equals(this.last))
				{
					string old = this.last;
					this.last = value;
					notifyListener("last", old, this.last);
				}
			}
			
		}
		public string Middle
		{
			get
			{
				return this.middle;
			}
			
			set
			{
				if (!value.Equals(this.middle))
				{
					string old = this.middle;
					this.middle = value;
					notifyListener("middle", old, this.middle);
				}
			}
			
		}
		public string OfficeCode
		{
			get
			{
				return this.officeCode;
			}
			
			set
			{
				if (!value.Equals(this.officeCode))
				{
					string old = this.officeCode;
					this.officeCode = value;
					notifyListener("officeCode", old, this.officeCode);
				}
			}
			
		}
		public string RegionCode
		{
			get
			{
				return this.regionCode;
			}
			
			set
			{
				if (!value.Equals(this.regionCode))
				{
					string old = this.regionCode;
					this.regionCode = value;
					notifyListener("regionCode", old, this.regionCode);
				}
			}
			
		}
		public string Status
		{
			get
			{
				return this.status;
			}
			
			set
			{
				if (!value.Equals(this.status))
				{
					string old = this.status;
					this.status = value;
					notifyListener("status", old, this.status);
				}
			}
			
		}
		public string AccountId
		{
			get
			{
				return this.accountId;
			}
			
			set
			{
				if (!value.Equals(this.accountId))
				{
					string old = this.accountId;
					this.accountId = value;
					notifyListener("accountId", old, this.accountId);
				}
			}
			
		}
		public string AccountType
		{
			get
			{
				return this.accountType;
			}
			
			set
			{
				if (!value.Equals(this.accountType))
				{
					string old = this.accountType;
					this.accountType = value;
					notifyListener("accountType", old, this.accountType);
				}
			}
			
		}
		public string Username
		{
			get
			{
				return this.username;
			}
			
			set
			{
				if (!value.Equals(this.username))
				{
					string old = this.username;
					this.username = value;
					notifyListener("username", old, this.username);
				}
			}
			
		}
		public string AreaCode
		{
			get
			{
				return this.areaCode;
			}
			
			set
			{
				if (!value.Equals(this.areaCode))
				{
					string old = this.areaCode;
					this.areaCode = value;
					notifyListener("areaCode", old, this.areaCode);
				}
			}
			
		}
		public string Exchange
		{
			get
			{
				return this.exchange;
			}
			
			set
			{
				if (!value.Equals(this.exchange))
				{
					string old = this.exchange;
					this.exchange = value;
					notifyListener("exchange", old, this.exchange);
				}
			}
			
		}
		public string Number
		{
			get
			{
				return this.number;
			}
			
			set
			{
				if (!value.Equals(this.number))
				{
					string old = this.number;
					this.number = value;
					notifyListener("number", old, this.number);
				}
			}
			
		}
		public string Ext
		{
			get
			{
				return this.ext;
			}
			
			set
			{
				if (!value.Equals(this.ext))
				{
					string old = this.ext;
					this.ext = value;
					notifyListener("ext", old, this.ext);
				}
			}
			
		}
		
		protected internal string first = null;
		protected internal string middle = null;
		protected internal string last = null;
		/// <summary> mr, mrs, ms, junior, etc</summary>
		protected internal string title = null;
		protected internal string accountId = null;
		protected internal string accountType = null;
		protected internal string status = null;
		protected internal string username = null;
		/// <summary> this would represent the region of the office the account
		/// was opened at.
		/// </summary>
		protected internal string regionCode = null;
		/// <summary> the code for the office where the account was opened.</summary>
		protected internal string officeCode = null;
		protected internal string areaCode = null;
		protected internal string exchange = null;
		protected internal string number = null;
		protected internal string ext = null;
		
		protected internal System.Collections.ArrayList listeners = new System.Collections.ArrayList();
		
		
		public Account()
		{
		}
		
		
		public void  addPropertyChangeListener(PropertyChangeListener listener)
		{
			this.listeners.Add(listener);
		}
		
		
		public void  removePropertyChangeListener(PropertyChangeListener listener)
		{
            this.listeners.Remove(listener);
		}
		
		protected internal void  notifyListener(string field, System.Object oldValue, System.Object newValue)
		{
			if (listeners == null || listeners.Count == 0)
			{
				return ;
			}
			else
			{
				PropertyChangeEvent event_Renamed = new PropertyChangeEvent(this, field, oldValue, newValue);
				
				for (int i = 0; i < listeners.Count; i++)
				{
					((java.beans.PropertyChangeListener) listeners[i]).propertyChange(event_Renamed);
				}
			}
		}
	}
}