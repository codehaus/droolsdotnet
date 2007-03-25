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
using org.drools.dotnet.rule;
using org.drools.dotnet.util;
using org.drools.compiler;
using java.lang;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;


namespace org.drools.dotnet.compiler
{
    public class PackageBuilder
    {
        private org.drools.compiler.PackageBuilder _javaPackageBuilder;

        public PackageBuilder()
        {
            _javaPackageBuilder = new org.drools.compiler.PackageBuilder();
        }

        public PackageBuilder(org.drools.dotnet.rule.Package pkg)
        {
            _javaPackageBuilder = new org.drools.compiler.PackageBuilder(pkg._javaPackage);
        }

        public PackageBuilder(org.drools.dotnet.rule.Package pkg, PackageBuilderConfiguration conf)
        {
            _javaPackageBuilder = new org.drools.compiler.PackageBuilder(pkg._javaPackage, conf);
        }

        public PackageBuilder(PackageBuilderConfiguration conf)
        {
            _javaPackageBuilder = new org.drools.compiler.PackageBuilder(conf);
        }
        public org.drools.compiler.PackageBuilder GetUnderlyingJavaObject()
        {
            return _javaPackageBuilder;
        }


        public void AddPackageFromDrl(System.IO.Stream drlStream)
        {
            AddPackageFromDrl("", drlStream);
        }        

        public void AddPackageFromDrl(string fileName, System.IO.Stream drlStream)
        {
            _javaPackageBuilder.addPackageFromDrl(fileName, DroolsDotnetUtil.getJavaStringReaderFromSystemStream(drlStream));
        }

        
        public void AddPackageFromDrl(System.IO.Stream drlStream, System.IO.Stream dslStream)
        {

            _javaPackageBuilder.addPackageFromDrl(DroolsDotnetUtil.getJavaStringReaderFromSystemStream(drlStream), DroolsDotnetUtil.getJavaStringReaderFromSystemStream(dslStream));
        }

        public void AddPackageFromDrl(string drlString)
        {
            _javaPackageBuilder.addPackageFromDrl(new java.io.StringReader(drlString));
        }


        public void AddPackageFromDrl(string drlString, string dslString)
        {
            _javaPackageBuilder.addPackageFromDrl(new java.io.StringReader(drlString), new java.io.StringReader(dslString));
            
        }

        public void AddPackageFromXml(System.IO.Stream xmlStream)
        {
            _javaPackageBuilder.addPackageFromXml(DroolsDotnetUtil.getJavaStringReaderFromSystemStream(xmlStream));
        }

        public void AddPackageFromXml(string xmlString)
        {
            _javaPackageBuilder.addPackageFromXml(new java.io.StringReader(xmlString));
        }

        public void AddPackage(org.drools.lang.descr.PackageDescr pkgDesc)
        {
            _javaPackageBuilder.addPackage(pkgDesc);
        }
        public org.drools.dotnet.rule.Package GetPackage()
        {
            return new org.drools.dotnet.rule.Package(_javaPackageBuilder.getPackage());
        }

        public DroolsError[] GetErrors()
        {
            org.drools.compiler.DroolsError[] _javaErrors = this._javaPackageBuilder.getErrors();
            DroolsError[] errors = new DroolsError[_javaErrors.Length];
            int i = 0;
            foreach(org.drools.compiler.DroolsError error in _javaErrors)
            {
                errors[i] = new DroolsError(error);
                i++;
            }
            return errors;
            
        }

        public bool HasErrors()
        {
            return _javaPackageBuilder.hasErrors();
        }

        public int HashCode()
        {
            return _javaPackageBuilder.hashCode();
        }
        public string PrintErrors()
        {
            return _javaPackageBuilder.printErrors();
        }

        
    }
}
