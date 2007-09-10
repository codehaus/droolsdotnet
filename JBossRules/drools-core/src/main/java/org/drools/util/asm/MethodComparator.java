package org.drools.util.asm;

import java.util.List;

import org.drools.asm.AnnotationVisitor;
import org.drools.asm.Attribute;
import org.drools.asm.ClassReader;
import org.drools.asm.ClassVisitor;
import org.drools.asm.FieldVisitor;
import org.drools.asm.MethodVisitor;
import org.drools.asm.util.TraceMethodVisitor;

/**
 * The purpose of this utility it to check if 2 method implementations are equivalent, by comparing the bytecode.
 * This essentual for node sharing where java semantics are involved.
 * @author Michael Neale
 */
public class MethodComparator {

    /**
     * This actually does the comparing.
     * Class1 and Class2 are class reader instances to the respective classes. method1 and method2 are looked up on the 
     * respective classes and their contents compared.
     * 
     * This is a convenience method.
     */
    public boolean equivalent(final String method1,
                              final ClassReader class1,
                              final String method2,
                              final ClassReader class2) {

        final List list1 = getMethodBytecode( method1,
                                              class1 );
        final List list2 = getMethodBytecode( method2,
                                              class2 );

        return compareBytecode( list1,
                                list2 );
    }

    /**
     * This will return a series of bytecode instructions which can be used to compare one method with another.
     * debug info like local var declarations and line numbers are ignored, so the focus is on the content.
     */
    public List getMethodBytecode(final String methodName,
                                  final ClassReader classReader) {
        final Tracer visit = new Tracer( methodName );
        classReader.accept( visit,
                            true );
        final TraceMethodVisitor trace = visit.getTrace();
        return trace.getText();
    }

    /**
     * This will return a series of bytecode instructions which can be used to compare one method with another.
     * debug info like local var declarations and line numbers are ignored, so the focus is on the content.
     */
    public static List getMethodBytecode(final String methodName,
                                         final byte[] bytes) {
        final Tracer visit = new Tracer( methodName );
        final ClassReader classReader = new ClassReader( bytes );
        classReader.accept( visit,
                            true );
        final TraceMethodVisitor trace = visit.getTrace();
        return trace.getText();
    }

    /**
     * Compares 2 bytecode listings.
     * Returns true if they are identical.
     */
    public static boolean compareBytecode(final List b1,
                                          final List b2) {
        if ( b1.size() != b2.size() ) {
            return false;
        }

        for ( int i = 0; i < b1.size(); i++ ) {
            if ( !(b1.get( i ).equals( b2.get( i ) )) ) {
                return false;

            }
        }
        return true;
    }

    public static class Tracer
        implements
        ClassVisitor {

        private TraceMethodVisitor trace;
        private String             methodName;

        public Tracer(final String methodName) {
            this.methodName = methodName;
        }

        public void visit(final int version,
                          final int access,
                          final String name,
                          final String signature,
                          final String superName,
                          final String[] interfaces) {
        }

        public AnnotationVisitor visitAnnotation(final String desc,
                                                 final boolean visible) {
            return new DummyAnnotationVisitor();
        }

        public void visitAttribute(final Attribute attr) {
        }

        public void visitEnd() {
        }

        public FieldVisitor visitField(final int access,
                                       final String name,
                                       final String desc,
                                       final String signature,
                                       final Object value) {
            return null;
        }

        public void visitInnerClass(final String name,
                                    final String outerName,
                                    final String innerName,
                                    final int access) {
        }

        public MethodVisitor visitMethod(final int access,
                                         final String name,
                                         final String desc,
                                         final String signature,
                                         final String[] exceptions) {

            if ( this.methodName.equals( name ) ) {
                this.trace = new TraceMethodVisitor();
                return this.trace;
            }
            return null;
        }

        public void visitOuterClass(final String owner,
                                    final String name,
                                    final String desc) {
        }

        public void visitSource(final String source,
                                final String debug) {
        }

        public TraceMethodVisitor getTrace() {
            return this.trace;
        }

    }

    static class DummyAnnotationVisitor
        implements
        AnnotationVisitor {

        public void visit(final String name,
                          final Object value) {
        }

        public AnnotationVisitor visitAnnotation(final String name,
                                                 final String desc) {
            return new DummyAnnotationVisitor();
        }

        public AnnotationVisitor visitArray(final String name) {
            return new DummyAnnotationVisitor();
        }

        public void visitEnd() {
        }

        public void visitEnum(final String name,
                              final String desc,
                              final String value) {
        }

    }

}
