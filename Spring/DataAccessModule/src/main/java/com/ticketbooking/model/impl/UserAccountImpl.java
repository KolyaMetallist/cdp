/**
 * 
 */
package com.ticketbooking.model.impl;

import com.ticketbooking.model.UserAccount;

/**
 * @author kolya_metallist
 *
 */
public class UserAccountImpl extends AbstractEntity implements UserAccount {

	private double amount;
	
	/* (non-Javadoc)
	 * @see com.ticketbooking.model.UserAccount#getAmount()
	 */
	@Override
	public double getAmount() {
		return amount;
	}

	/* (non-Javadoc)
	 * @see com.ticketbooking.model.UserAccount#setAmount(double)
	 */
	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAccountImpl [amount=");
		builder.append(amount);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof UserAccountImpl)) {
			return false;
		}
		UserAccountImpl other = (UserAccountImpl) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount)) {
			return false;
		}
		return true;
	}
	
}
