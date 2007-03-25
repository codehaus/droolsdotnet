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
using org.drools.@base;
using java.lang;
using org.drools;
using System.Reflection;
using System.Collections;
using org.drools.spi;

namespace org.drools.dotnet.semantics
{
    public class DotnetBaseClassFieldExtractor : BaseClassFieldExtractor
    {
        private readonly ClassObjectType objectType;

        private readonly int  index;

        private readonly Type fieldType;

        private readonly string fieldName;
                
        public DotnetBaseClassFieldExtractor(Class clazz, string fieldName): base (null, null)
        {
            Type type = null;
            foreach (Assembly assem in AppDomain.CurrentDomain.GetAssemblies())
            {
                type = assem.GetType(clazz.getName().Replace("cli.", ""));
                if(type != null)
                    break;
            }
            
            this.fieldName = fieldName;
            FieldInfo[] info = type.GetFields();
            string fieldType = type.GetProperty(fieldName).PropertyType.FullName;


            //string originalClassName = clazz.getName().Replace('.', '/');
            //string className = CSClassFieldExtractorFactory.BASE_PACKAGE + "/" + originalClassName + "$" + fieldName;
            try {
                    //this.index = getIndex(type, fieldName);
                    this.index = FieldIndexGenerator.getIndex();
                    this.fieldType = type.GetProperty(fieldName).PropertyType;
                    this.objectType = DotnetClassFieldExtractorFactory.getClassObjectType( this.fieldType );
            } catch ( System.Exception e ) {
                throw new RuntimeDroolsException( e );
            }
            
        }



        /*private int getIndex(Type type, string fieldName)
        {
            int i = 0;
            IEnumerator enume = type.GetProperties().GetEnumerator();
            while (enume.MoveNext())
            {
                i++;
                if (((PropertyInfo)(enume.Current)).Name.Equals(fieldName))
                    return i;
            }
            return i;
        }*/

        public override object getValue(object objs)
        {   
            object o = objs.GetType().GetProperty(fieldName).GetValue(objs, null);
            /*if (objs.GetType().GetProperty(fieldName).PropertyType.IsPrimitive)
            {
                return GetNativeObject(o);
            }
                
            else*/
                return o;
        }

        private object GetNativeObject(object o)
        {
            if (o.GetType() == typeof(System.Int32))
                return new Integer((int)o);
            if (o.GetType() == typeof(System.Int64))
                return new Long((long)o);
            if (o.GetType() == typeof(System.Int16))
                return new Short((short)o);
            if (o.GetType() == typeof(System.Boolean))
                return new java.lang.Boolean((bool)o);
            if (o.GetType() == typeof(System.Char))
                return new java.lang.Character((char)o);
            if (o.GetType() == typeof(System.Single))
                return new java.lang.Float((float)o);
            if (o.GetType() == typeof(System.Double))
                return new java.lang.Double((double)o);
            if (o.GetType() == typeof(System.SByte))
                return new java.lang.Byte((byte)o);
            return o;
        }

    
        protected  Type getFieldType() {
        return this.fieldType;
        }

    
    public override ObjectType getObjectType() {
        return this.objectType;
    }

    public bool equals(object other) {
        if ( this == other ) {
            return true;
        }
        if ( !(other.GetType() == typeof(DotnetBaseClassFieldExtractor)) ) {
            return false;
        }
        DotnetBaseClassFieldExtractor extr = (DotnetBaseClassFieldExtractor) other;
        return this.objectType.equals( extr.objectType ) && this.index == extr.index;
    }

        public override int getIndex()
        {
            return this.index;
        }
    

    }
}
