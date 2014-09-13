package edu.test.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/* @Author Sergio Bilello
 * 
 * Simple POJO
 * 
 */
public class PalindromeResult {
	int index;
	int length;
	String text;
	
	public PalindromeResult(String text, int index, int length) {
		this.text=text;
		this.index=index;
		this.length=length;
	}
	public int getIndex() {
		return index;
	}
	public int getLength() {
		return length;
	}
	public String getText() {
		return text;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "Text: "+ text +" Index: "+ index +" Length: "+length;
	}
	
	@Override
	public int hashCode(){
	    return new HashCodeBuilder()
	        .append(index)
	        .append(length)
	        .append(text)
	        .toHashCode();
	}
	
	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof PalindromeResult){
	        final PalindromeResult other = (PalindromeResult) obj;
	        return new EqualsBuilder()
	            .append(index, other.index)
	            .append(length, other.length)
	            .append(text, other.text)
	            .isEquals();
	    } else{
	        return false;
	    }
	}
}