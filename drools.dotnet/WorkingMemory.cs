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
using System.Collections.Generic;
using System.Text;
using System.Collections;
using java.util;
using org.drools.@event;
using org.drools.spi;
using java.lang;
using org.drools.dotnet.events;

namespace org.drools.dotnet
{
    public class WorkingMemory
    {
        internal org.drools.WorkingMemory _javaWorkingMemory;

        public WorkingMemory(org.drools.WorkingMemory wm)
        {
            this._javaWorkingMemory = wm;
            _javaWorkingMemory.addEventListener(new EventListener(this));
        }

        public org.drools.WorkingMemory GetUnderlyingJavaObject()
        {
            return _javaWorkingMemory;
        }

        public void addEventListener(WorkingMemoryEventListener listener)
        {
            _javaWorkingMemory.addEventListener(listener);
        }



        public void removeEventListener(WorkingMemoryEventListener listener)
        {
            _javaWorkingMemory.removeEventListener(listener);

        }

        public List getWorkingMemoryEventListeners()
        {
            return _javaWorkingMemory.getWorkingMemoryEventListeners();
        }

        public void addEventListener(AgendaEventListener listener)
        {
            _javaWorkingMemory.addEventListener(listener);
        }

        public void removeEventListener(AgendaEventListener listener)
        {
            _javaWorkingMemory.removeEventListener(listener);
        }

        public List getAgendaEventListeners()
        {
            return _javaWorkingMemory.getAgendaEventListeners();
        }

        public Agenda getAgenda()
        {
            return _javaWorkingMemory.getAgenda();
        }

        public Map getGlobals()
        {
            return _javaWorkingMemory.getGlobals();
        }

        public void setGlobal(string name,
                       object value)
        {
            this._javaWorkingMemory.setGlobal(name, value);
        }

        public object getGlobal(string name)
        {
            return this._javaWorkingMemory.getGlobal(name);
        }

        public RuleBase getRuleBase()
        {
            return new RuleBase(this._javaWorkingMemory.getRuleBase());
        }

        public void fireAllRules()
        {
            this._javaWorkingMemory.fireAllRules();
        }

        public void fireAllRules(AgendaFilter agendaFilter)
        {
            this._javaWorkingMemory.fireAllRules(agendaFilter);
        }

        public object getObject(FactHandle handle)
        {
            return this._javaWorkingMemory.getObject(handle);
        }

        public FactHandle getFactHandle(object obj)
        {
            return this._javaWorkingMemory.getFactHandle(obj);
        }

        public List getObjects()
        {
            return this._javaWorkingMemory.getObjects();
        }

        public AgendaGroup getFocus()
        {
            return this._javaWorkingMemory.getFocus();
        }

        public void setFocus(string focus)
        {
            this._javaWorkingMemory.setFocus(focus);
        }

        public void setFocus(AgendaGroup focus)
        {
            this._javaWorkingMemory.setFocus(focus);
        }

        public List getObjects(Class objectClass)
        {
            return this._javaWorkingMemory.getObjects(objectClass);
        }

        public List getFactHandles()
        {
            return this._javaWorkingMemory.getFactHandles();
        }

        public FactHandle assertObject(object obj)
        {
            return this._javaWorkingMemory.assertObject(obj);
        }

        public QueryResults getQueryResults(string query)
        {
            return this._javaWorkingMemory.getQueryResults(query);
        }

        public FactHandle assertObject(object obj,
                                bool dynamic)
        {
            return this._javaWorkingMemory.assertObject(obj, dynamic);
        }

        public void retractObject(FactHandle handle)
        {
            this._javaWorkingMemory.retractObject(handle);
        }

        public void modifyObject(FactHandle handle,
                          object obj)
        {
            this._javaWorkingMemory.modifyObject(handle, obj);
        }

        public void setAsyncExceptionHandler(AsyncExceptionHandler handler)
        {
            this._javaWorkingMemory.setAsyncExceptionHandler(handler);
        }

        public void clearAgenda()
        {
            this._javaWorkingMemory.clearAgenda();
        }

        public void clearAgendaGroup(string group)
        {
            this._javaWorkingMemory.clearAgendaGroup(group);
        }

        public void dispose()
        {
            this._javaWorkingMemory.dispose();
        }

        public event EventHandler<ObjectAssertedEventArgs> ObjectAsserted;
		public event EventHandler<ObjectModifiedEventArgs> ObjectModified;
		public event EventHandler<ObjectRetractedEventArgs> ObjectRetracted;


        internal void OnObjectAsserted(object sender, ObjectAssertedEventArgs e)
        {
            if (ObjectAsserted != null) ObjectAsserted(sender, e);
        }

        internal void OnObjectModified(object sender, ObjectModifiedEventArgs e)
        {
            if (ObjectModified != null) ObjectModified(sender, e);
        }

        internal void OnObjectRetracted(object sender, ObjectRetractedEventArgs e)
        {
            if (ObjectRetracted != null) ObjectRetracted(sender, e);
        }
    }
}