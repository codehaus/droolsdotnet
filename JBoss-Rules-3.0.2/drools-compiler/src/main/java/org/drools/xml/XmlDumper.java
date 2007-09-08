package org.drools.xml;

/*
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.BoundVariableDescr;
import org.drools.lang.descr.ColumnDescr;
import org.drools.lang.descr.EvalDescr;
import org.drools.lang.descr.ExistsDescr;
import org.drools.lang.descr.FieldBindingDescr;
import org.drools.lang.descr.FunctionDescr;
import org.drools.lang.descr.LiteralDescr;
import org.drools.lang.descr.NotDescr;
import org.drools.lang.descr.OrDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.PackageDescrDumper;
import org.drools.lang.descr.PredicateDescr;
import org.drools.lang.descr.QueryDescr;
import org.drools.lang.descr.ReturnValueDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.util.ReflectiveVisitor;

/**
 * This utility will take a AST of a rule package, and emit XML.
 * This can be used in porting from DRL to XML.
 * @author <a href="mailto:jayaramcs@gmail.com">Author Jayaram C S</a>
 */
public class XmlDumper extends ReflectiveVisitor
    implements
    PackageDescrDumper {

    private StringBuffer        xmlDump;
    private final static String eol = System.getProperty( "line.separator" );

    public synchronized String dump(final PackageDescr packageDescr) {
        this.xmlDump = new StringBuffer();
        visitPackageDescr( packageDescr );
        return this.xmlDump.toString();
    }

    public void visitAndDescr(final AndDescr descr) {
        this.template = new String();
        if ( descr.getDescrs() != Collections.EMPTY_LIST ) {
            this.template = "<and>" + processDescrList( descr.getDescrs() ) + "</and>";
        } else {
            this.template = "<and> </and>";
        }
    }

    public void visitAttributeDescr(final AttributeDescr attributeDescr) {
        this.template = new String();
        this.template = "<rule-attribute name=\"" + attributeDescr.getName() + "\" value=\"" + attributeDescr.getValue() + "\" />" + XmlDumper.eol;
    }

    public void visitBoundVariableDescr(final BoundVariableDescr descr) {
        this.template = new String();
        this.template = "<bound-variable field-name=\"" + descr.getFieldName() + "\" evaluator=\"" + getEvaluator( descr.getEvaluator() ) + "\" identifier=\"" + descr.getIdentifier() + "\" />" + XmlDumper.eol;
    }

    public void visitColumnDescr(final ColumnDescr descr) {
        this.template = new String();
        if ( descr.getDescrs() != Collections.EMPTY_LIST ) {
            if ( descr.getIdentifier() != null ) {
                this.template = "<column identifier=\"" + descr.getIdentifier() + "\" object-type=\"" + descr.getObjectType() + "\" >" + processDescrList( descr.getDescrs() ) + "</column>" + XmlDumper.eol;
            } else {
                this.template = "<column object-type=\"" + descr.getObjectType() + "\" >" + processDescrList( descr.getDescrs() ) + "</column>" + XmlDumper.eol;
            }
        } else {
            if ( descr.getIdentifier() != null ) {
                this.template = "<column identifier=\"" + descr.getIdentifier() + "\" object-type=\"" + descr.getObjectType() + "\" > </column>" + XmlDumper.eol;
            } else {
                this.template = "<column object-type=\"" + descr.getObjectType() + "\" > </column>" + XmlDumper.eol;
            }
        }

    }

    public void visitEvalDescr(final EvalDescr descr) {
        this.template = new String();
        this.template = "<eval>" + descr.getText() + "</eval>" + XmlDumper.eol;
    }

    public void visitExistsDescr(final ExistsDescr descr) {
        this.template = new String();
        if ( descr.getDescrs() != Collections.EMPTY_LIST ) {
            this.template = "<exists>" + processDescrList( descr.getDescrs() ) + "</exists>";
        } else {
            this.template = "<exists> </exists>";
        }
    }

    public void visitFieldBindingDescr(final FieldBindingDescr descr) {
        this.template = new String();
        this.template = "<field-binding field-name=\"" + descr.getFieldName() + "\" identifier=\"" + descr.getIdentifier() + "\" />" + XmlDumper.eol;
    }

    public void visitFunctionDescr(final FunctionDescr functionDescr) {
        this.template = new String();
        final String parameterTemplate = processParameters( functionDescr.getParameterNames(),
                                                      functionDescr.getParameterTypes() );

        this.template = "<function return-type=\"" + functionDescr.getReturnType() + "\" name=\"" + functionDescr.getName() + "\">" + XmlDumper.eol + parameterTemplate + "<body>" + XmlDumper.eol + functionDescr.getText() + XmlDumper.eol + "</body>" + XmlDumper.eol + "</function>" + XmlDumper.eol;
    }

    public void visitLiteralDescr(final LiteralDescr descr) {
        this.template = new String();
        this.template = "<literal field-name=\"" + descr.getFieldName() + "\" evaluator=\"" + getEvaluator( descr.getEvaluator() ) + "\" value=\"" + descr.getText() + "\" />" + XmlDumper.eol;
    }

    public void visitNotDescr(final NotDescr descr) {
        this.template = new String();
        if ( descr.getDescrs() != Collections.EMPTY_LIST ) {
            this.template = "<not>" + processDescrList( descr.getDescrs() ) + "</not>";
        } else {
            this.template = "<not> </not>";
        }

    }

    public void visitOrDescr(final OrDescr descr) {
        this.template = new String();
        if ( descr.getDescrs() != Collections.EMPTY_LIST ) {
            this.template = "<or>" + processDescrList( descr.getDescrs() ) + "</or>";
        } else {
            this.template = "<or> </or>";
        }
    }

    public void visitPackageDescr(final PackageDescr packageDescr) {
        final String packageName = packageDescr.getName();
        final String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " + XmlDumper.eol + " <package name=\"" + packageName + "\"  " + XmlDumper.eol + "\txmlns=\"http://drools.org/drools-3.0\" " + XmlDumper.eol + "\txmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\" " + XmlDumper.eol
                           + "\txs:schemaLocation=\"http://drools.org/drools-3.0 drools-3.0.xsd\"> " + XmlDumper.eol;
        appendXmlDump( xmlString );
        appendXmlDump( processImportsList( packageDescr.getImports() ) );
        appendXmlDump( processGlobalsMap( packageDescr.getGlobals() ) );
        appendXmlDump( processFunctionsList( packageDescr.getFunctions() ) );
        appendXmlDump( processRules( packageDescr.getRules() ) );
        appendXmlDump( "</package>" );
    }

    public void visitPredicateDescr(final PredicateDescr descr) {
        this.template = new String();
        this.template = "<predicate field-name=\"" + descr.getFieldName() + "\" identifier=\"" + descr.getDeclaration() + "\" >" + descr.getText() + "</predicate>" + XmlDumper.eol;

    }

    public void visitReturnValueDescr(final ReturnValueDescr descr) {
        this.template = new String();
        this.template = "<return-value field-name=\"" + descr.getFieldName() + "\" evaluator=\"" + getEvaluator( descr.getEvaluator() ) + "\" >" + descr.getText() + "</return-value>" + XmlDumper.eol;
    }

    public void visitQueryDescr(final QueryDescr descr) {
        this.template = new String();
        this.template = "<query name=\"" + descr.getName() + "\">" + "<lhs>" + processDescrList( descr.getLhs().getDescrs() ) + "</lhs>" + "</query>";
    }

    private String template;

    private String processRules(final List rules) {
        String ruleList = "";
        for ( final Iterator iterator = rules.iterator(); iterator.hasNext(); ) {
            final RuleDescr ruleDescr = (RuleDescr) iterator.next();
            String rule = "<rule name=\"" + ruleDescr.getName() + "\">" + XmlDumper.eol;
            final String attribute = processAttribute( ruleDescr.getAttributes() );
            String lhs = "";
            if ( ruleDescr.getLhs().getDescrs() != Collections.EMPTY_LIST ) {
                lhs = "<lhs>" + processDescrList( ruleDescr.getLhs().getDescrs() ) + "</lhs>";
            } else {

                lhs = "<lhs> </lhs>";
            }

            final String rhs = "<rhs>" + ruleDescr.getConsequence() + "</rhs>" + XmlDumper.eol;
            rule += attribute;
            rule += lhs;
            rule += rhs;
            rule += "</rule>";
            ruleList += rule;
        }

        return ruleList + XmlDumper.eol;
    }

    private String processDescrList(final List descr) {
        String descrString = "";
        for ( final Iterator iterator = descr.iterator(); iterator.hasNext(); ) {
            visit( iterator.next() );
            descrString += this.template;
            descrString += XmlDumper.eol;
        }
        return descrString + XmlDumper.eol;
    }

    private String processFunctionsList(final List functions) {
        String functionList = "";

        for ( final Iterator iterator = functions.iterator(); iterator.hasNext(); ) {
            visit( iterator.next() );
            functionList += this.template;
        }

        return functionList + XmlDumper.eol;
    }

    private String processAttribute(final List attributes) {

        String attributeList = "";
        for ( final Iterator iterator = attributes.iterator(); iterator.hasNext(); ) {
            final AttributeDescr attributeDescr = (AttributeDescr) iterator.next();
            visit( attributeDescr );
            attributeList += this.template;
        }
        return attributeList + XmlDumper.eol;
    }

    private String processParameters(final List parameterNames,
                                     final List parameterTypes) {
        String paramList = "";
        int i = 0;
        for ( final Iterator iterator = parameterNames.iterator(); iterator.hasNext(); i++ ) {
            final String paramName = (String) iterator.next();
            final String paramType = (String) parameterTypes.get( i );
            final String paramTemplate = "<parameter identifier=\"" + paramName + "\" type=\"" + paramType + "\" />" + XmlDumper.eol;
            paramList += paramTemplate;
        }

        return paramList + XmlDumper.eol;
    }

    private String processGlobalsMap(final Map globals) {
        String globalList = "";
        for ( final Iterator iterator = globals.keySet().iterator(); iterator.hasNext(); ) {
            final String key = (String) iterator.next();
            final String value = (String) globals.get( key );
            final String globalTemplate = "<global identifier=\"" + key + "\" type=\"" + value + "\" />" + XmlDumper.eol;
            globalList += globalTemplate;
        }

        return globalList + XmlDumper.eol;
    }

    private String processImportsList(final List imports) {
        String importList = "";

        for ( final Iterator iterator = imports.iterator(); iterator.hasNext(); ) {
            final String importString = (String) iterator.next();
            final String importTemplate = "<import name=\"" + importString + "\" /> " + XmlDumper.eol;
            importList += importTemplate;
        }
        return importList + XmlDumper.eol;
    }

    private void appendXmlDump(final String temp) {
        this.xmlDump.append( temp );
    }

    private String getEvaluator(String eval) {

        eval = eval.replaceAll( "<",
                                "&lt;" );
        eval = eval.replaceAll( ">",
                                "&gt;" );
        return eval;
    }
}