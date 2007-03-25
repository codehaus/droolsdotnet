using System;
namespace org.drools.benchmark.models
{
	
	/// <author>  Peter Lin
	/// 
	/// </author>
	[Serializable]
	public class SubTransaction:Security
	{
		virtual public System.String[] TransactionSet
		{
			get
			{
				return this.transactionSet;
			}
			
			set
			{
				if (value != this.transactionSet)
				{
					System.String[] old = this.transactionSet;
					this.transactionSet = value;
					this.notifyListener("transactionSet", old, this.transactionSet);
				}
			}
			
		}
		
		protected internal System.String[] transactionSet = null;
		new protected internal System.Collections.ArrayList listeners = new System.Collections.ArrayList();
		
		public SubTransaction():base()
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