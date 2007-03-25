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
using java.io;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using org.drools.dotnet.util;

namespace org.drools.dotnet.rule
{
    [Serializable]
    public class Package
    {
        internal org.drools.rule.Package _javaPackage;

        internal Package(org.drools.rule.Package package)
        {
            this._javaPackage = package;
        }

        public Package()
        {
            this._javaPackage = new org.drools.rule.Package();
        }

        public org.drools.rule.Package GetUnderlyingJavaObject()
        {
            return _javaPackage;
        }

        public string GetErrorSummary()
        {
            return this._javaPackage.getErrorSummary();
        }

        public void Save(Stream stream)
        {
            //FileOutputStream _javastream = new FileOutputStream(filePath);
            java.io.ByteArrayOutputStream byteStream = new java.io.ByteArrayOutputStream();
            ObjectOutput objOut = new DotnetObjectOutputStream(byteStream);
            this._javaPackage.writeExternal(objOut);
            stream.Write(byteStream.toByteArray(), 0, byteStream.toByteArray().Length);
            byteStream.close();
        }

        public void Load(Stream stream)
        {
            //FileInputStream stream = new FileInputStream(filePath);
            java.io.InputStream _javaStream= DroolsDotnetUtil.getJavaInputStreamFromSystemStream(stream);
            ObjectInput objInp = new ObjectInputStream(_javaStream);
            this._javaPackage.readExternal(objInp);
            _javaStream.close();
            
        }
       
        /*public void dummy()
        {
            _javaPackage.addGlobal(string, class)
            _javaPackage.addImport(string)
            _javaPackage.addRule(Rule)
            _javaPackage.checkValidity()
            _javaPackage.clear()
            _javaPackage.equals(object)
            _javaPackage.getClass()
            _javaPackage.getErrorSummary()
            _javaPackage.getGlobals()
            _javaPackage.getImports()
            _javaPackage.getName()
            _javaPackage.getPackageCompilationData()
            _javaPackage.getRule(string)
            _javaPackage.getRules()
            _javaPackage.getTypeResolver()
            _javaPackage.hashCode()
            _javaPackage.isValid()
            _javaPackage.readExternal(ObjectInput)
            _javaPackage.removeFunction(string);
            _javaPackage.removeGlobal(string)
            _javaPackage.removeImport(string)
            _javaPackage.removeRule(Rule)
            _javaPackage.setError(string)
            _javaPackage.setTypeSolver(TypeResolver)
            _javaPackage.toString()
            _javaPackage.wait()
            _javaPackage.writeExternal(ObjectOutput)

        }*/

        
    }
}
