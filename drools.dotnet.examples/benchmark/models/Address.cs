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
	/// A simple address class that represents a house a person owns.
	/// </author>
	[Serializable]
	public class Address
	{
		virtual public string Title
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
		virtual public string State
		{
			get
			{
				return this.state;
			}
			
			set
			{
				if (!value.Equals(this.state))
				{
					string old = this.state;
					this.state = value;
					notifyListener("officeCode", old, this.state);
				}
			}
			
		}
		virtual public string City
		{
			get
			{
				return this.city;
			}
			
			set
			{
				if (!value.Equals(this.city))
				{
					string old = this.city;
					this.city = value;
					notifyListener("regionCode", old, this.city);
				}
			}
			
		}
		virtual public string Street2
		{
			get
			{
				return this.street2;
			}
			
			set
			{
				if (!value.Equals(this.street2))
				{
					string old = this.street2;
					this.street2 = value;
					notifyListener("status", old, this.street2);
				}
			}
			
		}
		virtual public string AccountId
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
		virtual public string Street
		{
			get
			{
				return this.street;
			}
			
			set
			{
				if (!value.Equals(this.street))
				{
					string old = this.street;
					this.street = value;
					notifyListener("accountType", old, this.street);
				}
			}
			
		}
		virtual public string Status
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
					notifyListener("username", old, this.status);
				}
			}
			
		}
		virtual public string PostalCode
		{
			get
			{
				return this.postalCode;
			}
			
			set
			{
				if (!value.Equals(this.postalCode))
				{
					string old = this.postalCode;
					this.postalCode = value;
					notifyListener("areaCode", old, this.postalCode);
				}
			}
			
		}
		virtual public string HouseType
		{
			get
			{
				return this.houseType;
			}
			
			set
			{
				if (!value.Equals(this.houseType))
				{
					string old = this.houseType;
					this.houseType = value;
					notifyListener("exchange", old, this.houseType);
				}
			}
			
		}
		virtual public string Country
		{
			get
			{
				return this.country;
			}
			
			set
			{
				if (!value.Equals(this.country))
				{
					string old = this.country;
					this.country = value;
					notifyListener("number", old, this.country);
				}
			}
			
		}
		
		protected internal string title = null;
		protected internal string accountId = null;
		protected internal string street = null;
		protected internal string street2 = null;
		protected internal string status = null;
		protected internal string city = null;
		protected internal string state = null;
		protected internal string postalCode = null;
		protected internal string houseType = null;
		protected internal string country = null;
		
		protected internal System.Collections.ArrayList listeners = new System.Collections.ArrayList();
		
		/// <summary> </summary>
		public Address()
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