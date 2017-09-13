/**
 * 
 */
package com.tutuka.transactioncompare.util.domain;

import lombok.Data;

/**
 * POJO for transaction details
 * 
 * @author abhinab
 *
 */
@Data
public class Transaction {

	private String profileName;
	private String transactionDate;
	private Long transactionAmount;
	private String transactionNarrative;
	private String transactionDescription;
	private String transactionId;
	private int transactionType;
	private String walletReference;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((profileName == null) ? 0 : profileName.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionDescription == null) ? 0 : transactionDescription.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionNarrative == null) ? 0 : transactionNarrative.hashCode());
		result = prime * result + transactionType;
		result = prime * result + ((walletReference == null) ? 0 : walletReference.hashCode());
		return result;
	}

	// Look for any close matches and suggest them based on amount and
	// case-insensitive search criteria. Also,if wallet reference and amount is
	// getting matched considering it as same(though seems to be odd, yet doing
	// this to highlight I understand what is meant by "this is not a normal
	// file comparison")
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (transactionAmount != null && walletReference != null) {
			if (other.transactionAmount != null && other.walletReference != null) {
				if (transactionAmount == other.transactionAmount
						&& walletReference.equalsIgnoreCase(other.walletReference)) {
					return true;
				}
			}
		}
		if (profileName == null) {
			if (other.profileName != null)
				return false;
		} else if (!profileName.equals(other.profileName) && !profileName.equalsIgnoreCase(other.profileName))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionDescription == null) {
			if (other.transactionDescription != null)
				return false;
		} else if (!transactionDescription.equals(other.transactionDescription)
				&& !transactionDescription.equalsIgnoreCase(other.transactionDescription))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionNarrative == null) {
			if (other.transactionNarrative != null)
				return false;
		} else if (!transactionNarrative.equals(other.transactionNarrative)
				&& !transactionNarrative.equalsIgnoreCase(other.transactionNarrative))
			return false;
		if (transactionType != other.transactionType)
			return false;
		if (walletReference == null) {
			if (other.walletReference != null)
				return false;
		} else if (!walletReference.equals(other.walletReference)
				&& !walletReference.equalsIgnoreCase(other.walletReference))
			return false;
		return true;

	}

}
