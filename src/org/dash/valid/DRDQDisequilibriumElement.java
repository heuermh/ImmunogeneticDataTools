package org.dash.valid;

public class DRDQDisequilibriumElement extends DisequilibriumElement {
	private String hladrb1Element;
	private String hladrb345Element;
	private String hladqa1Element;
	private String hladqb1Element;
	
	public DRDQDisequilibriumElement(String hladrb1Element, String hladrb345Element, String hladqa1Element,
									String hladqb1Element, String frequency, String note) {
		this.hladrb1Element = hladrb1Element;
		this.hladrb345Element = hladrb345Element;
		this.hladqa1Element = hladqa1Element;
		this.hladqb1Element = hladqb1Element;
		setFrequency(frequency);
		setNote(note);
	}
	
	public String getHladrb1Element() {
		return hladrb1Element;
	}
	public void setHladrb1Element(String bElement) {
		this.hladrb1Element = bElement;
	}
	public String getHladrb345Element() {
		return hladrb345Element;
	}
	public void setHladrb345Element(String cElement) {
		this.hladrb345Element = cElement;
	}
	public String getHladqa1Element() {
		return hladqa1Element;
	}

	public void setHladqa1Element(String hladqa1Element) {
		this.hladqa1Element = hladqa1Element;
	}

	public String getHladqb1Element() {
		return hladqb1Element;
	}

	public void setHladqb1Element(String hladqb1Element) {
		this.hladqb1Element = hladqb1Element;
	}
	
	public String toString() {
		return ("DRB1 Locus: " + this.getHladrb1Element() + "\n" + 
				"DRB345 Locus: " + this.getHladrb345Element() + "\n" + 
				"DQA1 Locus: " + this.getHladqa1Element() + "\n" +
				"DQB1 Locus: " + this.getHladqb1Element() + "\n" +
				"NegLocFreq: " + this.getFrequency() + "\n" + 
				"Notes: " + this.getNote());
	}
}