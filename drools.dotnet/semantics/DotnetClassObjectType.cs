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
using org.drools.spi;
using java.sql;
using java.lang;

namespace org.drools.dotnet.semantics
{
    public class DotnetClassObjectType : ClassObjectType
    {

//        private static readonly long serialVersionUID = -2527407693999309827L;


        protected new Type objectTypeClass;

        public DotnetClassObjectType(Type objectTypeClass) : base(objectTypeClass)
        {
            this.objectTypeClass = objectTypeClass;
            setValueType(objectTypeClass);
        }

        // ------------------------------------------------------------
        // Instance methods
        // ------------------------------------------------------------

        /**
         * Return the Java object class.
         * 
         * @return The Java object class.
         */
        public override Class getClassType()
        {
            return Class.forName(this.objectTypeClass.AssemblyQualifiedName);
        }

        public bool matches(object obj)
        {
            if (obj.GetType().Equals(getClassType()))
                return true;
            else
                return false;
        }

        public override int getValueType()
        {
            return this.valueType;
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // java.lang.Object
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        protected void setValueType(Type clazz)
        {
            if (clazz == null)
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.NULL_TYPE;
            }
            else if (clazz == typeof(System.Char))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.CHAR_TYPE;
            }
            else if (clazz == typeof(System.SByte))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.BYTE_TYPE;
            }
            else if (clazz == typeof(System.Int16))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.SHORT_TYPE;
            }
            else if (clazz == typeof(System.Int32))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.INTEGER_TYPE;
            }
            else if (clazz == typeof(System.Int64))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.LONG_TYPE;
            }
            else if (clazz == typeof(System.Single))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.FLOAT_TYPE;
            }
            else if (clazz == typeof(System.Double))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.DOUBLE_TYPE;
            }
            else if (clazz == typeof(System.Boolean))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.BOOLEAN_TYPE;
            }
            else if (clazz == typeof(Date))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.DATE_TYPE;
            }
            else if (clazz == typeof(System.DateTime))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.DATE_TYPE;
            }
            else if (clazz == typeof(System.Array))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.ARRAY_TYPE;
            }
            else if (clazz == typeof(System.String))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.STRING_TYPE;
            }
            else if (clazz == typeof(System.Object))
            {
                this.valueType = org.drools.spi.Evaluator.__Fields.OBJECT_TYPE;
            }

        }

        

        
        

    }
}
