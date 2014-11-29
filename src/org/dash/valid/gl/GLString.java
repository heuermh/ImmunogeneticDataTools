package org.dash.valid.gl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GLString {
	private String glString;
	private List<List<String>> bAlleles;
	private List<List<String>> cAlleles;
	private List<List<String>> drb1Alleles;
	private List<List<String>> drb345Alleles;
	private List<List<String>> dqb1Alleles;
	private List<List<String>> dqa1Alleles;
		
    private static final Logger LOGGER = Logger.getLogger(GLString.class.getName());

	public GLString(String glString) {
		this.glString = glString;
		init();
	}
	
	void init() {
		List tempList = new ArrayList<String>();
		bAlleles = new ArrayList<List<String>>(tempList);
		
		tempList = new ArrayList<String>();
		cAlleles = new ArrayList<List<String>>(tempList);
		
		tempList = new ArrayList<String>();
		drb1Alleles = new ArrayList<List<String>>(tempList);
		
		tempList = new ArrayList<String>();
		drb345Alleles = new ArrayList<List<String>>(tempList);
		
		tempList = new ArrayList<String>();
		dqb1Alleles = new ArrayList<List<String>>(tempList);
		
		tempList = new ArrayList<String>();
		dqa1Alleles = new ArrayList<List<String>>(tempList);
		
		parseGLString();
	}
	
	public boolean drb345AppearsHomozygous() {
		if (getDrb345Alleles().size() <= 1) {
			return true;
		}
		
		return false;
	}
	
	private void parseGLString() {		
		List<String> genes = GLStringUtilities.parse(glString, GLStringConstants.GENE_DELIMITER);
		for (String gene : genes) {			
			String[] splitString = gene.split(GLStringUtilities.ESCAPED_ASTERISK);
			String locus = splitString[0];
			
			List<String> genotypeAmbiguities = GLStringUtilities.parse(gene, GLStringConstants.GENOTYPE_AMBIGUITY_DELIMITER);
			for (String genotypeAmbiguity : genotypeAmbiguities) {
				List<String> geneCopies = GLStringUtilities.parse(genotypeAmbiguity, GLStringConstants.GENE_COPY_DELIMITER);
				for (String geneCopy : geneCopies) {
					List<String> genePhases = GLStringUtilities.parse(geneCopy, GLStringConstants.GENE_PHASE_DELIMITER);
					for (String genePhase : genePhases) {
						List<String> alleleAmbiguities = GLStringUtilities.parse(genePhase, GLStringConstants.ALLELE_AMBIGUITY_DELIMITER);
						organizeByLocus(locus, alleleAmbiguities);
					}
				}
			}
		}
	}
	
	private void organizeByLocus(String locus, List<String> alleleAmbiguities) {
		if (alleleAmbiguities.size() == 0) {
			LOGGER.warning("Unexpected formatting of GLString.  No alleles found");
			return;
		}
		
		String allele = alleleAmbiguities.get(0);
		if (allele == null) {
			LOGGER.warning("Unexpected formatting of GLString, allele == null");
			return;
		}
		
		switch (locus) {
		case GLStringConstants.HLA_B:
			bAlleles.add(alleleAmbiguities);
			break;
		case GLStringConstants.HLA_C:
			cAlleles.add(alleleAmbiguities);
			break;
		case GLStringConstants.HLA_DRB1:
			drb1Alleles.add(alleleAmbiguities);
			break;
		case GLStringConstants.HLA_DRB3:
		case GLStringConstants.HLA_DRB4:
		case GLStringConstants.HLA_DRB5:
			drb345Alleles.add(alleleAmbiguities);
			break;
		case GLStringConstants.HLA_DQB1:
			dqb1Alleles.add(alleleAmbiguities);
			break;
		case GLStringConstants.HLA_DQA1:
			dqa1Alleles.add(alleleAmbiguities);
			break;
		default:
			break;
		}
	}
	
	public List<List<String>> getBAlleles() {
		return bAlleles;
	}

	public void setBAlleles(List<List<String>> bAlleles) {
		this.bAlleles = bAlleles;
	}

	public List<List<String>> getCAlleles() {
		return cAlleles;
	}

	public void setCAlleles(List<List<String>> cAlleles) {
		this.cAlleles = cAlleles;
	}

	public List<List<String>> getDrb1Alleles() {
		return drb1Alleles;
	}

	public void setDrb1Alleles(List<List<String>> drb1Alleles) {
		this.drb1Alleles = drb1Alleles;
	}

	public List<List<String>> getDrb345Alleles() {
		return drb345Alleles;
	}

	public void setDrb345Alleles(List<List<String>> drb345Alleles) {
		this.drb345Alleles = drb345Alleles;
	}

	public List<List<String>> getDqb1Alleles() {
		return dqb1Alleles;
	}

	public void setDqb1Alleles(List<List<String>> dqb1Alleles) {
		this.dqb1Alleles = dqb1Alleles;
	}

	public List<List<String>> getDqa1Alleles() {
		return dqa1Alleles;
	}

	public void setDqa1Alleles(List<List<String>> dqa1Alleles) {
		this.dqa1Alleles = dqa1Alleles;
	}
	
	public String getGLString() {
		return glString;
	}

	public void setGLString(String glString) {
		this.glString = glString;
	}
	
	public String toString() {
		String alleleSummary = glString + "\n" + bAlleles + "\n" + cAlleles + "\n" + drb1Alleles + "\n" + drb345Alleles + "\n" + dqb1Alleles + "\n" + dqa1Alleles;
		return alleleSummary;
	}
}
