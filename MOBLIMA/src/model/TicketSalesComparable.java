package model;

@SuppressWarnings("rawtypes")
public class TicketSalesComparable implements Comparable{

	private Movie movie;
	private double totalTicketSales;
	
	public TicketSalesComparable(Movie movie, double totalTicketSales){
		this.movie = movie;
		this.totalTicketSales = totalTicketSales;
	}
	
	public Movie getMovie(){
		return movie;
	}
	
	public double getTotalTicketSales(){
		return totalTicketSales;
	}
	
	public int compareTo(Object o){
		TicketSalesComparable tsc = (TicketSalesComparable)o;
		if(this.getTotalTicketSales() < tsc.getTotalTicketSales()){
			return -1;
		}
		else {
			return 1;
		}
	}
}
