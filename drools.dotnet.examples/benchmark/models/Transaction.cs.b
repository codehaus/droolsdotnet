using System;
namespace org.drools.benchmark.models
{
	
	/// <author>  Peter Lin
	/// 
	/// </author>
	[Serializable]
	public class Transaction:Security
	{
		virtual public System.String AccountId
		{
			get
			{
				return this.accountId;
			}
			
			set
			{
				if (!value.Equals(this.accountId))
				{
					System.String old = this.accountId;
					this.accountId = value;
					this.notifyListener("accountId", old, this.accountId);
				}
			}
			
		}
		virtual public double BuyPrice
		{
			get
			{
				return this.buyPrice;
			}
			
			set
			{
				if (value != this.buyPrice)
				{
					System.Double old = (double) this.buyPrice;
					this.buyPrice = value;
					this.notifyListener("buyPrice", (System.Object) old, (System.Object) this.buyPrice);
				}
			}
			
		}
		virtual public System.String PurchaseDate
		{
			get
			{
				return this.purchaseDate;
			}
			
			set
			{
				if (!value.Equals(this.purchaseDate))
				{
					System.String old = this.purchaseDate;
					this.purchaseDate = value;
					this.notifyListener("purchaseDate", old, this.purchaseDate);
				}
			}
			
		}
		virtual public double Shares
		{
			get
			{
				return this.shares;
			}
			
			set
			{
				if (value != this.shares)
				{
					System.Double old = (double) this.shares;
					this.shares = value;
					this.notifyListener("shares", (System.Object) old, (System.Object) this.shares);
				}
			}
			
		}
		virtual public double Total
		{
			get
			{
				return this.total;
			}
			
			set
			{
				if (value != this.total)
				{
					System.Double old = (double) this.total;
					this.total = value;
					this.notifyListener("total", (System.Object) old, (System.Object) this.total);
				}
			}
			
		}
		virtual public System.String TransactionId
		{
			get
			{
				return this.transactionId;
			}
			
			set
			{
				if (!value.Equals(this.transactionId))
				{
					System.String old = this.transactionId;
					this.transactionId = value;
					this.notifyListener("transactionId", old, this.transactionId);
				}
			}
			
		}
		
		protected internal System.String accountId = null;
		protected internal double buyPrice;
		protected internal System.String purchaseDate = null;
		protected internal double shares;
		protected internal double total;
		protected internal System.String transactionId = null;
		
		new protected internal System.Collections.ArrayList listeners = new System.Collections.ArrayList();
		
		public Transaction():base()
		{
		}
		
		//UPGRADE_TODO: Interface 'java.beans.PropertyChangeListener' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1095'"
		public override void  addPropertyChangeListener(PropertyChangeListener listener)
		{
			this.listeners.Add(listener);
		}
		
		//UPGRADE_TODO: Interface 'java.beans.PropertyChangeListener' was not converted. "ms-help://MS.VSCC.v80/dv_commoner/local/redirect.htm?index='!DefaultContextWindowIndex'&keyword='jlca1095'"
		public override void  removePropertyChangeListener(PropertyChangeListener listener)
		{
			SupportClass.ICollectionSupport.Remove(this.listeners, listener);
		}
		
		protected internal override void  notifyListener(System.String field, System.Object oldValue, System.Object newValue)
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