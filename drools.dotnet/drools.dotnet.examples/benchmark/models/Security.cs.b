using System;
namespace org.drools.benchmark.models
{
	
	/// <author>  Peter Lin
	/// 
	/// </author>
	[Serializable]
	public class Security
	{
		virtual public System.String CountryCode
		{
			get
			{
				return this.countryCode;
			}
			
			set
			{
				if (!value.Equals(this.countryCode))
				{
					System.String old = this.countryCode;
					this.countryCode = value;
					this.notifyListener("countryCode", old, this.countryCode);
				}
			}
			
		}
		virtual public double CurrentPrice
		{
			get
			{
				return this.currentPrice;
			}
			
			set
			{
				if (value != this.currentPrice)
				{
					System.Double old = (double) this.currentPrice;
					this.currentPrice = value;
					this.notifyListener("currentPrice", (System.Object) old, (System.Object) this.currentPrice);
				}
			}
			
		}
		virtual public int Cusip
		{
			get
			{
				return this.cusip;
			}
			
			set
			{
				if (value != this.cusip)
				{
					System.Int32 old = (System.Int32) this.cusip;
					this.cusip = value;
					this.notifyListener("cusip", (System.Object) old, (System.Object) this.cusip);
				}
			}
			
		}
		virtual public System.String Exchange
		{
			get
			{
				return this.exchange;
			}
			
			set
			{
				if (!value.Equals(this.exchange))
				{
					System.String old = this.exchange;
					this.exchange = value;
					this.notifyListener("exchange", old, this.exchange);
				}
			}
			
		}
		virtual public int IndustryGroupID
		{
			get
			{
				return this.industryGroupID;
			}
			
			set
			{
				if (value != this.industryGroupID)
				{
					int old = this.industryGroupID;
					this.industryGroupID = value;
					this.notifyListener("industryGroupID", (System.Object) old, (System.Object) this.industryGroupID);
				}
			}
			
		}
		virtual public int IndustryID
		{
			get
			{
				return this.industryID;
			}
			
			set
			{
				if (value != this.industryID)
				{
					int old = this.industryID;
					this.industryID = value;
					this.notifyListener("industryID", (System.Object) old, (System.Object) this.industryID);
				}
			}
			
		}
		virtual public System.String Issuer
		{
			get
			{
				return this.issuer;
			}
			
			set
			{
				if (!value.Equals(this.issuer))
				{
					System.String old = this.issuer;
					this.issuer = value;
					this.notifyListener("issuer", old, this.issuer);
				}
			}
			
		}
		virtual public double LastPrice
		{
			get
			{
				return this.lastPrice;
			}
			
			set
			{
				if (value != this.lastPrice)
				{
					System.Double old = (double) this.lastPrice;
					this.lastPrice = value;
					this.notifyListener("lastPrice", (System.Object) old, (System.Object) this.lastPrice);
				}
			}
			
		}
		virtual public int SectorID
		{
			get
			{
				return this.sectorID;
			}
			
			set
			{
				if (value != this.sectorID)
				{
					int old = this.sectorID;
					this.sectorID = value;
					this.notifyListener("sectorID", (System.Object) old, (System.Object) this.sectorID);
				}
			}
			
		}
		virtual public int SubIndustryID
		{
			get
			{
				return this.subIndustryID;
			}
			
			set
			{
				if (value != this.subIndustryID)
				{
					int old = this.subIndustryID;
					this.subIndustryID = value;
					this.notifyListener("subIndustryID", (System.Object) old, (System.Object) this.subIndustryID);
				}
			}
			
		}
		
		protected internal System.String countryCode = null;
		protected internal double currentPrice;
		protected internal int cusip;
		protected internal System.String exchange = null;
		protected internal int industryGroupID;
		protected internal int industryID;
		protected internal System.String issuer = null;
		protected internal double lastPrice;
		protected internal int sectorID;
		protected internal int subIndustryID;
		
		protected internal System.Collections.ArrayList listeners = new System.Collections.ArrayList();
		
		public Security():base()
		{
		}
		
		//UPGRADE_TODO: Interface 'java.beans.PropertyChangeListener' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1095'"
		public virtual void  addPropertyChangeListener(PropertyChangeListener listener)
		{
			this.listeners.Add(listener);
		}
		
		//UPGRADE_TODO: Interface 'java.beans.PropertyChangeListener' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1095'"
		public virtual void  removePropertyChangeListener(PropertyChangeListener listener)
		{
			SupportClass.ICollectionSupport.Remove(this.listeners, listener);
		}
		
		protected internal virtual void  notifyListener(System.String field, System.Object oldValue, System.Object newValue)
		{
			if (listeners == null || listeners.Count == 0)
			{
				return ;
			}
			else
			{
				//UPGRADE_ISSUE: Constructor 'java.beans.PropertyChangeEvent.PropertyChangeEvent' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1000_javabeansPropertyChangeEventPropertyChangeEvent_javalangObject_javalangString_javalangObject_javalangObject'"
				SupportClass.PropertyChangingEventArgs event_Renamed = new PropertyChangeEvent(this, field, oldValue, newValue);
				
				for (int i = 0; i < listeners.Count; i++)
				{
					//UPGRADE_TODO: Method 'java.beans.PropertyChangeListener.propertyChange' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1095'"
					//UPGRADE_TODO: Interface 'java.beans.PropertyChangeListener' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1095'"
					((java.beans.PropertyChangeListener) listeners[i]).propertyChange(event_Renamed);
				}
			}
		}
	}
}