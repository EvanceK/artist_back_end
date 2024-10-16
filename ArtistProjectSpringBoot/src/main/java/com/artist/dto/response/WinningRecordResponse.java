package com.artist.dto.response;

import java.util.List;

import com.artist.entity.Customers;

public class WinningRecordResponse {
    private Customers customer;
		 private List<WinningRecords> winningRecords;
		public WinningRecordResponse(Customers customer, List<WinningRecords> winningRecords) {
			super();
			this.customer = customer;
			this.winningRecords = winningRecords;
		}
		public WinningRecordResponse() {
			super();
		}
		public Customers getCustomer() {
			return customer;
		}
		public void setCustomer(Customers customer) {
			this.customer = customer;
		}
		public List<WinningRecords> getWinningRecords() {
			return winningRecords;
		}
		public void setWinningRecords(List<WinningRecords> winningRecords) {
			this.winningRecords = winningRecords;
		}
		 
		 

}
