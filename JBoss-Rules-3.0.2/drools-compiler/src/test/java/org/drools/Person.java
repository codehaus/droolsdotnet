package org.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Person
    implements
    Serializable,
    PersonInterface {
    /**
     * 
     */
    private static final long serialVersionUID = 2640286967578736742L;
    private final String name;
    private final String likes;
    private final int    age;
    private BigDecimal   bigDecimal;
    private BigInteger   bigInteger;

    private char         sex;

    private boolean      alive;

    private String       status;

    public Person(final String name) {
        this( name,
              "",
              0 );
    }

    public Person(final String name,
                  final String likes) {
        this( name,
              likes,
              0 );
    }

    public Person(final String name,
                  final String likes,
                  final int age) {
        this.name = name;
        this.likes = likes;
        this.age = age;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getStatus()
     */
    public String getStatus() {
        return this.status;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#setStatus(java.lang.String)
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getLikes()
     */
    public String getLikes() {
        return this.likes;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getName()
     */
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getAge()
     */
    public int getAge() {
        return this.age;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#isAlive()
     */
    public boolean isAlive() {
        return this.alive;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#setAlive(boolean)
     */
    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getSex()
     */
    public char getSex() {
        return this.sex;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#setSex(char)
     */
    public void setSex(final char sex) {
        this.sex = sex;
    }

    public String toString() {
        return "[Person name='" + this.name + "']";
    }

    /**
     * @inheritDoc
     */
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.age;
        result = PRIME * result + (this.alive ? 1231 : 1237);
        result = PRIME * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = PRIME * result + this.sex;
        return result;
    }

    /**
     * @inheritDoc
     */
    public boolean equals(final Object obj) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Person other = (Person) obj;
        if ( this.age != other.age ) {
            return false;
        }
        if ( this.alive != other.alive ) {
            return false;
        }
        if ( this.name == null ) {
            if ( other.name != null ) {
                return false;
            }
        } else if ( !this.name.equals( other.name ) ) {
            return false;
        }
        if ( this.sex != other.sex ) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getBigDecimal()
     */
    public BigDecimal getBigDecimal() {
        return this.bigDecimal;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#setBigDecimal(java.math.BigDecimal)
     */
    public void setBigDecimal(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#getBigInteger()
     */
    public BigInteger getBigInteger() {
        return this.bigInteger;
    }

    /* (non-Javadoc)
     * @see org.drools.PersonInterface#setBigInteger(java.math.BigInteger)
     */
    public void setBigInteger(final BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }
}