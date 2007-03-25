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
using System.Reflection;
using System.Runtime.Serialization;

namespace org.drools.dotnet.util
{
    public class DotnetObjectOutputStream : java.io.ObjectOutputStream
    {
        public DotnetObjectOutputStream(java.io.OutputStream os) : base(os)
        {
            enableReplaceObject(true);
        }
        protected override object replaceObject(object obj)
        {
            if (obj is SerializationInfo)
            {
                return new SerializationInfoWrapper((SerializationInfo)obj);
            }
            else if (obj is int)
            {
                return new Int32Wrapper((int)obj);
            }
            else if (obj is short)
            {
                return new Int16Wrapper((short)obj);
            }
            else if (obj is long)
            {
                return new Int64Wrapper((long)obj);
            }
            else if (obj is double)
            {
                return new DoubleWrapper((double)obj);
            }
            else if (obj is float)
            {
                return new FloatWrapper((float)obj);
            }
            else if (obj is bool)
            {
                return new BooleanWrapper((bool)obj);
            }
            else if (obj is char)
            {
                return new CharWrapper((char)obj);
            }
            else if (obj is byte)
            {
                return new ByteWrapper((byte)obj);
            }
            return base.replaceObject(obj);
        }
    }

    class SerializationInfoWrapper :java.io.Serializable.__Interface
    {
        private SerializationInfo value;

        internal SerializationInfoWrapper(SerializationInfo value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }
    class Int32Wrapper : java.io.Serializable.__Interface
    {
        private int value;

        internal Int32Wrapper(int value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }
    class Int16Wrapper : java.io.Serializable.__Interface
    {
        private short value;

        internal Int16Wrapper(short value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }

    class Int64Wrapper : java.io.Serializable.__Interface
    {
        private long value;

        internal Int64Wrapper(long value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }

    class DoubleWrapper : java.io.Serializable.__Interface
    {
        private double value;

        internal DoubleWrapper(double value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }

    class FloatWrapper : java.io.Serializable.__Interface
    {
        private float value;

        internal FloatWrapper(float value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }

    class CharWrapper : java.io.Serializable.__Interface
    {
        private char value;

        internal CharWrapper(char value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }

    class ByteWrapper : java.io.Serializable.__Interface
    {
        private byte value;

        internal ByteWrapper(byte value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }

    class BooleanWrapper : java.io.Serializable.__Interface
    {
        private bool value;

        internal BooleanWrapper(bool value)
        {
            this.value = value;
        }

        private object readResolve()
        {
            return value;
        }
    }
}

/*        static void Main(string[] args)
        {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            Program p = new Program(baos);
            java.util.Hashtable h = new java.util.Hashtable();
            h.put("foo", 42);
            p.writeObject(h);
            p.close();

            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(baos.toByteArray()));
            Console.WriteLine(ois.readObject());
        }*/

        
