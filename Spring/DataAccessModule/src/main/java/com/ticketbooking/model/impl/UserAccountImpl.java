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

}
